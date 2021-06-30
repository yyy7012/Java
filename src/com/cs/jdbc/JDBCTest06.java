package com.cs.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
实现功能:
    1、需求；
        模拟用户登录功能的实现
    2、业务描述:
        程序运行的时候，提供一个输入的入口，可以让用户输入用户名和密码用户输入用户名和
        密码之后，提交信息，java程序收集到用户信息Java程序连接数据库验证用户名和密码
        是否合法
        合法:显示登录成功
        不合法:显示登录失败
    3、数据的准备:
        在实际开发中，表的设计会使用专业的建模工具，我们这里安装一个建模
        工具:PowerDesigner使用PD工具来进行数据库表的设计。(参见user-login.sgl脚本)
    4、SQL注入(黑客经常使用)
        用户名：fdsa
        密码: fdsa' or '1'='1
        登录成功
 */
public class JDBCTest06 {
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
        Statement stmt = null;
        ResultSet rs = null;
        //打一个标记
        boolean loginSuccess = false;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cspowernode"
                    ,"root","1211");
            //获取数据库操作对象
            stmt = conn.createStatement();
            //执行sql
            //将map里面的数据拼接到sql语句中  "+...+"
            String sql = "select * from t_user where " +
                    "loginName = '"+userLoginInfo.get("loginName")+"' and" +
                    " loginPwd = '"+userLoginInfo.get("loginPwd")+"'";
            rs = stmt.executeQuery(sql);
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
