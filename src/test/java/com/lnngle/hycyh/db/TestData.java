package com.lnngle.hycyh.db;

import java.util.Properties;

import com.lnngle.hycyh.db.config.DatabaseKeys;

public class TestData {

	public static Properties getDatabaseConfig() {
		String url = "jdbc:mariadb://192.168.134.54:13306/chtest?useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true";
		String driverClassName = "org.mariadb.jdbc.Driver";
		String username = "chtest";
		String password = "chtest";
		
		Properties cfg = new Properties();
		cfg.setProperty(DatabaseKeys.DATASOURCE_URL, url);
		cfg.setProperty(DatabaseKeys.DATASOURCE_DRIVERCLASSNAME, driverClassName);
		cfg.setProperty(DatabaseKeys.DATASOURCE_USERNAME, username);
		cfg.setProperty(DatabaseKeys.DATASOURCE_PASSWORD, password);
		
		return cfg;
	}

}
