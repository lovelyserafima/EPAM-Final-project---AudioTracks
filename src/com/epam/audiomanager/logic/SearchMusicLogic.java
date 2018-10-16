package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.AudioTrackDAOImpl;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;

import java.util.List;

public class SearchMusicLogic {
    public static List<AudioTrack> findAllTracks() throws ProjectException {
        List<AudioTrack> audioTracks;

        DAOManager daoManager = new DAOManager();
        AudioTrackDAOImpl audioTrackDAOImpl = new AudioTrackDAOImpl();
        try {
            daoManager.startDAO(audioTrackDAOImpl);
            audioTracks = audioTrackDAOImpl.findAll();
        } finally {
            daoManager.endDAO();
        }
        return audioTracks;
    }

    public static List<AudioTrack> findTracksByEntity(String entity) throws ProjectException {
        List<AudioTrack> audioTracks;

        DAOManager daoManager = new DAOManager();
        AudioTrackDAOImpl audioTrackDAOImpl = new AudioTrackDAOImpl();
        try{
            daoManager.startDAO(audioTrackDAOImpl);
            audioTracks = audioTrackDAOImpl.findAudioTracksBySmth(entity);
        } finally {
            daoManager.endDAO();
        }
        return audioTracks;
    }
}
