package com.epam.audiomanager.database.dao.impl;

import com.epam.audiomanager.database.dao.AbstractDAO;
import com.epam.audiomanager.database.dao.AlbumDAO;
import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.exception.ProjectException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AlbumDAOImpl extends AbstractDAO implements AlbumDAO {
    private static final String FIND_ALBUM_BY_NAME_AND_BAND = "select id from Album where name = ? and artist = ?";
    private static final String FIND_ALBUM_BY_ALL_FIELDS = "select id from Album where name = ? and artist = ? and " +
            "year = ?";
    private static final String INSERT_ALBUM = "insert into Album(name, artist, year, price) values(?,?,?,?)";

    @Override
    public Integer findByNameAndBand(String name, String band) throws ProjectException {
        PreparedStatement preparedStatement = null;
        Integer id = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_ALBUM_BY_NAME_AND_BAND);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, band);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new ProjectException("SQLException, searching album by name and band");
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return id;
    }

    @Override
    public boolean findAlbumByAllFields(String name, String band, int year) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_ALBUM_BY_ALL_FIELDS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, band);
            preparedStatement.setInt(3, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, find album by all fields");
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public void insertAlbum(String name, String band, int year, BigDecimal price) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_ALBUM);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, band);
            preparedStatement.setInt(3, year);
            preparedStatement.setBigDecimal(4, price);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, inserting new album");
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public List findAll() throws ProjectException {
        return null;
    }

    @Override
    public boolean findByIds(int... id) throws ProjectException {
        return false;
    }
}
