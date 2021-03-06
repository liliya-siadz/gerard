package com.gerard.site.dao.impl;

import com.gerard.site.dao.AbstractDao;
import com.gerard.site.dao.PhotoDao;
import com.gerard.site.dao.connection.ConnectionException;
import com.gerard.site.dao.connection.ConnectionPool;
import com.gerard.site.service.view.Photo;
import com.gerard.site.service.entity.PhotoEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PhotoDaoImpl extends AbstractDao<PhotoEntity> implements PhotoDao {
    static final String TABLE_NAME = "gerard.photo";
    static final String COLUMN_LABEL_1 = "photo_id";
    static final String COLUMN_LABEL_2 = "photo_path";
    static final String COLUMN_LABEL_3 = "dog_id";
    static final String COLUMN_LABEL_4 = "photo_date";
    static final String COLUMN_LABEL_5 = "photo_type";
    private static PhotoDaoImpl instance;

    private static final String SELECT_ALL_PHOTOS_TYPE_PHOTO =
            "select photo_path, photo_date, photo_id,photo_type, photo_path, dog_id"
                    + " from photo"
                    + " where photo_type='photo'";
    private static final String SELECT_ALL_PHOTOS_AND_DOGS =
            "select photo_path, dog.nickname, photo_date"
                    + " from photo left join dog "
                    + " on photo.dog_id = dog.dog_id"
                    + " where photo_type='photo'"
                    + " order by photo_date desc";
    private static final String DELETE_PHOTO_BY_PHOTO_PATH
            = "delete from photo where photo_path=?";

    private static final Logger LOGGER = LogManager.getLogger(PhotoDaoImpl.class);

    private PhotoDaoImpl() {
        super();
    }


    public static PhotoDaoImpl getInstance() {
        if (instance == null) {
            instance = new PhotoDaoImpl();
        }
        return instance;
    }

    public List<Photo> selectAllPhotos() throws DaoException {
        try (Connection connection
                     = ConnectionPool.getInstance().giveOutConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet
                    = statement.executeQuery(SELECT_ALL_PHOTOS_AND_DOGS);
            if (resultSet.isBeforeFirst()) {
                List<Photo> photos = new ArrayList<>();
                while (resultSet.next()) {
                    String photoPath = resultSet.getString(COLUMN_LABEL_2);
                    Date photoDate = resultSet.getDate(COLUMN_LABEL_4);
                    String nickname = resultSet.getString(DogDaoImpl.COLUMN_LABEL_3);
                    Photo photo = new Photo(photoPath, photoDate, nickname);
                    photos.add(photo);
                }
                return photos;
            } else {
                LOGGER.info("No records were found in table: " + TABLE_NAME + ". ");
                return Collections.emptyList();
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: " + TABLE_NAME
                    + " ! " + exception.getMessage(), exception);
        }
    }

    @Override
    public List<PhotoEntity> selectAll() throws DaoException {
        try (Connection connection
                     = ConnectionPool.getInstance().giveOutConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet
                    = statement.executeQuery(SELECT_ALL_PHOTOS_TYPE_PHOTO);
            if (resultSet.isBeforeFirst()) {
                List<PhotoEntity> photos = new ArrayList<>();
                while (resultSet.next()) {
                    photos.add(parseResultSetEntity(resultSet));
                }
                return photos;
            } else {
                LOGGER.info("No records were found in table: "
                        + TABLE_NAME + ". ");
                return Collections.emptyList();
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to select data from table: "
                    + TABLE_NAME + " ! "
                    + "Reason: " + exception.getMessage(), exception);
        }
    }

    @Override
    public List<Photo> provideDecimalPieceOfPhotos(int pieceValue)
            throws DaoException {
        try (Connection connection
                     = ConnectionPool.getInstance().giveOutConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(buildPaginatedQuery(
                    SELECT_ALL_PHOTOS_AND_DOGS, pieceValue));
            if (resultSet.isBeforeFirst()) {
                List<Photo> photos = new ArrayList<>();
                while (resultSet.next()) {
                    String photoPath = resultSet.getString(COLUMN_LABEL_2);
                    Date photoDate = resultSet.getDate(COLUMN_LABEL_4);
                    String nickname = resultSet.getString(DogDaoImpl.COLUMN_LABEL_3);
                    Photo photo = new Photo(photoPath, photoDate, nickname);
                    photos.add(photo);
                }
                return photos;
            } else {
                LOGGER.info("No records were found in table: "
                        + TABLE_NAME + ". ");
                return Collections.emptyList();
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to select data from table: "
                    + TABLE_NAME + " ! "
                    + exception.getMessage(), exception);
        }
    }

    @Override
    public boolean deleteByPhotoPath(String photoPath) throws DaoException {
        if (photoPath == null) {
            LOGGER.error("Parameter 'photoPath' is null!");
            throw new DaoException("Parameter 'photoPath' is null!");
        }
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().giveOutConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            DELETE_PHOTO_BY_PHOTO_PATH);
            preparedStatement.setString(1, photoPath);
            connection.setAutoCommit(false);
            int updatedRowsQuantity = preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            ConnectionPool.getInstance().getBackConnection(connection);
            LOGGER.info(updatedRowsQuantity
                    + " rows was updated in table " + TABLE_NAME);
        } catch (ConnectionException | SQLException exception) {
            try {
                if (connection != null) {
                    connection.rollback();
                    LOGGER.trace("Unsuccessfully");
                    return false;
                } else {
                    LOGGER.warn("Connection is null");
                }
            } catch (SQLException throwables) {
                LOGGER.error("Unable to rollback transaction! "
                        + throwables.getMessage(), throwables);
            }
            throw new DaoException("Unable to update data in table: "
                    + TABLE_NAME + " ! "
                    + exception.getMessage(), exception);
        }
        LOGGER.trace("Successfully");
        return true;
    }

    @Override
    public PhotoEntity parseResultSetEntity(ResultSet resultSet)
            throws SQLException {
        int photoId = resultSet.getInt(COLUMN_LABEL_1);
        String photoPath = resultSet.getString(COLUMN_LABEL_2);
        int dogId = resultSet.getInt(COLUMN_LABEL_3);
        Date photoDate = resultSet.getDate(COLUMN_LABEL_4);
        PhotoEntity.PhotoType photoType = PhotoEntity.PhotoType.valueOf(
                resultSet.getString(COLUMN_LABEL_5).toUpperCase());
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setId(photoId);
        photoEntity.setPhotoPath(photoPath);
        photoEntity.setPhotoType(photoType);
        photoEntity.setDogId(dogId);
        photoEntity.setPhotoDate(photoDate);
        return photoEntity;
    }

    @Override
    public Optional<PhotoEntity> find(PhotoEntity user) throws DaoException {
        throw new UnsupportedOperationException();
    }
}
