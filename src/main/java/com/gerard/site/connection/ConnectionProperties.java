package com.gerard.site.connection;

import com.gerard.site.util.CustomDocumentUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

final class ConnectionProperties {
    static Properties dbConnectionProperties;
    static int poolSize;
    static long antiLeakingConnectionsStartMin;
    static long antiLeakingConnectionsPeriodMin;
    static int quantityOfTriesToOfferFreeConnections;
    static String url;
    static int quantityOfTriesToCreateConnection;
    static int quantityOfTriesToCreateValidConnection;
    private static ConnectionProperties instance;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private ConnectionProperties() {
        String dbConnectionResourcePath = "/connection.properties";
        try {
            dbConnectionProperties = CustomDocumentUtil.loadResourcePropertiesByObject(this, dbConnectionResourcePath);
            if (dbConnectionProperties == null) {
                LOGGER.fatal("Database connection properties is null! "
                        + "Resource FILE: " + dbConnectionResourcePath);
                throw new RuntimeException("Database connection properties is null! "
                        + "Resource FILE: " + dbConnectionResourcePath);
            }
            String poolSizePropertyKey = "size";
            String quantityOfTriesToOfferFreeConnectionsPropertyKey = "triesToOfferFreeConnections.quantity";
            String antiLeakingConnectionsStartMinPropertyKey = "antiLeakingConnectionsStart.min";
            String antiLeakingConnectionsPeriodMinPropertyKey = "antiLeakingConnectionsPeriod.min";
            String quantityOfTriesToCreateValidConnectionPropertyKey = "connectionCreationValidTries.quantity";
            String urlPropertyKey = "url";
            String quantityOfTriesToCreateConnectionPropertyKey = "connectionCreationTries.quantity";
            poolSize = Integer.parseInt(
                    dbConnectionProperties.getProperty(poolSizePropertyKey));
            quantityOfTriesToOfferFreeConnections = Integer.parseInt(
                    dbConnectionProperties.getProperty(quantityOfTriesToOfferFreeConnectionsPropertyKey));
            antiLeakingConnectionsStartMin = Long.parseLong(
                    dbConnectionProperties.getProperty(antiLeakingConnectionsStartMinPropertyKey));
            antiLeakingConnectionsPeriodMin = Long.parseLong(
                    dbConnectionProperties.getProperty(antiLeakingConnectionsPeriodMinPropertyKey));
            quantityOfTriesToCreateValidConnection = Integer.parseInt(
                    dbConnectionProperties.getProperty(quantityOfTriesToCreateValidConnectionPropertyKey));
            url = dbConnectionProperties.getProperty(urlPropertyKey);
            quantityOfTriesToCreateConnection = Integer.parseInt(
                    dbConnectionProperties.getProperty(quantityOfTriesToCreateConnectionPropertyKey));
        } catch (IOException | URISyntaxException exception) {
            LOGGER.fatal("Database connection properties resource file CONTENT: "
                    + dbConnectionResourcePath + "is invalid!");
            throw new RuntimeException("Database connection properties resource file CONTENT: "
                    + dbConnectionResourcePath + "is invalid! " + exception.getMessage(), exception);
        }
    }

    static void init() {
        if (instance == null) {
            instance = new ConnectionProperties();
        }
    }
}