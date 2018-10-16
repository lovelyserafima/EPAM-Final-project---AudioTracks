package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.UserDAOImpl;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.util.Encryption;

public class SignInLogic {
    public static User isUserExists(String email, String password) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        UserDAOImpl userDAO = new UserDAOImpl();
        password = Encryption.encryptPassword(password);

        try {
            daoManager.startDAO(userDAO);
            return userDAO.findUserByEmailAndPassword(email, password);
        } finally {
            daoManager.endDAO();
        }
    }
}
