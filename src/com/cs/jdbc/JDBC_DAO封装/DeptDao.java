package com.cs.jdbc.JDBC_DAO封装;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD
 */
public class DeptDao {

    private JdbcUtil jdbcUtil = new JdbcUtil();
    //添加数据行
    //做插入数据时只需要调用add方法然后传参
    public int add(String deptNo,String dname,String loc){
        String sql = "insert into dept (deptNO,dname,loc) values(?,?,?)";
        int rs = 0;
        PreparedStatement ps = jdbcUtil.createStatement(sql);
        try {
            ps.setInt(1, Integer.parseInt(deptNo));
            ps.setString(2,dname);
            ps.setString(3,loc);
            rs = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return rs;
    }

    //删除数据行
    public int delete(String deptNo){
        String sql = "delete from dept where deptno=?";
        PreparedStatement ps = jdbcUtil.createStatement(sql);
        int rs = 0;
        try {
            ps.setInt(1, Integer.parseInt(deptNo));
            rs = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return rs;
    }

    //更新数据行
    public int update(String deptNo,String dname,String loc){
        String sql = "Update dept set dname=?,loc=? where deptno=?";
        PreparedStatement ps = jdbcUtil.createStatement(sql);
        int rs = 0;
        try {
            ps.setString(1,dname);
            ps.setString(2,loc);
            ps.setInt(3, Integer.parseInt(deptNo));
            rs = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return rs;
    }

    //查询数据行
    public List findAll() {
        String sql = "select *from dept";
        PreparedStatement ps = jdbcUtil.createStatement(sql);
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            ps.executeQuery();
            while (rs.next()){
                //将临时表数据行转为实体类实例对象保管
                int deptNo = rs.getInt("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                Dept dept = new Dept();
                list.add(dept);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            jdbcUtil.close(rs);
        }
        return list;
    }
}
