package com.lnngle.hycyh.db.export.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.lnngle.hycyh.db.config.DatabaseKeys;
import com.lnngle.hycyh.db.config.ExporterConfig;
import com.lnngle.hycyh.db.export.Exporter;
import com.lnngle.hycyh.db.reveng.dialect.RevengDialect;
import com.lnngle.hycyh.db.reveng.dialect.RevengDialectFactory;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class JsonExporter implements Exporter {

	@Override
	public void export(ExporterConfig exporterConfig) {
		Properties databaseConfig = exporterConfig.getDatabaseConfig();
		String tableCat = databaseConfig.getProperty(DatabaseKeys.TABLE_CAT);
		String tableSchem = databaseConfig.getProperty(DatabaseKeys.TABLE_SCHEM);
		String tableName = databaseConfig.getProperty(DatabaseKeys.TABLE_NAME);
		JSONArray root = new JSONArray();
		
		RevengDialect revengDialect = RevengDialectFactory.create(databaseConfig);
		Iterator<Map<String, Object>> tables = revengDialect.getTables(tableCat, tableSchem, tableName);
		while (tables.hasNext()) {
			JSONObject tJson = new JSONObject();
			Map<String, Object> table = tables.next();
			String tName = (String) table.get(DatabaseKeys.TABLE_NAME);
			String tRemarks = (String) table.get(DatabaseKeys.TABLE_REMARKS);
			String tType = (String) table.get(DatabaseKeys.TABLE_TYPE);
			
			tJson.set(CharSequenceUtil.toCamelCase(DatabaseKeys.TABLE_NAME), tName);
			tJson.set(DatabaseKeys.TABLE_REMARKS.toLowerCase(), tRemarks);
			tJson.set(CharSequenceUtil.toCamelCase(DatabaseKeys.TABLE_TYPE), tType);
			
			JSONArray cList = new JSONArray();
			Iterator<Map<String, Object>> columns = revengDialect.getColumns(tableCat, tableSchem, tName, null);
			while (columns.hasNext()) {
				JSONObject cJson = new JSONObject();
				Map<String, Object> column = columns.next();
				String cName = (String) column.get(DatabaseKeys.COLUMN_NAME);
				String cRemarks = (String) column.get(DatabaseKeys.COLUMN_REMARKS);
				Integer cDataType = (Integer) column.get(DatabaseKeys.COLUMN_DATA_TYPE);
				String cTypeName = (String) column.get(DatabaseKeys.COLUMN_TYPE_NAME);
				Integer cSize = (Integer) column.get(DatabaseKeys.COLUMN_SIZE);
				Integer cDecimalDigits = (Integer) column.get(DatabaseKeys.COLUMN_DECIMAL_DIGITS);
				Integer cNullable = (Integer) column.get(DatabaseKeys.COLUMN_NULLABLE);
				
				cJson.set(CharSequenceUtil.toCamelCase(DatabaseKeys.COLUMN_NAME), cName);
				cJson.set(DatabaseKeys.COLUMN_REMARKS.toLowerCase(), cRemarks);
				cJson.set(CharSequenceUtil.toCamelCase(DatabaseKeys.COLUMN_DATA_TYPE), cDataType);
				cJson.set(CharSequenceUtil.toCamelCase(DatabaseKeys.COLUMN_TYPE_NAME), cTypeName);
				cJson.set(CharSequenceUtil.toCamelCase(DatabaseKeys.COLUMN_SIZE), cSize);
				cJson.set(CharSequenceUtil.toCamelCase(DatabaseKeys.COLUMN_DECIMAL_DIGITS), cDecimalDigits);
				cJson.set(DatabaseKeys.COLUMN_NULLABLE.toLowerCase(), cNullable);
				
				cList.add(cJson);
			}
			tJson.set(DatabaseKeys.TABLE_COLUMNS.toLowerCase(), cList);
			root.add(tJson);
		}
		
		String jsonStr = JSONUtil.toJsonStr(root);
		try {
			exporterConfig.getOutput().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
