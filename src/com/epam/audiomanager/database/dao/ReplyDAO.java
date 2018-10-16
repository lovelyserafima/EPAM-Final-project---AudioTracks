package com.epam.audiomanager.database.dao;

import com.epam.audiomanager.entity.audio.Reply;
import com.epam.audiomanager.exception.ProjectException;
import java.util.List;

public interface ReplyDAO {
    void insertReply(int user_id, int audio_id, String text) throws ProjectException;
    List<Reply> findRepliesByAudioId(int audioId) throws ProjectException;
}
