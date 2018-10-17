package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DaoManager;
import com.epam.audiomanager.database.dao.impl.UserDaoImpl;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.util.Encryption;

public class SignInLogic {
    private SignInLogic(){}

    public static User isUserExists(String email, String password) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        UserDaoImpl userDAO = new UserDaoImpl();
        password = Encryption.encryptPassword(password);

        try {
            daoManager.startDAO(userDAO);
            return userDAO.findUserByEmailAndPassword(email, password);
        } finally {
            daoManager.endDAO();
        }
    }
}
