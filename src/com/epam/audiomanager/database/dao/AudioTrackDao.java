package com.epam.audiomanager.database.dao;

import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;
import java.math.BigDecimal;
import java.util.List;

public interface AudioTrackDao {
    AudioTrack findByAudioId(int audioId) throws ProjectException;
    List findAudioTracksBySmth(String entity) throws ProjectException;
    void buyAudioTrack(int clientId, int audioId) throws ProjectException;
    void update(int audioId, Integer albumId, String name, String band, int year, BigDecimal price, String demoAudioPath,
                String fullAudioPath) throws ProjectException;
    void deleteById(int audioId) throws ProjectException;
    boolean findByNameAndBand(String name, String band) throws ProjectException;
    void insert(Integer album_id, String name, String artist, int year, BigDecimal price, String full_audio_path,
                String demo_audio_path) throws ProjectException;
    void insertWithoutAlbum(String name, String artist, int year, BigDecimal price, String full_audio_path,
                            String demo_audio_path) throws ProjectException;
}
