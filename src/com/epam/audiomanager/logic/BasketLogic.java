package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DaoManager;
import com.epam.audiomanager.database.dao.impl.BasketDaoImpl;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import java.util.List;

public class BasketLogic {
    public static boolean insertOrder(int clientId, int audioTrackId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        BasketDaoImpl basketDaoImpl = new BasketDaoImpl();
        try {
            daoManager.startDAO(basketDaoImpl);
            basketDaoImpl.insertOrder(clientId, audioTrackId);
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

        DaoManager daoManager = new DaoManager();
        BasketDaoImpl basketDaoImpl = new BasketDaoImpl();
        try{
            daoManager.startDAO(basketDaoImpl);
            audioTracks = basketDaoImpl.findAll(id);
        } finally {
            daoManager.endDAO();
        }
        return audioTracks;
    }

    public static List<User> findAllUserWantingToBuyTracks(int audioId) throws ProjectException {
        List<User> users;

        DaoManager daoManager = new DaoManager();
        BasketDaoImpl basketDaoImpl = new BasketDaoImpl();
        try{
            daoManager.startDAO(basketDaoImpl);
            users = basketDaoImpl.findUsersWantingToBuy(audioId);
            return users;
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean isOrderInBasket(int clientId, int audioTrackId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        BasketDaoImpl basketDaoImpl = new BasketDaoImpl();
        try {
            daoManager.startDAO(basketDaoImpl);
            return basketDaoImpl.findByIds(clientId, audioTrackId);
        } finally {
            daoManager.endDAO();
        }
    }

    public static void cancelOrder(int clientId, int audioTrackId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        BasketDaoImpl basketDaoImpl = new BasketDaoImpl();
        try{
            daoManager.startDAO(basketDaoImpl);
            basketDaoImpl.deleteByClientIdAndAudioId(clientId, audioTrackId);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }

    public static void resumeOrder(int clientId, int audioTrackId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        BasketDaoImpl basketDaoImpl = new BasketDaoImpl();
        try{
            daoManager.startDAO(basketDaoImpl);
            basketDaoImpl.resumeOrder(clientId, audioTrackId);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean wasOrderInBasket(int clientId, int audioId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        BasketDaoImpl basketDaoImpl = new BasketDaoImpl();
        try{
            daoManager.startDAO(basketDaoImpl);
            return basketDaoImpl.findCanceledOrders(clientId, audioId);
        } finally {
            daoManager.endDAO();
        }
    }
}
