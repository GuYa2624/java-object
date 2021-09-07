package com.bxw.dao.impl;

import com.bxw.dao.UserDao;
import com.bxw.entity.User;
import com.bxw.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

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
}
