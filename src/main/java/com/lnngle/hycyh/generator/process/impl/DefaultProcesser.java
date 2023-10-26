package com.lnngle.hycyh.generator.process.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import com.lnngle.hycyh.generator.config.FtlFileFilter;
import com.lnngle.hycyh.generator.config.ProcesserConfig;
import com.lnngle.hycyh.generator.config.TemplateKeys;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Template;

public class DefaultProcesser extends AbstractProcesser {

	@SuppressWarnings("unchecked")
	@Override
	public void process(ProcesserConfig processerConfig) {
		try {
			this.getConfiguration().setDirectoryForTemplateLoading(processerConfig.getTemplateDir());
			Map<String, Object> data = processerConfig.getData();
			Map<String, Object> modelData = (Map<String, Object>) data.get(TemplateKeys.MODEL_DATA);
			Map<String, Object> templateData = (Map<String, Object>) data.get(TemplateKeys.TEMPLATE_DATA);
			File outputDir = processerConfig.getOutputDir();
			List<File> files = FileUtil.loopFiles(processerConfig.getTemplateDir(), new FtlFileFilter());
			for (File file : files) {
				String name = file.getName();
				Template template = this.getConfiguration().getTemplate(name);
				String path = (String) templateData.get(name);
				File outFile = FileUtil.file(outputDir, path);
				FileWriter fileWriter = new FileWriter(outFile);
				this.generateFile(template, modelData, fileWriter);
			}
			
			Map<String, Object>[] signData = (Map<String, Object>[]) data.get(TemplateKeys.SIGN_DATA);
			if (signData != null && signData.length > 0) {
				for (Map<String, Object> map : signData) {
					File file = (File) map.get(TemplateKeys.SignKeys.FILE);
					String sign = (String) map.get(TemplateKeys.SignKeys.SIGN);
					String content = (String) map.get(TemplateKeys.SignKeys.CONTENT);
					
					this.addContent(file, sign, content);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
