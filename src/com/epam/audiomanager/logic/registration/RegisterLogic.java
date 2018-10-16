package com.epam.audiomanager.logic.registration;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.UserDAOImpl;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterLogic {
    private static final Logger LOGGER = LogManager.getLogger(RegisterLogic.class);

    public static void registerNewClient(User user, String encryptedPassword) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        UserDAOImpl userDAO = new UserDAOImpl();

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
