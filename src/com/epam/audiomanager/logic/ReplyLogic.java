package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.ReplyDAOImpl;
import com.epam.audiomanager.entity.audio.Reply;
import com.epam.audiomanager.exception.ProjectException;
import java.util.List;

public class ReplyLogic {
    public static boolean isFeedbackExists(int user_id, int audio_id) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        ReplyDAOImpl replyDAOImpl = new ReplyDAOImpl();
        try {
            daoManager.startDAO(replyDAOImpl);
            return replyDAOImpl.findByIds(user_id, audio_id);
        } finally {
            daoManager.endDAO();
        }
    }

    public static void sendReply(int user_id, int audio_id, String text) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        ReplyDAOImpl replyDAOImpl = new ReplyDAOImpl();
        try{
            daoManager.startDAO(replyDAOImpl);
            replyDAOImpl.insertReply(user_id, audio_id, text);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }

    public static List<Reply> findReplies(int audioId) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        ReplyDAOImpl replyDAOImpl = new ReplyDAOImpl();
        try{
            daoManager.startDAO(replyDAOImpl);
            return replyDAOImpl.findRepliesByAudioId(audioId);
        } finally {
            daoManager.endDAO();
        }
    }
}
