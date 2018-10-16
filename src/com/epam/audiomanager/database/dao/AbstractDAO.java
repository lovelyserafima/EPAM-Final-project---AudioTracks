package com.epam.audiomanager.database.dao;

import com.epam.audiomanager.database.pool.ProxyConnection;
import com.epam.audiomanager.entity.Entity;
import com.epam.audiomanager.exception.ProjectException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO <T extends Entity> {
    protected ProxyConnection connection;
    protected static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    public abstract List<T> findAll() throws ProjectException;
    public abstract boolean findByIds(int... id) throws ProjectException;

    public void setConnection(ProxyConnection connection){
        this.connection = connection;
    }

    public void close(Statement statement) throws ProjectException {
        try{
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            throw new ProjectException("CloseStatementException!", e);
        }
    }
}

