package com.aaygee.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.aaygee.chatapp.utils.ConfigReader.getValue;

public interface CommonDAO {
    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        // Loading JDBC Driver
        Class.forName(getValue("DRIVER"));

        //Credentials
        final String url = getValue("DB_URL");
        final String user = getValue("USER");
        final String password = getValue("PASSWORD");

        //Making Connection
        Connection con = DriverManager.getConnection(url,user,password);
        if(con!=null){
            System.out.println("Connected");
        }
        return con;
    }
}
