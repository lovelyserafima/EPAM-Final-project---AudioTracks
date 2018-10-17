package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DaoManager;
import com.epam.audiomanager.database.dao.impl.ReplyDaoImpl;
import com.epam.audiomanager.entity.audio.Reply;
import com.epam.audiomanager.exception.ProjectException;
import java.util.List;

public class ReplyLogic {
    private ReplyLogic(){}

    public static boolean isFeedbackExists(int user_id, int audio_id) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        ReplyDaoImpl replyDaoImpl = new ReplyDaoImpl();
        try {
            daoManager.startDAO(replyDaoImpl);
            return replyDaoImpl.findByIds(user_id, audio_id);
        } finally {
            daoManager.endDAO();
        }
    }

    public static void sendReply(int user_id, int audio_id, String text) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        ReplyDaoImpl replyDaoImpl = new ReplyDaoImpl();
        try{
            daoManager.startDAO(replyDaoImpl);
            replyDaoImpl.insertReply(user_id, audio_id, text);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }

    public static List<Reply> findReplies(int audioId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        ReplyDaoImpl replyDaoImpl = new ReplyDaoImpl();
        try{
            daoManager.startDAO(replyDaoImpl);
            return replyDaoImpl.findRepliesByAudioId(audioId);
        } finally {
            daoManager.endDAO();
        }
    }
}
