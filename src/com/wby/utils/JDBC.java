package com.wby.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC {
    public static void main(String[] args) {
        try {//出现异常提示时用try-catch包围
            //加载mysql驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("驱动加载成功");
            //连接数据库,获得连接对象
            //三个参数：数据库地址/数据库账户/数据库密码
            String url = "jdbc:mysql://localhost:3306/your_database_name?useUnicode=true&characterEncoding=utf8";
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/data?serverTimezone=UTC","root","wby@20040311W");
            System.out.println("数据库连接成功");
            //创建执行环境
            Statement createStatement = conn.createStatement();
            //执行sql语句，得到结果集
            //数据库大小写不敏感
            ResultSet result = createStatement.executeQuery("SELECT * FROM users");
            //结果集指针一开始指向表头//当下一个数据不为空时循环打印
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
