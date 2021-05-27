package lk.sliit.itpmproject.db;

import java.sql.*;

public class DBConnection {

    public static final String USERNAME ="MFkwg22AgC";
    public static final String DB ="MFkwg22AgC";
    public static final String PASSWORD ="YTrnJWogtE";
    private  PreparedStatement pstm;



    private static lk.sliit.itpmproject.db.DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/"+ DB, USERNAME, PASSWORD);

             pstm = connection.prepareStatement("SHOW TABLES");

        } catch (ClassNotFoundException e) {
            throw new SQLException();
        }finally {
            pstm.close();
        }
    }

    public static lk.sliit.itpmproject.db.DBConnection getInstance() throws SQLException {

        if(dbConnection == null){
            dbConnection = new lk.sliit.itpmproject.db.DBConnection();
         return dbConnection ;
        }else {
            return dbConnection;
        }
    }

    public Connection getConnection() {
        return connection;
    }

}