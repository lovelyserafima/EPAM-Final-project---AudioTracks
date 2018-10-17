package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DaoManager;
import com.epam.audiomanager.database.dao.impl.AlbumDaoImpl;
import com.epam.audiomanager.database.dao.impl.AudioTrackDaoImpl;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;

import java.math.BigDecimal;

public class AudioLogic {
    public static AudioTrack findAudioTrack(int audioId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        AudioTrackDaoImpl audioTrackDaoImpl = new AudioTrackDaoImpl();
        try{
            daoManager.startDAO(audioTrackDaoImpl);
            AudioTrack audioTrack = audioTrackDaoImpl.findByAudioId(audioId);
            return audioTrack;
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean isAudioTrackExists(String name, String band) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        AudioTrackDaoImpl audioTrackDaoImpl = new AudioTrackDaoImpl();
        try{
            daoManager.startDAO(audioTrackDaoImpl);
            return audioTrackDaoImpl.findByNameAndBand(name, band);
        } finally {
            daoManager.endDAO();
        }
    }

    public static Integer isAlbumExists(String album, String band) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try{
            daoManager.startDAO(albumDaoImpl);
            return albumDaoImpl.findByNameAndBand(album, band);
        } finally {
            daoManager.endDAO();
        }
    }

    public static void updateAudioTrack(int audioId, Integer albumId, String name, String band, int year,
                                        BigDecimal price, String demoAudioPath,
                                        String fullAudioPath) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        AudioTrackDaoImpl audioTrackDaoImpl = new AudioTrackDaoImpl();
        try{
            daoManager.startDAO(audioTrackDaoImpl);
            audioTrackDaoImpl.update(audioId, albumId, name, band, year, price, demoAudioPath, fullAudioPath);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }

    public static void deleteAudioTrack(int audioId) throws ProjectException {
        DaoManager daoManager =  new DaoManager();
        AudioTrackDaoImpl audioTrackDaoImpl = new AudioTrackDaoImpl();
        try{
            daoManager.startDAO(audioTrackDaoImpl);
            audioTrackDaoImpl.deleteById(audioId);
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
       DaoManager daoManager = new DaoManager();
       AudioTrackDaoImpl audioTrackDaoImpl = new AudioTrackDaoImpl();
       try{
           daoManager.startDAO(audioTrackDaoImpl);
           audioTrackDaoImpl.insert(album_id, name, artist, year, price, full_audio_path, demo_audio_path);
           daoManager.commit();
       } catch (ProjectException e) {
           daoManager.rollback();
           throw e;
       } finally {
           daoManager.endDAO();
       }
    }

    public static void insertAudioTrackWithoutAlbum(String name, String artist, int year, BigDecimal price,
                                                    String full_audio_path, String demo_audio_path)
            throws ProjectException {
        DaoManager daoManager = new DaoManager();
        AudioTrackDaoImpl audioTrackDaoImpl = new AudioTrackDaoImpl();
        try{
            daoManager.startDAO(audioTrackDaoImpl);
            audioTrackDaoImpl.insertWithoutAlbum(name, artist, year, price, full_audio_path, demo_audio_path);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }
}
