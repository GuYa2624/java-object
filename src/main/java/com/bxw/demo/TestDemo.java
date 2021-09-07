package com.bxw.demo;

import com.bxw.entity.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class TestDemo {
    public static void main(String[] args) {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        QueryRunner qr = new QueryRunner(ds);
        User user = null;
        String sql = "select * from user where uid=1";
        try {
            user = qr.query(sql, new BeanHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(user.getName());
    }
}
