package com.epam.audiomanager.logic.registration;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.UserDAOImpl;
import com.epam.audiomanager.exception.ProjectException;

public class EmailLogic {
    public static boolean isEmailExists(String email) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            daoManager.startDAO(userDAO);
            return userDAO.findUserByEmail(email);
        } finally {
            daoManager.endDAO();
        }
    }
}
