package com.cs.jdbc;
import java.sql.*;
/*
    JDBC编程六步
 */
public class JDBCTest01 {
    public static void main(String[] args) {
        Statement stmt = null;//执行sql语句
        Connection conn = null;//获取连接对象
        try {
            //1、注册驱动
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);

            //2、获取连接
            /*
                jdbc:mysql://协议
                127.0.0.1 IP地址(localhost都是本机IP)
                3306 MySQL数据库端口号
                cspowernode 具体的数据库实例名
             */
            String url = "jdbc:mysql://localhost:3306/cspowernode";
            String user = "root";
            String password = "1211";
            conn = DriverManager.getConnection(url,user,password);

            //3、获取数据库操作对象
            stmt = conn.createStatement();

            //4、执行sql
            String sql = "insert into dept(deptno,dname,loc) values (50,'人事部','北京')";
            //专门执行DML语句（insert，update，delete）
            //返回值是“影响数据库中的记录条数”
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1 ? "保存成功" : "保存失败");

            //5、处理查询语句集

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
