package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DaoManager;
import com.epam.audiomanager.database.dao.impl.AudioTrackDaoImpl;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;

import java.util.List;

public class SearchMusicLogic {
    private SearchMusicLogic(){}

    public static List<AudioTrack> findAllTracks() throws ProjectException {
        List<AudioTrack> audioTracks;

        DaoManager daoManager = new DaoManager();
        AudioTrackDaoImpl audioTrackDaoImpl = new AudioTrackDaoImpl();
        try {
            daoManager.startDAO(audioTrackDaoImpl);
            audioTracks = audioTrackDaoImpl.findAll();
        } finally {
            daoManager.endDAO();
        }
        return audioTracks;
    }

    public static List<AudioTrack> findTracksByEntity(String entity) throws ProjectException {
        List<AudioTrack> audioTracks;

        DaoManager daoManager = new DaoManager();
        AudioTrackDaoImpl audioTrackDaoImpl = new AudioTrackDaoImpl();
        try{
            daoManager.startDAO(audioTrackDaoImpl);
            audioTracks = audioTrackDaoImpl.findAudioTracksBySmth(entity);
        } finally {
            daoManager.endDAO();
        }
        return audioTracks;
    }
}
