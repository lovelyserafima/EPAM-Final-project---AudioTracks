package com.epam.audiomanager.database.dao;

import com.epam.audiomanager.exception.ProjectException;
import java.math.BigDecimal;

public interface AlbumDao {
    Integer findByNameAndBand(String name, String band) throws ProjectException;
    boolean findAlbumByAllFields(String name, String band, int year) throws ProjectException;
    void insertAlbum(String name, String band, int year, BigDecimal price) throws ProjectException;
}
