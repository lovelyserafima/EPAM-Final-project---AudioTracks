package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.UserDAOImpl;
import com.epam.audiomanager.entity.user.Client;
import com.epam.audiomanager.exception.ProjectException;

import java.math.BigDecimal;

public class ChangeParametresLogic {
    public static boolean changeParametresLogic(Client user, String oldLogin) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        UserDAOImpl userDAO = new UserDAOImpl();
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
        DAOManager daoManager = new DAOManager();
        UserDAOImpl userDAO = new UserDAOImpl();
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
        DAOManager daoManager = new DAOManager();
        UserDAOImpl userDAO = new UserDAOImpl();
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
