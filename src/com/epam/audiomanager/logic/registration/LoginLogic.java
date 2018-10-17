package com.epam.audiomanager.logic.registration;

import com.epam.audiomanager.database.dao.DaoManager;
import com.epam.audiomanager.database.dao.impl.UserDaoImpl;
import com.epam.audiomanager.exception.ProjectException;

public class LoginLogic {
    private LoginLogic(){}

    public static boolean isLoginExists(String login) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        UserDaoImpl userDAO = new UserDaoImpl();
        try {
            daoManager.startDAO(userDAO);
            return userDAO.findUserByLogin(login);
        } finally {
            daoManager.endDAO();
        }
    }
}
