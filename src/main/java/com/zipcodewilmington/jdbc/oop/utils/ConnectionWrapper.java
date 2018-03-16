package com.zipcodewilmington.jdbc.oop.utils;

import com.zipcodewilmington.jdbc.oop.utils.exception.SQLeonException;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class ConnectionWrapper {
    private final Connection connection;

    public ConnectionWrapper(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }


    public DatabaseMetaData getMetaData() {
        try {
            return connection.getMetaData();
        } catch (SQLException e) {
            String errorMessage = "Failed to retrieve metadata from the connection.";
            throw new SQLeonException(e, errorMessage);
        }
    }

    public String getCatalog() {
        try {
            return connection.getCatalog();
        } catch (SQLException e) {
            String errorMessage = "Failed to retrieve catalog from the metadata.";
            throw new SQLeonException(e, errorMessage);
        }
    }

    public ResultSetHandler getCatalogs() {
        try {
            ResultSet rs =  getMetaData().getCatalogs();
            ResultSetHandler rsh = new ResultSetHandler(rs);
            return rsh;
        } catch (SQLException e) {
            String errorMessage = "Failed to retrieve the catalogs from the metadata.";
            throw new SQLeonException(e, errorMessage);
        }
    }

    public String[] getSchemaNames() {
        ResultSetHandler rsh = getCatalogs();
        String schemaColumn = rsh.getColumnName(1);
        System.out.println(schemaColumn);
        String[] schemaNames = rsh.getRows(schemaColumn);
        return schemaNames;
    }


    public Boolean hasDatabase(String name) {
        List<String> schemaNames = Arrays.asList(getSchemaNames());
        return schemaNames.contains(name);
    }
}
