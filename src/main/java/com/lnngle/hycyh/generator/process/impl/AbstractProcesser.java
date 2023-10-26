package com.lnngle.hycyh.generator.process.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lnngle.hycyh.generator.config.FreemarkerConfiguration;
import com.lnngle.hycyh.generator.process.Processer;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class AbstractProcesser implements Processer {

	private Configuration configuration;

	public Configuration getConfiguration() {
		if (this.configuration == null) {
			this.configuration = FreemarkerConfiguration.getConfiguration();
		}
		return this.configuration;
	}

	protected void generateFile(Template template, Map<String, Object> data, FileWriter out)
			throws TemplateException, IOException {
		template.process(data, out);
	}

	protected void copyFile(File src, File dest, boolean isOverride) {
		FileUtil.copy(src, dest, isOverride);
	}

	protected void addContent(File file, String sign, String content) {
		List<String> lines = FileUtil.readUtf8Lines(file);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (lines.contains(sign)) {
				if (line.length() > sign.length()) {
					String s = line.substring(0, line.length() - sign.length()) + content;
					list.add(s);
				}
				list.add(content);
			}
			list.add(line);
		}
		FileUtil.writeUtf8Lines(list, file);
	}

}
