package com.epam.audiomanager.database.dao.impl.audio;

import com.epam.audiomanager.database.dao.AbstractDAO;
import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AudioTrackDAO extends AbstractDAO {
    private static final String FIND_ALL_AUDIO_TRACKS = "select AudioTrack.id, AudioTrack.name, AudioTrack.artist, " +
            "AudioTrack.year, AudioTrack.price, full_audio_path, demo_audio_path, Album.name from AudioTrack join Album " +
            "on album_id = Album.id";
    private static final String FIND_AUDIO_TRACKS_BY_SMTH = "select AudioTrack.id, AudioTrack.name, AudioTrack.artist, " +
            "AudioTrack.year, AudioTrack.price, full_audio_path, demo_audio_path, Album.name from AudioTrack join Album " +
            "on album_id = Album.id where AudioTrack.name = ? or Album.name = ? or AudioTrack.artist = ?";
    private static final String INSERT_AUDIOTRACK_INTO_MEDIATRACK_LIBRARY = "insert into MediaLibrary(audio_id, user_id) " +
            "values(?, ?)";

    @Override
    public List findAll() throws ProjectException {
        Statement statement = null;
        List<AudioTrack> audioTracks = new ArrayList<>();
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_AUDIO_TRACKS);
            while (resultSet.next()){
                AudioTrack audioTrack = new AudioTrack(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getBigDecimal(5),
                        resultSet.getString(6), resultSet.getString(7),
                        resultSet.getString(8));
                audioTracks.add(audioTrack);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException, searching all audiotracks", e);
            throw new ProjectException("SQLException, searching all audiotracks", e);
        } finally {
            if (connection != null){
                close(statement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return audioTracks;
    }

    @Override
    public boolean findByID(int... id) throws ProjectException {
        return false;
    }

    public List findAudioTracksBySmth(String entity) throws ProjectException {
        PreparedStatement preparedStatement = null;
        List<AudioTrack> audioTracks = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(FIND_AUDIO_TRACKS_BY_SMTH);
            for (int i = 1; i <= 3; i++){
                preparedStatement.setString(i, entity);
            }
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
            throw new ProjectException("SQLException, searching audiotracks", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return audioTracks;
    }

    public void buyAudioTrack(int clientId, int audioId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_AUDIOTRACK_INTO_MEDIATRACK_LIBRARY);
            preparedStatement.setInt(2, clientId);
            preparedStatement.setInt(1, audioId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, Buying audiotrack", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }
}