package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.AlbumDAOImpl;
import com.epam.audiomanager.database.dao.impl.AudioTrackDAOImpl;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;

import java.math.BigDecimal;

public class AudioLogic {
    public static AudioTrack findAudioTrack(int audioId) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        AudioTrackDAOImpl audioTrackDAOImpl = new AudioTrackDAOImpl();
        try{
            daoManager.startDAO(audioTrackDAOImpl);
            AudioTrack audioTrack = audioTrackDAOImpl.findByAudioId(audioId);
            return audioTrack;
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean isAudioTrackExists(String name, String band) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        AudioTrackDAOImpl audioTrackDAOImpl = new AudioTrackDAOImpl();
        try{
            daoManager.startDAO(audioTrackDAOImpl);
            return audioTrackDAOImpl.findByNameAndBand(name, band);
        } finally {
            daoManager.endDAO();
        }
    }

    public static Integer isAlbumExists(String album, String band) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        AlbumDAOImpl albumDAOImpl = new AlbumDAOImpl();
        try{
            daoManager.startDAO(albumDAOImpl);
            return albumDAOImpl.findByNameAndBand(album, band);
        } finally {
            daoManager.endDAO();
        }
    }

    public static void updateAudioTrack(int audioId, Integer albumId, String name, String band, int year,
                                        BigDecimal price, String demoAudioPath,
                                        String fullAudioPath) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        AudioTrackDAOImpl audioTrackDAOImpl = new AudioTrackDAOImpl();
        try{
            daoManager.startDAO(audioTrackDAOImpl);
            audioTrackDAOImpl.update(audioId, albumId, name, band, year, price, demoAudioPath, fullAudioPath);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }

    public static void deleteAudioTrack(int audioId) throws ProjectException {
        DAOManager daoManager =  new DAOManager();
        AudioTrackDAOImpl audioTrackDAOImpl = new AudioTrackDAOImpl();
        try{
            daoManager.startDAO(audioTrackDAOImpl);
            audioTrackDAOImpl.deleteById(audioId);
            daoManager.commit();
        } catch (ProjectException e){
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }

    public static void insertAudioTrack(int album_id, String name, String artist, int year, BigDecimal price,
                                        String full_audio_path, String demo_audio_path) throws ProjectException {
       DAOManager daoManager = new DAOManager();
       AudioTrackDAOImpl audioTrackDAOImpl = new AudioTrackDAOImpl();
       try{
           daoManager.startDAO(audioTrackDAOImpl);
           audioTrackDAOImpl.insert(album_id, name, artist, year, price, full_audio_path, demo_audio_path);
           daoManager.commit();
       } catch (ProjectException e) {
           daoManager.rollback();
           throw e;
       } finally {
           daoManager.endDAO();
       }
    }
}
