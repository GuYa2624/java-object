package com.bxw.util;

import org.apache.commons.dbutils.QueryRunner;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {
	static ComboPooledDataSource ds = new ComboPooledDataSource();
	static QueryRunner qr = new QueryRunner(ds);
	public static QueryRunner getQueryRunner() {
		return qr;
	}
}
