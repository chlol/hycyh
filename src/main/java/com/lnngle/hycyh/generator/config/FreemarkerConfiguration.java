package com.lnngle.hycyh.generator.config;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerConfiguration {
	private static final String DEFAULT_ENCODING = "UTF-8";

	private FreemarkerConfiguration() {
	}

	public static Configuration getConfiguration() {
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
			cfg.setDefaultEncoding(DEFAULT_ENCODING);
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			return cfg;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
