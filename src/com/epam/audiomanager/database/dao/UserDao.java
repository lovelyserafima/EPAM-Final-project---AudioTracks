package com.epam.audiomanager.database.dao;

import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import java.math.BigDecimal;

public interface UserDao {
    User findUserByEmailAndPassword(String email, String password) throws ProjectException;
    boolean findUserByEmail(String email) throws ProjectException;
    boolean findUserByLogin(String login) throws ProjectException;
    boolean updateUserPassword(String login, String newPassword) throws ProjectException;
    boolean updateUserParametres(User user, String oldLogin) throws ProjectException;
    void updateUserMoney(int clientID, BigDecimal price, BigDecimal clientMoney) throws ProjectException;
    BigDecimal isEnoughMoney(BigDecimal clientMonte, int audioID) throws ProjectException;
}
