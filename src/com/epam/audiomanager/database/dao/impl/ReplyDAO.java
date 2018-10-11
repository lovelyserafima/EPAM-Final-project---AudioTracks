package com.epam.audiomanager.database.dao.impl;

import com.epam.audiomanager.database.dao.AbstractDAO;
import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.exception.ProjectException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReplyDAO extends AbstractDAO {
    private static final String FIND_BY_USER_ID_AND_AUDIO_ID = "select * from Reply where user_id = ? and audio_id = ?";
    private static final String INSERT_REPLY = "insert into Reply(user_id, audio_id, text) values(?, ?, ?)";
    @Override
    public List findAll() throws ProjectException {
        return null;
    }

    @Override
    public boolean findByID(int... id) throws ProjectException {
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
}
