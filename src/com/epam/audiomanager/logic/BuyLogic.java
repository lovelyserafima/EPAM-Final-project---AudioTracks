package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DaoManager;
import com.epam.audiomanager.database.dao.impl.BasketDaoImpl;
import com.epam.audiomanager.database.dao.impl.AudioTrackDaoImpl;
import com.epam.audiomanager.database.dao.impl.UserDaoImpl;
import com.epam.audiomanager.exception.ProjectException;

import java.math.BigDecimal;

public class BuyLogic {
    private BuyLogic(){}

    public static BigDecimal isEnoughMoney(BigDecimal clientMoney, int audioId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        UserDaoImpl userDAO = new UserDaoImpl();
        try {
            daoManager.startDAO(userDAO);
            return userDAO.isEnoughMoney(clientMoney, audioId);
        } finally {
            daoManager.endDAO();
        }
    }

    public static void buyAudioTrack(int clientId, int audioId, BigDecimal clientMoney, BigDecimal priceAudio)
            throws ProjectException {
        DaoManager daoManager =  new DaoManager();
        AudioTrackDaoImpl audioTrackDaoImpl = new AudioTrackDaoImpl();
        BasketDaoImpl basketDaoImpl = new BasketDaoImpl();
        UserDaoImpl userDAO = new UserDaoImpl();
        try {
            daoManager.startDAO(audioTrackDaoImpl, basketDaoImpl, userDAO);
            audioTrackDaoImpl.buyAudioTrack(clientId, audioId);
            userDAO.updateUserMoney(clientId, clientMoney, priceAudio);
            basketDaoImpl.deleteByClientIdAndAudioId(clientId, audioId);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }
}
