package com.cs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    PreparedStatement完成insert，delete，update
 */
public class JDBCTest09 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cspowernode"
                    ,"root","1211");
            //获取预编译的数据库操作对象
            String sql = "insert into dept(deptno,dname,loc) values (?,?,?)";
            ps = conn.prepareStatement(sql);
            //给占位符传“值”
            ps.setInt(1,60);
            ps.setString(2,"技术部");
            ps.setString(3,"长沙");
            //执行SQL
            int count = ps.executeUpdate();
            System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (ps != null){
                try {
                    ps.close();
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
