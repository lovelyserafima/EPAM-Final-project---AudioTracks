package com.epam.audiomanager.logic.registration;

import com.epam.audiomanager.database.dao.DaoManager;
import com.epam.audiomanager.database.dao.impl.UserDaoImpl;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterLogic {
    private static final Logger LOGGER = LogManager.getLogger(RegisterLogic.class);

    public static void registerNewClient(User user, String encryptedPassword) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        UserDaoImpl userDAO = new UserDaoImpl();

        try {
            daoManager.startDAO(userDAO);
            userDAO.insert(user, encryptedPassword);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;//why?
        } finally {
            daoManager.endDAO();
        }
    }
}
