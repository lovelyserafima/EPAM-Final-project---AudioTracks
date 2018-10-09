package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.BasketDAO;
import com.epam.audiomanager.database.dao.impl.audio.AudioTrackDAO;
import com.epam.audiomanager.database.dao.impl.user.UserDAOImpl;
import com.epam.audiomanager.exception.ProjectException;

import java.math.BigDecimal;

public class BuyLogic {
    public static BigDecimal isEnoughMoney(BigDecimal clientMoney, int audioId) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            daoManager.startDAO(userDAO);
            return userDAO.isEnoughMoney(clientMoney, audioId);
        } finally {
            daoManager.endDAO();
        }
    }

    public static void buyAudioTrack(int clientId, int audioId, BigDecimal clientMoney, BigDecimal priceAudio)
            throws ProjectException {
        DAOManager daoManager =  new DAOManager();
        AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
        BasketDAO basketDAO = new BasketDAO();
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            daoManager.startDAO(audioTrackDAO, basketDAO, userDAO);
            audioTrackDAO.buyAudioTrack(clientId, audioId);
            userDAO.updateUserMoney(clientId, clientMoney, priceAudio);
            basketDAO.deleteByID(clientId, audioId);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }
}
