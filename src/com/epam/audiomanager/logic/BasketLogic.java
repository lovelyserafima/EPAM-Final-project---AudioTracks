package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.BasketDAO;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;
import java.util.List;

public class BasketLogic {
    public static boolean insertOrder(int clientId, int audioTrackId) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        BasketDAO basketDAO = new BasketDAO();
        try {
            daoManager.startDAO(basketDAO);
            basketDAO.insertOrder(clientId, audioTrackId);
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
        BasketDAO basketDAO = new BasketDAO();
        try{
            daoManager.startDAO(basketDAO);
            audioTracks = basketDAO.findAll(id);
        } finally {
            daoManager.endDAO();
        }
        return audioTracks;
    }

    public static boolean isOrderInBasket(int clientId, int audioTrackId) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        BasketDAO basketDAO = new BasketDAO();
        try {
            daoManager.startDAO(basketDAO);
            return basketDAO.findByID(clientId, audioTrackId);
        } finally {
            daoManager.endDAO();
        }
    }
}
