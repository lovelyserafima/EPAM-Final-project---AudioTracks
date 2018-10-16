package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.MediaLibraryDAOImpl;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;

import java.util.List;

public class MediaLibraryLogic {
    public static List<AudioTrack> findAllUserTracks(int id) throws ProjectException {
        List<AudioTrack> audioTracks;

        DAOManager daoManager = new DAOManager();
        MediaLibraryDAOImpl mediaLibraryDAOImplImpl = new MediaLibraryDAOImpl();
        try{
            daoManager.startDAO(mediaLibraryDAOImplImpl);
            audioTracks = mediaLibraryDAOImplImpl.findAll(id);
        } finally {
            daoManager.endDAO();
        }
        return audioTracks;
    }

    public static List<User> findAllUserHavingTracks(int audioId) throws ProjectException {
        List<User> users;

        DAOManager daoManager = new DAOManager();
        MediaLibraryDAOImpl mediaLibraryDAOImplImpl = new MediaLibraryDAOImpl();
        try{
            daoManager.startDAO(mediaLibraryDAOImplImpl);
            users = mediaLibraryDAOImplImpl.findUsersHavingTrack(audioId);
            return users;
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean isTrackInMediaLibrary(int clientID, int audioID) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        MediaLibraryDAOImpl mediaLibraryDAOImplImpl = new MediaLibraryDAOImpl();
        try{
            daoManager.startDAO(mediaLibraryDAOImplImpl);
            return mediaLibraryDAOImplImpl.findByIds(clientID, audioID);
        } finally {
            daoManager.endDAO();
        }
    }
}
