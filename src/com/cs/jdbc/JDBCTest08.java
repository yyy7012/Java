package com.cs.jdbc;

import java.sql.*;
import java.util.Scanner;

/*
    用户输入 降序或者升序
 */
public class JDBCTest08 {
    public static void main(String[] args) {
        //用户再控制台输入desc就是降序，输入asc就是升序
        Scanner s = new Scanner(System.in);
        System.out.println("请输入desc或者asc，desc表示降序，asc表示升序");
        System.out.println("请输入：");
        String keyWords = s.nextLine();

        //执行SQL
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
            //执行SQL
            String sql = "select ename from emp order by ename " + keyWords;
            rs = stmt.executeQuery(sql);
            //遍历结果集
            while (rs.next()){
                System.out.println(rs.getString("ename"));
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
            }
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
