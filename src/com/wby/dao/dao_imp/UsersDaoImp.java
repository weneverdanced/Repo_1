package com.wby.dao.dao_imp;

import com.wby.entity.Users;
import com.wby.dao.UsersDao;
import com.wby.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDaoImp implements UsersDao {
    private static final String SQL_USER_LOGIN = "SELECT id, username, password, type, email FROM users WHERE id=? AND password=?";
    private static final String SQL_USER_INSERT = "INSERT INTO users (id, username, password, type, email) VALUES(?,?,?,?,?)";
    private static final String SQL_FIND_BY_USERNAME = "SELECT id, username, password, type, email FROM users WHERE username=?";
    private static final String SQL_FIND_BY_ID = "SELECT id, username, password, type, email FROM users WHERE id=?";
    private static final String SQL_USER_UPDATE = "UPDATE users SET password=? WHERE id=?";
    private static final String SQL_USER_DELETE = "DELETE from users WHERE id=?";

    @Override
    public Users login(String id, String password) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Users user = null;

        try {
            preparedStatement = conn.prepareStatement(SQL_USER_LOGIN);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String type = resultSet.getString("type");
                String email = resultSet.getString("email");
                user = new Users(id, username, password, type, email);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatement, resultSet);
        }
        return user;
    }

    @Override
    public Users register(String id, String username, String password, String type, String email) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        Users user = null;

        try {
            preparedStatement = conn.prepareStatement(SQL_USER_INSERT);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, type);
            preparedStatement.setString(5, email);

            int line = preparedStatement.executeUpdate();
            if (line > 0) {
                user = new Users(id, username, password, type, email);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatement, null);
        }
        return user;
    }

    @Override
    public Users findByUsername(String username) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Users user = null;

        try {
            preparedStatement = conn.prepareStatement(SQL_FIND_BY_USERNAME);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String password = resultSet.getString("password");
                String type = resultSet.getString("type");
                String email = resultSet.getString("email");
                user = new Users(id, username, password, type, email);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatement, resultSet);
        }
        return user;
    }

    @Override
    public Users findById(String id) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Users user = null;

        try {
            preparedStatement = conn.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String type = resultSet.getString("type");
                String email = resultSet.getString("email");
                user = new Users(id, username, password, type, email);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatement, resultSet);
        }
        return user;
    }

    @Override
public boolean updatePassword(String password,String id) {
    Connection conn = JDBCUtils.getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        preparedStatement = conn.prepareStatement(SQL_USER_UPDATE);
        preparedStatement.setString(1, password);
        preparedStatement.setString(2, id);
        int line = preparedStatement.executeUpdate();
        if (line > 0) {
            System.out.println("数据库更新成功");
            return true;
        } else {
            System.out.println("数据库更新失败");
            return false;
        }
    } catch (SQLException e) {
        System.out.println("更新密码时发生异常: " + e.getMessage());
        throw new RuntimeException(e);
    } finally {
        JDBCUtils.close(conn, preparedStatement, resultSet);
    }
}

}
