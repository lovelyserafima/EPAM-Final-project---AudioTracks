package com.epam.audiomanager.database.dao.impl;

import com.epam.audiomanager.database.dao.AbstractDAO;
import com.epam.audiomanager.entity.Entity;
import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.database.pool.ProxyConnection;
import com.epam.audiomanager.exception.ProjectException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AlbumDAO extends AbstractDAO {
    private static final String FIND_ALBUM_BY_NAME_AND_BAND = "select id from Album where name = ? and artist = ?";
    @Override
    public List findAll() throws ProjectException {
        return null;
    }

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
    public boolean findByID(int... id) throws ProjectException {
        return false;
    }
}
