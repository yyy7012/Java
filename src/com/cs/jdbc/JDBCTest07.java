package com.cs.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/*
    解决SQL注入问题:
        让用户提供的信息不参与SQL语句的编译过程
        使用java.sql.PreparedStatement，属于预编译的数据库操作对象
        预先对SQL语句的框架进行编译，然后给SQL语句传“值”
        继承了java.sql.Statement
    Statement是编译一次执行一次
    PreparedStatement是编译一次执行n次，而且会在编译阶段进行类型的安全检查
        当业务要求必须SQL注入的时候用Statement
 */
public class JDBCTest07 {
    public static void main(String[] args) {
        //初始化一个界面
        Map<String,String> userLoginInfo = initUI();
        //验证用户名和密码
        boolean loginSuccess = login(userLoginInfo);
        //最后输出结果
        System.out.println(loginSuccess ? "登录成功" : "登录失败");
    }

    /**
     * 用户登录
     * @param userLoginInfo 用户登录信息
     * @return false表示登录失败，true表示登录成功
     */
    private static boolean login(Map<String, String> userLoginInfo) {
        //JDBC代码
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //打一个标记
        boolean loginSuccess = false;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cspowernode"
                    ,"root","1211");
            //获取预编译的数据库操作对象
            //? 为占位符，一个 ？ 只能接收一个“值”
            String sql = "select * from t_user where loginName = ? and loginPwd = ?";
            //程序执行到这里会发送sql语句的框子给DBMS，然后DBMS进行sql语句的预先编译
            ps = conn.prepareStatement(sql);
            //给占位符 ？传值（第一个问号下表是1,JDBC中所有的下表从一开始）
            ps.setString(1,userLoginInfo.get("loginName"));
            ps.setString(2,userLoginInfo.get("loginPwd"));
            //执行sql
            rs = ps.executeQuery();//如果写sql还会再编译一次
            //处理查询结果集
            if (rs.next()){
                //登录成功
                loginSuccess = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //释放资源
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
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
        return loginSuccess;
    }

    /**
     * 初始化用户界面
     * @return 用户输入的同户名和密码等登录信息
     */
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.print("用户名：");
        String loginName = s.nextLine();

        System.out.print("用户密码：");
        String loginPwd = s.nextLine();

        Map<String,String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName",loginName);
        userLoginInfo.put("loginPwd",loginPwd);

        return userLoginInfo;
    }
}
