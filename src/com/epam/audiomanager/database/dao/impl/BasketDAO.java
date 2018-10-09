package com.epam.audiomanager.database.dao.impl;

import com.epam.audiomanager.database.dao.AbstractDAO;
import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketDAO extends AbstractDAO {
    private static final String FIND_ORDER_BY_ID_AND_AUDIO_ID = "select user_id, audiotrack_id from Basket where user_id = " +
            "? and audiotrack_id  = ? and available = 1";
    private static final String INSERT_ORDER = "insert into Basket(audiotrack_id, user_id) values(?, ?)";
    private static final String FIND_ALL_ORDERS_OF_CLIENTS = "select AudioTrack.id, AudioTrack.name, AudioTrack.artist, " +
            "AudioTrack.year, AudioTrack.price, full_audio_path, demo_audio_path,Album.name from User join " +
            "Basket on User.id = Basket.user_id join AudioTrack on Basket.audiotrack_id = AudioTrack.id join Album \n" +
            "on AudioTrack.artist = Album.artist where User.id = ? and available = 1";
    private static final String DELETE_ORDER_FROM_BASKET_BY_ID = "update Basket set available = 0 " +
            "where user_id = ? and audiotrack_id = ?";


    public boolean insertOrder(int clientId, int audioId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setInt(2, clientId);
            preparedStatement.setInt(1, audioId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new ProjectException("Error with inserting new order", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    public List findAll(int id) throws ProjectException {
        PreparedStatement preparedStatement = null;
        List<AudioTrack> audioTracks = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(FIND_ALL_ORDERS_OF_CLIENTS);
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
            LOGGER.error("SQLException, searching all orders", e);
            throw new ProjectException("SQLException, searching all orders", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return audioTracks;
    }

    @Override
    public List findAll() throws ProjectException {
        return null;
    }

    @Override
    public boolean findByID(int... id) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID_AND_AUDIO_ID);
            preparedStatement.setInt(1, id[0]);
            preparedStatement.setInt(2, id[1]);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new ProjectException("Error with inserting new order", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return false;
    }

    public void deleteByID(int clientID, int audio_id) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(DELETE_ORDER_FROM_BASKET_BY_ID);
            preparedStatement.setInt(1, clientID);
            preparedStatement.setInt(2, audio_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException, deleting order by id", e);
            throw new ProjectException("SQLException, deleting order by id", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }
}
