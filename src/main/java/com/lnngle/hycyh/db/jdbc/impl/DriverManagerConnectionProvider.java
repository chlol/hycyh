package com.lnngle.hycyh.db.jdbc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.lnngle.hycyh.db.jdbc.ConnectionProvider;

public class DriverManagerConnectionProvider implements ConnectionProvider {
	
	private String url;
	private String driverClassName;
	private String username;
	private String password;
	
	public DriverManagerConnectionProvider(String url, String driverClassName, String username, String password) {
		this.url = url;
		this.driverClassName = driverClassName;
		this.username = username;
		this.password = password;
	}

	@Override
	public Connection getConnection() throws SQLException {
		try {
			Class.forName(this.driverClassName);
			return DriverManager.getConnection(this.url, this.username, this.password);
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

}
