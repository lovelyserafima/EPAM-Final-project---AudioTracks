package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DaoManager;
import com.epam.audiomanager.database.dao.impl.UserDaoImpl;
import com.epam.audiomanager.entity.user.Client;
import com.epam.audiomanager.exception.ProjectException;

import java.math.BigDecimal;

public class ChangeParametresLogic {
    public static boolean changeParametresLogic(Client user, String oldLogin) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        UserDaoImpl userDAO = new UserDaoImpl();
        boolean flag = false;
        try {
            daoManager.startDAO(userDAO);
            if (userDAO.updateUserParametres(user, oldLogin)) {
                flag = true;
            }
            daoManager.commit();
        } catch (ProjectException e){
            daoManager.rollback();
            throw e;
        } finally{
            daoManager.endDAO();
        }
        return flag;
    }

    public static boolean changePassword(String login, String newPassword) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        UserDaoImpl userDAO = new UserDaoImpl();
        boolean flag = false;
        try {
            daoManager.startDAO(userDAO);
            if (userDAO.updateUserPassword(login, newPassword)) {
                flag = true;
            }
            daoManager.commit();
        } catch (ProjectException e){
            daoManager.rollback();
            throw e;
        } finally{
            daoManager.endDAO();
        }
        return flag;
    }

    public static void topUpAccount(int clientID, BigDecimal clientMoney, BigDecimal changes) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        UserDaoImpl userDAO = new UserDaoImpl();
        try{
            daoManager.startDAO(userDAO);
            userDAO.updateUserMoney(clientID, clientMoney, changes);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }
}
