package com.epam.audiomanager.database.dao.impl;

import com.epam.audiomanager.database.dao.AbstractDao;
import com.epam.audiomanager.database.dao.AudioTrackDao;
import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AudioTrackDaoImpl extends AbstractDao implements AudioTrackDao {
    private static final String FIND_ALL_AUDIO_TRACKS = "select AudioTrack.id, AudioTrack.name, AudioTrack.artist, " +
            "AudioTrack.year, AudioTrack.price, full_audio_path, demo_audio_path, Album.name from AudioTrack left join " +
            "Album on album_id = Album.id where available = 1";
    private static final String FIND_AUDIO_TRACKS_BY_SMTH = "select AudioTrack.id, AudioTrack.name, AudioTrack.artist, " +
            "AudioTrack.year, AudioTrack.price, full_audio_path, demo_audio_path, Album.name from AudioTrack left join Album " +
            "on album_id = Album.id where AudioTrack.name = ? or Album.name = ? or AudioTrack.artist = ? and " +
            "available = 1";
    private static final String FIND_AUDIO_TRACK_BY_ID = "select AudioTrack.id, AudioTrack.name, AudioTrack.artist, " +
            "AudioTrack.year, AudioTrack.price, full_audio_path, demo_audio_path, Album.name from AudioTrack left join Album " +
            "on album_id = Album.id where AudioTrack.id = ?";
    private static final String INSERT_AUDIOTRACK_INTO_MEDIATRACK_LIBRARY = "insert into MediaLibrary(audio_id, user_id) " +
            "values(?, ?)";
    private static final String UPDATE_AUDIO_TRACK = "update AudioTrack set album_id = ?, name = ?, artist = ?, year = ?," +
            " price = ?, full_audio_path = ?, demo_audio_path = ? where id = ?";
    private static final String DELETE_AUDIO_TRACK = "update AudioTrack set available = 0 where id = ?";
    private static final String FIND_AUDIO_TRACK_BY_NAME_AND_BAND = "select id from AudioTrack where name = ? and " +
            "artist = ?";
    private static final String INSERT_AUDIO_TRACK = "insert into AudioTrack(album_id, name, artist, year, price, " +
            "full_audio_path, demo_audio_path) values(?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_AUDIO_TRACK_WITHOUT_ALBUM = "insert into AudioTrack(name, artist, year, " +
            "price, full_audio_path, demo_audio_path) values(?, ?, ?, ?, ?, ?)";

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
            throw new ProjectException("SQLException, searching all audiotracks", e);
        } finally {
            if (connection != null){
                close(statement);
            }
        }
        return audioTracks;
    }

    @Override
    public AudioTrack findByAudioId(int audioId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        AudioTrack audioTrack = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_AUDIO_TRACK_BY_ID);
            preparedStatement.setInt(1, audioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                audioTrack = new AudioTrack(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getBigDecimal(5),
                        resultSet.getString(6), resultSet.getString(7),
                        resultSet.getString(8));
            }
        } catch (SQLException e){
            throw new ProjectException("SQLException, searching audio by id");
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return audioTrack;
    }

    @Override
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
            }
        }
    }

    @Override
    public void update(int audioId, Integer albumId, String name, String band, int year, BigDecimal price,
                       String demoAudioPath, String fullAudioPath) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(UPDATE_AUDIO_TRACK);
            preparedStatement.setInt(1, albumId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, band);
            preparedStatement.setInt(4, year);
            preparedStatement.setBigDecimal(5, price);
            preparedStatement.setString(6, fullAudioPath);
            preparedStatement.setString(7, demoAudioPath);
            preparedStatement.setInt(8, audioId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, editing audiotrack", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
    }

    @Override
    public void deleteById(int audioId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(DELETE_AUDIO_TRACK);
            preparedStatement.setInt(1, audioId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, deleting audioTrack", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
    }

    @Override
    public boolean findByNameAndBand(String name, String band) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_AUDIO_TRACK_BY_NAME_AND_BAND);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, band);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, find by name and band");
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
    }

    @Override
    public void insert(Integer album_id, String name, String artist, int year, BigDecimal price,
                          String full_audio_path, String demo_audio_path) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_AUDIO_TRACK);
            preparedStatement.setInt(1, album_id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, artist);
            preparedStatement.setInt(4, year);
            preparedStatement.setBigDecimal(5, price);
            preparedStatement.setString(6, full_audio_path);
            preparedStatement.setString(7, demo_audio_path);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, inserting new audiotrack");
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
    }

    @Override
    public void insertWithoutAlbum(String name, String artist, int year, BigDecimal price, String full_audio_path,
                                   String demo_audio_path) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_AUDIO_TRACK_WITHOUT_ALBUM);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, artist);
            preparedStatement.setInt(3, year);
            preparedStatement.setBigDecimal(4, price);
            preparedStatement.setString(5, full_audio_path);
            preparedStatement.setString(6, demo_audio_path);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, inserting new audiotrack");
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
    }

    @Override
    public boolean findByIds(int... id) throws ProjectException {
        return false;
    }
}