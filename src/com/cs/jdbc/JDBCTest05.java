package com.cs.jdbc;

import java.sql.*;
/*
    遍历结果集
 */
public class JDBCTest05 {
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cspowernode"
                    ,"root","1211");
            //获取数据库操作对象
            stmt = conn.createStatement();
            //执行sql
            String sql = "select empno,ename,sal from emp";
            //专门执行DQL语句的方法
            rs = stmt.executeQuery(sql);
            //处理查询结果集
            while ( rs.next() ){
                /*//下标从1开始
                String empno = rs.getString(1);
                String ename = rs.getString(2);
                String sal = rs.getString(3);*/
                //以查询语句的最终列名的名字获取
                String empno = rs.getString("empno");
                String ename = rs.getString("ename");
                String sal = rs.getString("sal");
                System.out.println(empno + "," + ename + "," +sal);
                //也可以以特定的类型取出，例如：
                //int empno = rs.getInt("empno");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }if (conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
