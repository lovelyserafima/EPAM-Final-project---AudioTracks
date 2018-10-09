/*
package com.epam.audiomanager.database.dao.impl;

import com.epam.audiomanager.database.dao.AbstractDAO;
import com.epam.audiomanager.entity.Entity;
import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.database.pool.ProxyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AssemblyDAO extends AbstractDAO {
    private static final String SHOW_ALL_INFORMATION = "select * from Assembly";

    public AssemblyDAO(ProxyConnection pool) {

    }

    @Override
    public void findAll() {
        Statement statement = null;
        try{
            statement = pool.createStatement();
            ResultSet resultSet = statement.executeQuery(SHOW_ALL_INFORMATION);
            while(resultSet.next()){
                LOGGER.info(resultSet.getInt(1) + " " + resultSet.getString(2) + " "
                        + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pool != null){
                close(statement);
                ConnectionPool.getInstance().releaseConnection(pool);
            }
        }
    }

    @Override
    public Entity findEntityById(int id) {
        return null;
    }

    @Override
    public void insert(Object... objects) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(String name, int id) {

    }
}
*/
