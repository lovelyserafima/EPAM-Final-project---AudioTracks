package com.epam.audiomanager.database.dao.impl;

import com.epam.audiomanager.database.dao.AbstractDao;
import com.epam.audiomanager.database.dao.BasketDao;
import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketDaoImpl extends AbstractDao implements BasketDao {
    private static final String FIND_ORDER_BY_ID_AND_AUDIO_ID = "select user_id, audiotrack_id from Basket where user_id = " +
            "? and audiotrack_id  = ? and available = 1";
    private static final String INSERT_ORDER = "insert into Basket(audiotrack_id, user_id) values(?, ?)";
    private static final String FIND_ALL_ORDERS_OF_CLIENTS = "select AudioTrack.id, AudioTrack.name, AudioTrack.artist, " +
            "AudioTrack.year, AudioTrack.price, full_audio_path, demo_audio_path, Album.name from User join Basket on " +
            "User.id = Basket.user_id join AudioTrack on Basket.audiotrack_id = AudioTrack.id left join Album on " +
            "AudioTrack.album_id = Album.id where User.id = ? and Basket.available = 1 and AudioTrack.available = 1";
    private static final String DELETE_ORDER_FROM_BASKET_BY_ID = "update Basket set available = 0 " +
            "where user_id = ? and audiotrack_id = ?";
    private static final String FIND_ALL_USERS_WANTING_TO_BUY_TRACK = "select id, login from User join Basket " +
            "on User.id = Basket.user_id where audiotrack_id = ? and available = 1";
    private static final String FIND_CANCELED_ORDERS = "select user_id from Basket where user_id = ? and " +
            "audiotrack_id = ? and available = 0";
    private static final String RESUME_ORDER = "update Basket set available = 1 where user_id = ? and audiotrack_id = ?";

    @Override
    public void insertOrder(int clientId, int audioId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setInt(2, clientId);
            preparedStatement.setInt(1, audioId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectException("Error with inserting new order", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
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
    public boolean findByIds(int... id) throws ProjectException {
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

    @Override
    public void deleteByClientIdAndAudioId(int clientID, int audio_id) throws ProjectException {
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

    @Override
    public boolean findCanceledOrders(int clientId, int audioId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_CANCELED_ORDERS);
            preparedStatement.setInt(1, clientId);
            preparedStatement.setInt(2, audioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, find canceled orders", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public void resumeOrder(int clientId, int audioId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(RESUME_ORDER);
            preparedStatement.setInt(1, clientId);
            preparedStatement.setInt(2, audioId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, resume order", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public List<User> findUsersWantingToBuy(int audioId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        List<User> users = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(FIND_ALL_USERS_WANTING_TO_BUY_TRACK);
            preparedStatement.setInt(1, audioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(new User(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            throw new ProjectException("SQLException, searching users wanting to buy", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return users;
    }
}
