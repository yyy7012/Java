package com.cs.jdbc;

import java.sql.*;

/*
    JDBC完成delete
 */
public class JDBCTest02 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //注册驱动
            DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());

            //获取连接
            /*String url = "jdbc:mysql://localhost:3306/cspowernode";
            String user = "root";
            String password = "1211";
            conn = DriverManager.getConnection(url,user,password);*/
            //合并
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cspowernode"
                    ,"root","1211");

            //获取数据库操作对象
            stmt = conn.createStatement();

            //执行sql
            String sql = "delete from dept where deptno = 50";
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1 ? "删除成功" : "删除失败");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //释放资源
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
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
