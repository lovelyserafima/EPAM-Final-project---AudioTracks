package com.epam.audiomanager.database.pool;

import com.epam.audiomanager.exception.ProjectException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool{
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private BlockingQueue<ProxyConnection> connectionQueue;
    private final int DEFAULT_POOL_SIZE = 10;
    private static AtomicBoolean instanceCreated  = new AtomicBoolean();
    private static ConnectionPool instance;
    private static ReentrantLock lock = new ReentrantLock();

    private ConnectionPool(){
        connectionQueue = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++){
            Connection connection = ConnectorDb.getConnection();
            try {
                connectionQueue.put(new ProxyConnection(connection));
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException, creating constructor");
                Thread.currentThread().interrupt();
            }
        }
    }

    public static ConnectionPool getInstance(){
        if (!instanceCreated.get()){
            lock.lock();
            try{
                if (instance == null){
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() throws ProjectException {
        ProxyConnection connection;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException", e);
            throw new ProjectException("InterruptedException", e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        try {
            connection.setAutoCommit(true);
            connectionQueue.put(connection);
        } catch (InterruptedException | SQLException e) {
            LOGGER.error("Interrupted or SQLException in connection pool", e);
        }
    }

    public void closePool() {
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                connectionQueue.take().closeConnection();
            }
            derigesterDrivers();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e);
        } catch (InterruptedException e){
            LOGGER.error("InterruptedException", e);
        }
    }

    private void derigesterDrivers() throws SQLException {
        Enumeration<Driver> driverEnumeration = DriverManager.getDrivers();
        while (driverEnumeration.hasMoreElements()){
            Driver driver = driverEnumeration.nextElement();
            DriverManager.deregisterDriver(driver);
        }
    }
}
