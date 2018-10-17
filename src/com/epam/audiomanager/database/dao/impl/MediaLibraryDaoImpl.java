package com.epam.audiomanager.database.dao.impl;

import com.epam.audiomanager.database.dao.AbstractDao;
import com.epam.audiomanager.database.dao.MediaLibraryDao;
import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MediaLibraryDaoImpl extends AbstractDao implements MediaLibraryDao {
    private static final String FIND_ALL_USER_TRACKS = "select AudioTrack.id, AudioTrack.name, AudioTrack.artist, " +
            "AudioTrack.year, AudioTrack.price, full_audio_path, demo_audio_path, Album.name from User join " +
            "MediaLibrary on User.id = MediaLibrary.user_id join AudioTrack on MediaLibrary.audio_id = AudioTrack.id " +
            "left join Album on AudioTrack.album_id = Album.id where User.id = ? and available = 1";
    private static final String FIND_ALL_USERS_HAVING_TRACK = "select id, login from User join MediaLibrary " +
            "on User.id = MediaLibrary.user_id where audio_id = ?";
    private static final String FIND_BY_ID = "select user_id from MediaLibrary where user_id = ? and audio_id = ?";

    @Override
    public List findAll(int id) throws ProjectException {
        PreparedStatement preparedStatement = null;
        List<AudioTrack> audioTracks = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(FIND_ALL_USER_TRACKS);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                AudioTrack audioTrack = new AudioTrack(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getBigDecimal(5),
                        resultSet.getString(6), resultSet.getString(7),
                        resultSet.getString(8));
                audioTracks.add(audioTrack);
            }
        } catch (SQLException e) {
            throw new ProjectException("SQLException, searching all user tracks", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return audioTracks;
    }

    @Override
    public boolean findByIds(int... id) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id[0]);
            preparedStatement.setInt(2, id[1]);
            return preparedStatement.executeQuery().next();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, searching by id order", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public List<User> findUsersHavingTrack(int audioId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        List<User> users = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(FIND_ALL_USERS_HAVING_TRACK);
            preparedStatement.setInt(1, audioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(new User(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            throw new ProjectException("SQLException, searching users having tracks", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return users;
    }

    @Override
    public List findAll() throws ProjectException {
        return null;
    }
}
