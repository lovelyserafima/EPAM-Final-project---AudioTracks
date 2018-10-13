package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.audio.AudioTrackDAO;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;

public class AudiLogic {
    public static AudioTrack findAudioTrack(int audioId) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
        try{
            daoManager.startDAO(audioTrackDAO);
            AudioTrack audioTrack = audioTrackDAO.findByAudioId(audioId);
            return audioTrack;
        } finally {
            daoManager.endDAO();
        }
    }
}
