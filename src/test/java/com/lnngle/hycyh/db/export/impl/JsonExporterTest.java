package com.lnngle.hycyh.db.export.impl;

import java.io.StringWriter;
import java.io.Writer;

import org.junit.jupiter.api.Test;

import com.lnngle.hycyh.db.TestData;
import com.lnngle.hycyh.db.config.ExporterConfig;

class JsonExporterTest {

	@Test
	void testExport() {
		JsonExporter exporter = new JsonExporter();
		ExporterConfig config = new ExporterConfig();
		config.setDatabaseConfig(TestData.getDatabaseConfig());
		Writer writer = new StringWriter();
		config.setOutput(writer);
		exporter.export(config);
		System.out.println(writer.toString());
	}

}
