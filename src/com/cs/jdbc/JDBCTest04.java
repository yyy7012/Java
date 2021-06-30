package com.cs.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

/*
    将数据库的所有信息配置到配置文件中
 */
public class JDBCTest04 {
    public static void main(String[] args) {
        //只用资源绑定器绑定配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user= bundle.getString("user");
        String password = bundle.getString("password");

        Statement stmt = null;
        Connection conn = null;
        try {
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2、获取连接
            url = "jdbc:mysql://localhost:3306/cspowernode";
            user = "root";
            password = "1211";
            conn = DriverManager.getConnection(url,user,password);

            //3、获取数据库操作对象
            stmt = conn.createStatement();

            //4、执行sql
            String sql = "update dept set dname='技术部',loc='长沙' where deptno=50";
            //专门执行DML语句（insert，update，delete）
            //返回值是“影响数据库中的记录条数”
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1 ? "修改成功" : "修改失败");

            //5、处理查询语句集

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //6、释放资源
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
