package com.gerard.site.connection;


public class ConnectionException extends Exception {
    ConnectionException() {
        super();
    }

    ConnectionException(String message) {
        super(message);
    }

    ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "ConnectionException{"
                + "message: " + super.getMessage()
                + ", cause: " + super.getCause()
                + "}:";
    }
}
