package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DaoManager;
import com.epam.audiomanager.database.dao.impl.AlbumDaoImpl;
import com.epam.audiomanager.exception.ProjectException;
import java.math.BigDecimal;

public class AlbumLogic {
    private AlbumLogic(){}

    public static boolean isAlbumExists(String name, String band, int year) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try{
            daoManager.startDAO(albumDaoImpl);
            return albumDaoImpl.findAlbumByAllFields(name, band, year);
        } finally {
            daoManager.endDAO();
        }
    }

    public static void addNewAlbum(String name, String band, int year, BigDecimal price) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        AlbumDaoImpl albumDaoImpl = new AlbumDaoImpl();
        try{
            daoManager.startDAO(albumDaoImpl);
            albumDaoImpl.insertAlbum(name, band, year, price);
            daoManager.commit();
        } catch (final Exception e){
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }
}
