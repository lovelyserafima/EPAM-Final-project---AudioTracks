package com.epam.audiomanager.database.dao.impl;

import com.epam.audiomanager.database.dao.AbstractDao;
import com.epam.audiomanager.database.dao.ReplyDao;
import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.entity.audio.Reply;
import com.epam.audiomanager.exception.ProjectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReplyDaoImpl extends AbstractDao implements ReplyDao {
    private static final String FIND_BY_USER_ID_AND_AUDIO_ID = "select * from Reply where user_id = ? and audio_id = ?";
    private static final String INSERT_REPLY = "insert into Reply(user_id, audio_id, text) values(?, ?, ?)";
    private static final String FIND_REPLIES_BY_AUDIO_ID = "select id, user_id, text from Reply where audio_id = ?";
    private static final String FIND_USER_LOGIN_BY_ID = "select login from User where id = ?";

    @Override
    public boolean findByIds(int... id) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_BY_USER_ID_AND_AUDIO_ID);
            preparedStatement.setInt(1, id[0]);
            preparedStatement.setInt(2, id[1]);
            return preparedStatement.executeQuery().next();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, searching reply");
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public void insertReply(int user_id, int audio_id, String text) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_REPLY);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, audio_id);
            preparedStatement.setString(3, text);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, inserting reply", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public List<Reply> findRepliesByAudioId(int audioId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        List<Reply> replies = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(FIND_REPLIES_BY_AUDIO_ID);
            preparedStatement.setInt(1, audioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                preparedStatement = connection.prepareStatement(FIND_USER_LOGIN_BY_ID);
                preparedStatement.setInt(1, resultSet.getInt(2));
                ResultSet resultSerForUser = preparedStatement.executeQuery();
                if (resultSerForUser.next()) {
                    replies.add(new Reply(resultSet.getInt(1), resultSerForUser.getString(1),
                            resultSet.getString(3)));
                }
            }
        } catch (SQLException e) {
            throw new ProjectException("SQLException, find replies by audio id)");
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return replies;
    }

    @Override
    public List findAll() throws ProjectException {
        return null;
    }
}
