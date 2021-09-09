package com.bxw.dao.impl;

import com.bxw.dao.UserDao;
import com.bxw.entity.User;
import com.bxw.util.JdbcUtil;
import com.bxw.util.PageUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private QueryRunner qr = JdbcUtil.getQueryRunner();
    @Override
    public User login(String username, String password) {
        User user = null;
        String sql = "select * from user where username=? and password=?";
        try {
            user = qr.query(sql, new BeanHandler<>(User.class),username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int getCounts() {
        int counts = 0;
        String sql = "select count(*) from user";
        try {
            counts = (int)(long)qr.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counts;
    }

    @Override
    public List<User> getUserByPage(PageUtil pageUtil) {
        List<User> list = null;
        String sql = "select * from user limit ?,?";
        try {
            list = qr.query(sql, new BeanListHandler<>(User.class), pageUtil.getIndex(), pageUtil.getRows());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getCountsByUsername(String username) {
        int counts = 0;
        String sql = "select count(*) from user where username like '%"+ username +"%'";
        try {
            counts = (int)(long)qr.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counts;
    }

    @Override
    public List<User> getUserByUsername(PageUtil pageUtil , String username) {
        List<User> list = null;
        String sql = "select * from user where username like '%"+ username +"%' limit ?,?";
        try {
            list = qr.query(sql, new BeanListHandler<>(User.class), pageUtil.getIndex(), pageUtil.getRows());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int addUser(User user) {
        int rows = 0;
        String sql = "insert into user(name,phone,age,sex,username,password,status,createtime,role) values(?,?,?,?,?,?,?,?,?)";
        try {
            rows = qr.update(sql, user.getName(),user.getPhone(),user.getAge(),user.getSex(),user.getUsername(),user.getPassword(),user.getStatus(),
                    new Date(),user.getRole());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int delAll(String uid) {
        int row = 0;
        String sql = "delete from user where uid in ("+ uid +")";
        try {
            row = qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(row);
        return row;
    }
}
