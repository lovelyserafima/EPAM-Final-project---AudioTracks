package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.AlbumDAO;
import com.epam.audiomanager.database.dao.impl.audio.AudioTrackDAO;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;
import java.math.BigDecimal;

public class AudioLogic {
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

    public static Integer isAlbumExists(String album, String band) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        AlbumDAO albumDAO = new AlbumDAO();
        try{
            daoManager.startDAO(albumDAO);
            return albumDAO.findByNameAndBand(album, band);
        } finally {
            daoManager.endDAO();
        }
    }

    public static void updateAudioTrack(int audioId, Integer albumId, String name, String band, int year,
                                        BigDecimal price, String demoAudioPath,
                                        String fullAudioPath) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
        try{
            daoManager.startDAO(audioTrackDAO);
            audioTrackDAO.update(audioId, albumId, name, band, year, price, demoAudioPath, fullAudioPath);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }
}
