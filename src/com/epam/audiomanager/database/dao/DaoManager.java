package com.epam.audiomanager.database.dao;

import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.database.pool.ProxyConnection;
import com.epam.audiomanager.exception.ProjectException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoManager {
    private static final Logger LOGGER = LogManager.getLogger(DaoManager.class);
    private ProxyConnection connection;
    private List<AbstractDao> abstractDaos;

    public DaoManager(){
        abstractDaos = new ArrayList<>();
    }

    public void startDAO(AbstractDao abstractDao, AbstractDao... abstractDaos) throws ProjectException {
        if (connection == null){
            connection = ConnectionPool.getInstance().getConnection();
        }
        try{
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ProjectException("SQLException", e);
        }
        abstractDao.setConnection(connection);
        this.abstractDaos.add(abstractDao);
        for (AbstractDao currentDao: abstractDaos){
            currentDao.setConnection(connection);
            this.abstractDaos.add(currentDao);
        }
    }

    public void endDAO(){
        for (AbstractDao currentDao: abstractDaos){
            currentDao.setConnection(null);
        }
        abstractDaos.clear();
        if (connection != null){
            ConnectionPool.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    public void commit() throws ProjectException {
        if (connection != null){
            try{
                connection.commit();
            } catch (SQLException e) {
                LOGGER.error("SQLException", e);
                throw new ProjectException("SQLException", e);
            }
        }
    }

    public void rollback() throws ProjectException {
        if (connection != null){
            try{
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("SQLException", e);
                throw new ProjectException("SQLException", e);
            }
        }
    }
}
