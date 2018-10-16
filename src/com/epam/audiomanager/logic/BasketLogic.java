package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.BasketDAOImpl;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import java.util.List;

public class BasketLogic {
    public static boolean insertOrder(int clientId, int audioTrackId) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        BasketDAOImpl basketDAOImpl = new BasketDAOImpl();
        try {
            daoManager.startDAO(basketDAOImpl);
            basketDAOImpl.insertOrder(clientId, audioTrackId);
            daoManager.commit();
            return true;
        } catch (ProjectException e){
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }

    public static List<AudioTrack> findAllOrders(int id) throws ProjectException {
        List<AudioTrack> audioTracks;

        DAOManager daoManager = new DAOManager();
        BasketDAOImpl basketDAOImpl = new BasketDAOImpl();
        try{
            daoManager.startDAO(basketDAOImpl);
            audioTracks = basketDAOImpl.findAll(id);
        } finally {
            daoManager.endDAO();
        }
        return audioTracks;
    }

    public static List<User> findAllUserWantingToBuyTracks(int audioId) throws ProjectException {
        List<User> users;

        DAOManager daoManager = new DAOManager();
        BasketDAOImpl basketDAOImpl = new BasketDAOImpl();
        try{
            daoManager.startDAO(basketDAOImpl);
            users = basketDAOImpl.findUsersWantingToBuy(audioId);
            return users;
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean isOrderInBasket(int clientId, int audioTrackId) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        BasketDAOImpl basketDAOImpl = new BasketDAOImpl();
        try {
            daoManager.startDAO(basketDAOImpl);
            return basketDAOImpl.findByIds(clientId, audioTrackId);
        } finally {
            daoManager.endDAO();
        }
    }

    public static void cancelOrder(int clientId, int audioTrackId) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        BasketDAOImpl basketDAOImpl = new BasketDAOImpl();
        try{
            daoManager.startDAO(basketDAOImpl);
            basketDAOImpl.deleteByClientIdAndAudioId(clientId, audioTrackId);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }

    public static void resumeOrder(int clientId, int audioTrackId) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        BasketDAOImpl basketDAOImpl = new BasketDAOImpl();
        try{
            daoManager.startDAO(basketDAOImpl);
            basketDAOImpl.resumeOrder(clientId, audioTrackId);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean wasOrderInBasket(int clientId, int audioId) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        BasketDAOImpl basketDAOImpl = new BasketDAOImpl();
        try{
            daoManager.startDAO(basketDAOImpl);
            return basketDAOImpl.findCanceledOrders(clientId, audioId);
        } finally {
            daoManager.endDAO();
        }
    }
}
