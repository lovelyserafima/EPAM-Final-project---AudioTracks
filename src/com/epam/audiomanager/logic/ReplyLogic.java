package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.ReplyDAO;
import com.epam.audiomanager.exception.ProjectException;

public class ReplyLogic {
    public static boolean isFeedbackExists(int user_id, int audio_id) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        ReplyDAO replyDAO = new ReplyDAO();
        try {
            daoManager.startDAO(replyDAO);
            return replyDAO.findByID(user_id, audio_id);
        } finally {
            daoManager.endDAO();
        }
    }

    public static void sendReply(int user_id, int audio_id, String text) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        ReplyDAO replyDAO = new ReplyDAO();
        try{
            daoManager.startDAO(replyDAO);
            replyDAO.insertReply(user_id, audio_id, text);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }
}
