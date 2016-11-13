package util;

import com.mysql.jdbc.Driver;

import java.sql.*;

/**
 * Created by Administrator on 2016/11/12.
 */

// refactor 重构
public class DB {

    private static final String URL = "jdbc:mysql:///db_dictionary";
    private static final String USER = "root";
    private static final String PASSWORD = "system";

    public static void main(String[] args) {
        getConnection();
    }

    public static Connection getConnection() {
        try {
            new Driver();
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            System.out.println(databaseMetaData.getUserName());
            System.out.println(databaseMetaData.getDatabaseProductVersion());
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
