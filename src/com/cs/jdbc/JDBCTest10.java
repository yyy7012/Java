package com.cs.jdbc;
/*
    账户转账演示事务
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * sql脚本：
 *      drop table if exists t_act;
 *      create table t_act(
 *      actno int,
 *      balance double(7,2)//7表示有效数字的个数，2表示小数的位数
 *      );
 *      insert into t_act (actno,balance) values (111,20000);
 *      insert into t_act (actno,balance) values (222,30000);
 *      commit;
 *      select * from t_act;
 *重点代码：
 *      conn.setAutoCommit(false);
 *      conn.commit();
 *      conn.rollback();
 */
public class JDBCTest10 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cspowernode"
                    ,"root","1211");
            //将自动提交机制改成手动提交机制
            conn.setAutoCommit(false);//开启事务
            //获取预编译的数据库操作对象
            String sql = "update t_act set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);

            //给？传值
            ps.setInt(2,111);
            ps.setDouble(1,10000);
            int count = ps.executeUpdate();

            //String s = null;
            //s.toString();//空指针异常，导致上面执行下面不执行

            ps.setInt(2,222);
            ps.setDouble(1,40000);
            count += ps.executeUpdate();

            System.out.println(count ==2 ? "转账成功" : "转账失败");

            //手动提交事务
            conn.commit();

        } catch (Exception e) {
            if ( conn != null){
                try {
                    //回滚事务
                    conn.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
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
