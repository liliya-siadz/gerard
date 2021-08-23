package com.gerard.site.service;

import com.gerard.site.entity.PhotoAndDog;
import com.gerard.site.entity.PhotoEntity;
import com.gerard.site.exception.ServiceException;

import java.util.List;

public interface PhotoService {
    List<PhotoAndDog> provideAllPhotosOfDogs() throws ServiceException;
    List<PhotoEntity> provideAllPhotos() throws ServiceException;
}
