package com.cs.jdbc.util;

import java.sql.*;

/**
 * 将JDBC规范下相关对象【创建】【销毁】封装到方法
 */
public class JdbcUtil {
    private Connection con = null;//类文件属性,可以在类文件中所有的方法中使用
    private PreparedStatement ps = null;

    //在当前类文件第一次加载到JVM时，JVM会自动调用当前类文件中的静态代码块
    //注册数据库服务器提供的Driver接口实现类
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Driver接口实现类被注册了");
    }

    //封装Connection对象创建细节
    public Connection creatCon(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cspowernode"
                    ,"root","1211");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    //封装PreparedStatement对象创建细节
    public PreparedStatement createStatement(String sql){
        Connection con = creatCon();
        try {
            ps = con.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }

    //封装Connection和PreparedStatement对象销毁细节
    public void close(){
        if (ps != null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (con != null){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //封装Connection和PreparedStatement,ResultSet对象销毁细节
    public void close(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //处理Connection和PreparedStatement
        close();
    }
}
