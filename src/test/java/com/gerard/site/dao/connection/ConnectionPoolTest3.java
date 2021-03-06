package com.gerard.site.dao.connection;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;

import static org.testng.Assert.assertEquals;

public class ConnectionPoolTest3 {
    private ConnectionPool connectionPool;

    @BeforeClass
    public void callConnectionPoolInstance() {
        connectionPool = ConnectionPool.getInstance();
    }

    @DataProvider(name = "dataProviderGivenConnectionsToGetBack")
    public Object[][] dataProviderGivenConnectionsToGetBack()
            throws ConnectionException {
        return new Object[][]{
                {connectionPool.giveOutConnection(), true},
                {connectionPool.giveOutConnection(), true},
                {connectionPool.giveOutConnection(), true},
                {null, false}
        };
    }

    @Test(dataProvider = "dataProviderGivenConnectionsToGetBack",
            description = "Check that pool connection can get back taken connections",
            threadPoolSize = 3, invocationCount = 2)
    public void testGetBackConnection(Connection connection, boolean expected)
            throws ConnectionException {
        boolean actual = connectionPool.getBackConnection(connection);
        assertEquals(actual, expected);
    }

    @AfterClass
    public void closeOpenedConnections() {
        connectionPool.destroy();
    }
}