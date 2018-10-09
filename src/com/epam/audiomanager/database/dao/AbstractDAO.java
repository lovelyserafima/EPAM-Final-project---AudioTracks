package com.epam.audiomanager.database.dao;

import com.epam.audiomanager.entity.Entity;
import com.epam.audiomanager.exception.ProjectException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO <K, T extends Entity> {
    protected Connection connection;
    protected static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    public abstract List<T> findAll() throws ProjectException;
    public abstract boolean findByID(int... id) throws ProjectException;

    public void setConnection(Connection connection){
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

