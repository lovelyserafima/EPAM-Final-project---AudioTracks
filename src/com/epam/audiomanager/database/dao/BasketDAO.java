package com.epam.audiomanager.database.dao;

import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;

import java.util.List;

public interface BasketDAO {
    void insertOrder(int clientId, int audioId) throws ProjectException;
    List findAll(int id) throws ProjectException;
    void deleteByClientIdAndAudioId(int clientID, int audio_id) throws ProjectException;
    boolean findCanceledOrders(int clientId, int audioId) throws ProjectException;
    void resumeOrder(int clientId, int audioId) throws ProjectException;
    List<User> findUsersWantingToBuy(int audioId) throws ProjectException;
}
