package com.lnngle.hycyh.generator.config;

import java.io.File;
import java.util.Map;

public class ProcesserConfig {

	private File templateDir;
	private Map<String, Object> data;
	private File outputDir;

	public File getTemplateDir() {
		return templateDir;
	}

	public void setTemplateDir(File templateDir) {
		this.templateDir = templateDir;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public File getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(File outputDir) {
		this.outputDir = outputDir;
	}

}
