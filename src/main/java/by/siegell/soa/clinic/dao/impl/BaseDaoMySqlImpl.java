package by.siegell.soa.clinic.dao.impl;

import java.sql.Connection;

abstract public class BaseDaoMySqlImpl {
    private Connection connection;

    protected Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}