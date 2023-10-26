package com.lnngle.hycyh.db.config;

import java.io.Writer;
import java.util.Properties;

public class ExporterConfig {
	private Writer output;
	private Properties databaseConfig = new Properties();

	public Writer getOutput() {
		return output;
	}

	public void setOutput(Writer output) {
		this.output = output;
	}

	public Properties getDatabaseConfig() {
		return databaseConfig;
	}

	public void setDatabaseConfig(Properties databaseConfig) {
		this.databaseConfig = databaseConfig;
	}

}
