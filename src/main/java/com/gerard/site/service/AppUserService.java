package com.gerard.site.service;

import com.gerard.site.dao.AppUserDao;
import com.gerard.site.dao.DaoException;
import com.gerard.site.entity.UserEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.form.LoginForm;
import com.gerard.site.util.BCrypt;
import com.gerard.site.util.CustomDocumentUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Properties;

public class AppUserService {

    private static AppUserService instance;
    private static final Logger LOGGER = LogManager.getLogger(AppUserService.class);

    private AppUserService(){
    }

    public static AppUserService getInstance() {
        if(instance == null){
            instance = new AppUserService();
        }return instance;
    }

    public Optional<UserEntity> authenticate(LoginForm loginForm)
            throws ServiceException {
        if (loginForm == null) {
            throw new ServiceException("Parameter 'loginForm' is null!");
        }
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        if((email == null) || (password == null)) {
            throw new ServiceException("Login form has null values!");
        }
        UserEntity user = new UserEntity.Builder().email(email).build();
        try {
            UserEntity realUser = AppUserDao.getInstance().findRecord(user);
            if (realUser != null) {
                String token = getToken();
                String userPassword = AppUserDao.getInstance()
                        .selectUserPasswordByEmail(token, email);
                boolean isPasswordCorrect = BCrypt.checkpw(password, userPassword);
                if (isPasswordCorrect) {
                    return Optional.of(realUser);
                }
            }
            LOGGER.info("No user found with presented credentials. Email: " + email);
            return Optional.empty();
        } catch (DaoException | IOException | URISyntaxException exception) {
            throw new ServiceException("Unable to find record from form:  "
                    + loginForm + " . " + exception.getMessage(), exception);
        }
    }

    private String getToken() throws IOException, URISyntaxException {
        String securityFileResourcePath = "/security.properties";
        Properties securityProperties
                = CustomDocumentUtil.loadResourcePropertiesByObject(
                instance, securityFileResourcePath);
        if ((securityProperties == null)) {
            LOGGER.fatal("Security properties is null! "
                    + "Security properties file path: "
                    + securityFileResourcePath);
            throw new RuntimeException("Security properties is null! "
                    + "Security properties file path: "
                    + securityFileResourcePath);
        }
        String passwordToken = "passwordToken";
        String token = securityProperties.getProperty(passwordToken);
        return token;
    }
}
