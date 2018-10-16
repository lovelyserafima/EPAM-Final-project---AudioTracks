package com.epam.audiomanager.logic;

import com.epam.audiomanager.database.dao.DAOManager;
import com.epam.audiomanager.database.dao.impl.AlbumDAOImpl;
import com.epam.audiomanager.exception.ProjectException;
import java.math.BigDecimal;

public class AlbumLogic {

    public static boolean isAlbumExists(String name, String band, int year) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        AlbumDAOImpl albumDAOImpl = new AlbumDAOImpl();
        try{
            daoManager.startDAO(albumDAOImpl);
            return albumDAOImpl.findAlbumByAllFields(name, band, year);
        } finally {
            daoManager.endDAO();
        }
    }

    public static void addNewAlbum(String name, String band, int year, BigDecimal price) throws ProjectException {
        DAOManager daoManager = new DAOManager();
        AlbumDAOImpl albumDAOImpl = new AlbumDAOImpl();
        try{
            daoManager.startDAO(albumDAOImpl);
            albumDAOImpl.insertAlbum(name, band, year, price);
            daoManager.commit();
        } catch (ProjectException e){
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }

    //public static int getAlbumIdByNameAndBand()
}
