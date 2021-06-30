package com.cs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
    注册驱动的另一种方法
 */
public class JDBCTest03 {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            //第一种方式
            //DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
            //用反射机制类加载
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cspowernode"
                    ,"root","1211");
            System.out.println(conn);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
