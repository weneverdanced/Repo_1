package com.wby.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class JDBCUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        try (InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (is == null) {
                throw new RuntimeException("db.properties file not found in classpath");
            }

            Properties p = new Properties();
            p.load(is);
            driver = p.getProperty("driver");
            url = p.getProperty("url");
            username = p.getProperty("username");
            password = p.getProperty("password");

            Class.forName(driver);
            System.out.println("驱动加载成功！");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            System.out.println("数据库连接成功");
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("数据库连接失败");
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection conn, PreparedStatement preparedStatement, ResultSet result) {
        try {
            if (result != null) result.close();
            if (preparedStatement != null) preparedStatement.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
