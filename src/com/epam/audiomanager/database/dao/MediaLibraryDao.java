package com.epam.audiomanager.database.dao;

import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import java.util.List;

public interface MediaLibraryDao {
    List<User> findUsersHavingTrack(int audioId) throws ProjectException;
    List findAll(int id) throws ProjectException;
}
