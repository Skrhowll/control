package com.dev.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VehiculeDBConfig {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/auto_dealer";
    private static final String user = "root";
    private static final String password = "";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user,
                password);
    }}