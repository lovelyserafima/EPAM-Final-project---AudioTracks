package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.BasketDAOImpl;
import com.epam.audiomanager.database.dao.impl.AudioTrackDAOImpl;
import com.epam.audiomanager.database.dao.impl.UserDAOImpl;
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
        AudioTrackDAOImpl audioTrackDAOImpl = new AudioTrackDAOImpl();
        BasketDAOImpl basketDAOImpl = new BasketDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            daoManager.startDAO(audioTrackDAOImpl, basketDAOImpl, userDAO);
            audioTrackDAOImpl.buyAudioTrack(clientId, audioId);
            userDAO.updateUserMoney(clientId, clientMoney, priceAudio);
            basketDAOImpl.deleteByClientIdAndAudioId(clientId, audioId);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }
}
