package com.gerard.site.service.impl;

import com.gerard.site.dao.impl.AppUserDaoImpl;
import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.service.entity.AppUserEntity;
import com.gerard.site.service.ServiceException;
import com.gerard.site.controller.form.LoginForm;
import com.gerard.site.service.AppUserService;
import com.gerard.site.service.util.BCrypt;
import com.gerard.site.service.util.AppIdentifierUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Properties;

public class AppUserServiceImpl implements AppUserService {
    private static AppUserServiceImpl instance;
    private static final Logger LOGGER =
            LogManager.getLogger(AppUserServiceImpl.class);

    private AppUserServiceImpl() {
    }

    public static AppUserServiceImpl getInstance() {
        if (instance == null) {
            instance = new AppUserServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean authenticate(LoginForm loginForm)
            throws ServiceException {
        if (loginForm == null) {
            throw new ServiceException("Parameter 'loginForm' is null!");
        }
        LOGGER.trace("Authenticate service was called. Login form: " + loginForm);
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        if ((email == null) || (password == null)) {
            throw new ServiceException("Login form has null values!");
        }
        AppUserEntity user = new AppUserEntity.Builder().email(email).build();
        try {
            Optional<AppUserEntity> realUser = AppUserDaoImpl.getInstance().find(user);
            boolean userWasAuthenticated = false;
            if (realUser.isPresent()) {
                if (!realUser.get().isAdmin()) {
                    return false;
                }
                String token = getToken();
                String userPassword = AppUserDaoImpl.getInstance()
                        .selectPasswordByEmail(token, email).get();
                boolean isPasswordCorrect = BCrypt.checkpw(password, userPassword);
                if (isPasswordCorrect) {
                    userWasAuthenticated = true;
                }
            }
            return userWasAuthenticated;
        } catch (DaoException exception) {
            LOGGER.error("Unable to authenticate user:  "
                    + loginForm + " . " + exception.getMessage(), exception);
            throw new ServiceException("Unable to authenticate user:  "
                    + loginForm + " . " + exception.getMessage(), exception);
        }
    }

    private String getToken() {
        String securityFileResourcePath = "/security.properties";
        Properties securityProperties = AppIdentifierUtil.getPropertiesByPath(
                instance, securityFileResourcePath);
        String passwordToken = "passwordToken";
        String token = securityProperties.getProperty(passwordToken);
        return token;
    }
}
