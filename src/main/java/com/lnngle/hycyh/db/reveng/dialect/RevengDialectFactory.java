package com.lnngle.hycyh.db.reveng.dialect;

import java.util.Properties;

import com.lnngle.hycyh.db.config.DatabaseKeys;
import com.lnngle.hycyh.db.jdbc.ConnectionProvider;
import com.lnngle.hycyh.db.jdbc.impl.DriverManagerConnectionProvider;
import com.lnngle.hycyh.db.reveng.dialect.impl.H2MetaDataDialect;
import com.lnngle.hycyh.db.reveng.dialect.impl.HSQLMetaDataDialect;
import com.lnngle.hycyh.db.reveng.dialect.impl.JDBCMetaDataDialect;
import com.lnngle.hycyh.db.reveng.dialect.impl.MySQLMetaDataDialect;
import com.lnngle.hycyh.db.reveng.dialect.impl.OracleMetaDataDialect;
import com.lnngle.hycyh.db.reveng.dialect.impl.SQLServerMetaDataDialect;

public class RevengDialectFactory {
	public static RevengDialect create(Properties cfg) {
		String url = cfg.getProperty(DatabaseKeys.DATASOURCE_URL);
		String driverClassName = cfg.getProperty(DatabaseKeys.DATASOURCE_DRIVERCLASSNAME);
		String username = cfg.getProperty(DatabaseKeys.DATASOURCE_USERNAME);
		String password = cfg.getProperty(DatabaseKeys.DATASOURCE_PASSWORD);

		ConnectionProvider connectionProvider = new DriverManagerConnectionProvider(url, driverClassName, username,
				password);
		RevengDialect revengDialect = null;
		if (driverClassName != null) {
			if (driverClassName.toLowerCase().contains(DatabaseKeys.TYPE_ORACLE)) {
				revengDialect = new OracleMetaDataDialect();
			}
			else if (driverClassName.toLowerCase().contains(DatabaseKeys.TYPE_MYSQL)) {
				revengDialect =  new MySQLMetaDataDialect();
			}
			else if (driverClassName.toLowerCase().contains(DatabaseKeys.TYPE_H2)) {
				revengDialect =  new H2MetaDataDialect();
			}
			else if (driverClassName.toLowerCase().contains(DatabaseKeys.TYPE_HSQL)) {
				revengDialect =  new HSQLMetaDataDialect();
			}
			else if (driverClassName.toLowerCase().contains(DatabaseKeys.TYPE_SQLSERVER)) {
				revengDialect =  new SQLServerMetaDataDialect();
			} else {
				revengDialect = new JDBCMetaDataDialect();
			}
		}
		
		if (revengDialect != null) {
			revengDialect.configure(connectionProvider);
			return revengDialect;
		} else {
			return null;
		}
		
	}
}
