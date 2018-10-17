package com.epam.audiomanager.logic.registration;

import com.epam.audiomanager.database.dao.DaoManager;
import com.epam.audiomanager.database.dao.impl.UserDaoImpl;
import com.epam.audiomanager.exception.ProjectException;

public class EmailLogic {
    private EmailLogic(){}

    public static boolean isEmailExists(String email) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        UserDaoImpl userDAO = new UserDaoImpl();
        try {
            daoManager.startDAO(userDAO);
            return userDAO.findUserByEmail(email);
        } finally {
            daoManager.endDAO();
        }
    }
}
