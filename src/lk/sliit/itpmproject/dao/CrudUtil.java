package lk.sliit.itpmproject.dao;

import lk.sliit.itpmproject.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    private static PreparedStatement pstm = null;

    public static PreparedStatement getPstm(){
        return pstm;
    }
    private CrudUtil() {
        throw new IllegalStateException("Utility class");
    }
    public static <T> T execute(String sql, Object... params) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
         pstm =  connection.prepareStatement(sql);
        try {
            for (int i = 0; i < params.length; i++) {
                pstm.setObject((i + 1), params[i]);
            }
            if (sql.startsWith("SELECT")) {
                return (T) pstm.executeQuery();
            }
            return (T) ((Boolean) (pstm.executeUpdate() > 0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return null;

    }
}
