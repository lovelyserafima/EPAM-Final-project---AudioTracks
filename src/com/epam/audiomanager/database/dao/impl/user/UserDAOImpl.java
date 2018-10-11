package com.epam.audiomanager.database.dao.impl.user;

import com.epam.audiomanager.database.dao.AbstractDAO;
import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.entity.user.Client;
import com.epam.audiomanager.entity.user.TypeUser;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl extends AbstractDAO implements UserDAO {
    //database queries
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "select * from User where email = ? and password = ?";
    private static final String FIND_USER_BY_EMAIL = "select * from User where email = ?";
    private static final String FIND_USER_BY_LOGIN = "select * from User where login = ?";
    private static final String FIND_CLIENT_BY_ID = "select * from Client where user_id = ?";

    private static final String INSERT_USER = "insert into User(login, password, role, first_name, second_name, email) " +
            "values(?, ?, ?, ?, ?, ?)";
    private static final String INSERT_CLIENT = "insert into Client(bonus) values(?)";
    private static final String UPDATE_USER_BY_PASSWORD = "update User set password = ? where login = ?";
    private static final String UPDATE_USER_BY_PARAMETRES = "update User set email = ?, login = ?, first_name = ?, " +
            "second_name = ? where login = ?";
    private static final String FIND_AUDIO_PRICE = "select price from AudioTrack where id = ?";
    private static final String FIND_ALL = "select * from User";
    private static final String UPDATE_USER_MONEY = "update Client set money = ? where user_id = ?";

    public UserDAOImpl(){}

    @Override
    public User findUserByEmailAndPassword(String email, String password) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                if (resultSet.getString(4).toUpperCase().equals(ConstantAttributes.ADMIN)){
                    return createUser(resultSet);
                } else {
                    PreparedStatement preparedStatementForClient = connection.prepareStatement(FIND_CLIENT_BY_ID);
                    preparedStatementForClient.setInt(1, resultSet.getInt(1));
                    ResultSet resultSetForClient = preparedStatementForClient.executeQuery();
                    if (resultSetForClient.next()){
                        return createClient(resultSet, resultSetForClient);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ProjectException("SQLException", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return null;
    }

    @Override
    public boolean findUserByEmail(String email) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new ProjectException("Error with searching user by email", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return false;
    }

    @Override
    public boolean findUserByLogin(String login) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new ProjectException("Error with searching user by login", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return false;
    }

    @Override
    public boolean updateUserPassword(String login, String newPassword) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                preparedStatement = connection.prepareStatement(UPDATE_USER_BY_PASSWORD);
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, login);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new ProjectException("Error with updating password", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return false;
    }

    @Override
    public boolean updateUserParametres(User user, String oldLogin) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, oldLogin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                preparedStatement = connection.prepareStatement(UPDATE_USER_BY_PARAMETRES);
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.setString(3, user.getFirstName());
                preparedStatement.setString(4, user.getSecondName());
                preparedStatement.setString(5, oldLogin);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new ProjectException("Error with updating parametres", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return false;
    }

    @Override
    public boolean updateUserMoney(int clientID, BigDecimal clientMoney, BigDecimal price) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(UPDATE_USER_MONEY);
            preparedStatement.setBigDecimal(1,
                    BigDecimal.valueOf(clientMoney.doubleValue() - price.doubleValue()));
            preparedStatement.setInt(2, clientID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new ProjectException("SQLException, updating money");
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public BigDecimal isEnoughMoney(BigDecimal clientMoney, int audioID) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_AUDIO_PRICE);
            preparedStatement.setInt(1, audioID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                BigDecimal audioPrice = resultSet.getBigDecimal(1);
                return BigDecimal.valueOf(clientMoney.doubleValue() >= audioPrice.doubleValue() ?
                        audioPrice.doubleValue() : -1);

            }
        } catch (SQLException e) {
            throw new ProjectException("Error with buying track", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return BigDecimal.valueOf(-1);
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(1), resultSet.getString(2),
                TypeUser.valueOf(resultSet.getString(4).toUpperCase()),
                resultSet.getString(5), resultSet.getString(6),
                resultSet.getString(7));
    }

    private Client createClient(ResultSet resultSet, ResultSet resultSetForClient) throws SQLException {
        return new Client(resultSet.getInt(1), resultSet.getString(2),
                TypeUser.valueOf(resultSet.getString(4).toUpperCase()),
                resultSet.getString(5), resultSet.getString(6),
                resultSet.getString(7), resultSetForClient.getBoolean(2),
                resultSetForClient.getBigDecimal(3));
    }

    public void insert(User user, String encryptedPassword) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, encryptedPassword);
            preparedStatement.setString(3, String.valueOf(user.getType()));
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getSecondName());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.executeUpdate();
            if (user.getType() == TypeUser.CLIENT){
                PreparedStatement preparedStatementForClient = connection.prepareStatement(INSERT_CLIENT);
                preparedStatementForClient.setBoolean(1, ((Client) user).isBonus());
                preparedStatementForClient.executeUpdate();
            }
            preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.last()){
                user.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new ProjectException("Error with registration new client", e);
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
    public boolean findByID(int... id) throws ProjectException {
        return false;
    }

}
