package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DaoManager;
import com.epam.audiomanager.database.dao.impl.MediaLibraryDaoImpl;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;

import java.util.List;

public class MediaLibraryLogic {
    public static List<AudioTrack> findAllUserTracks(int id) throws ProjectException {
        List<AudioTrack> audioTracks;

        DaoManager daoManager = new DaoManager();
        MediaLibraryDaoImpl mediaLibraryDaoImplImpl = new MediaLibraryDaoImpl();
        try{
            daoManager.startDAO(mediaLibraryDaoImplImpl);
            audioTracks = mediaLibraryDaoImplImpl.findAll(id);
        } finally {
            daoManager.endDAO();
        }
        return audioTracks;
    }

    public static List<User> findAllUserHavingTracks(int audioId) throws ProjectException {
        List<User> users;

        DaoManager daoManager = new DaoManager();
        MediaLibraryDaoImpl mediaLibraryDaoImplImpl = new MediaLibraryDaoImpl();
        try{
            daoManager.startDAO(mediaLibraryDaoImplImpl);
            users = mediaLibraryDaoImplImpl.findUsersHavingTrack(audioId);
            return users;
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean isTrackInMediaLibrary(int clientID, int audioID) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        MediaLibraryDaoImpl mediaLibraryDaoImplImpl = new MediaLibraryDaoImpl();
        try{
            daoManager.startDAO(mediaLibraryDaoImplImpl);
            return mediaLibraryDaoImplImpl.findByIds(clientID, audioID);
        } finally {
            daoManager.endDAO();
        }
    }
}
