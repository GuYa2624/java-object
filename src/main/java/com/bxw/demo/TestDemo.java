package com.bxw.demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class TestDemo {
    public static void main(String[] args) {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        QueryRunner qr = new QueryRunner(ds);
        String sql = "delete from student where sid=1";
        try {
            int update = qr.update(sql);
            System.out.println(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
