package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.MediaLibraryDAO;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.entity.user.Client;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;

import java.util.List;

public class MediaLibraryLogic {
    public static List<AudioTrack> findAllUserTracks(int id) throws ProjectException {
        List<AudioTrack> audioTracks;

        DAOManager daoManager = new DAOManager();
        MediaLibraryDAO mediaLibraryDAO = new MediaLibraryDAO();
        try{
            daoManager.startDAO(mediaLibraryDAO);
            audioTracks = mediaLibraryDAO.findAll(id);
        } finally {
            daoManager.endDAO();
        }
        return audioTracks;
    }

    public static List<User> findAllUserHavingTracks(int audioId) throws ProjectException {
        List<User> users;

        DAOManager daoManager = new DAOManager();
        MediaLibraryDAO mediaLibraryDAO = new MediaLibraryDAO();
        try{
            daoManager.startDAO(mediaLibraryDAO);
            users = mediaLibraryDAO.findUsersHavingTrack(audioId);
            return users;
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean isTrackInMediaLibrary(int clientID, int audioID) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        MediaLibraryDAO mediaLibraryDAO = new MediaLibraryDAO();
        try{
            daoManager.startDAO(mediaLibraryDAO);
            return mediaLibraryDAO.findByID(clientID, audioID);
        } finally {
            daoManager.endDAO();
        }
    }
}
