package com.lnngle.hycyh.generator.process.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.lnngle.hycyh.generator.TestConstants;
import com.lnngle.hycyh.generator.config.ProcesserConfig;
import com.lnngle.hycyh.generator.config.TemplateKeys;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;

class DefaultProcesserTest {

	@SuppressWarnings("unchecked")
	@Test
	void testProcess() {

		DefaultProcesser process = new DefaultProcesser();
		try {
			ProcesserConfig config = new ProcesserConfig();
			Map<String, Object> data = new HashMap<>();
			
			JSON json = JSONUtil.readJSON(new ClassPathResource(TestConstants.MODEL_DATA_FILE).getFile(), Charset.defaultCharset());
			Map<String, Object>[] modelData = json.toBean(Map[].class);
			data.put(TemplateKeys.MODEL_DATA, modelData[0]);
			
			Map<String, Object> templateData = new HashMap<>();
			templateData.put("model.ftl", "model.java");
			data.put(TemplateKeys.TEMPLATE_DATA, templateData);
			
			config.setData(data);
			
			File templateDir = new ClassPathResource(TestConstants.MODEL_TEMPLATE_DIR).getFile();
			config.setTemplateDir(templateDir);
			
			File outputDir = new File(TestConstants.MODEL_OUTPUT_DIR);
			if (!outputDir.exists()) {
				outputDir.mkdir();
			}
			config.setOutputDir(outputDir.getAbsoluteFile());

			process.process(config);
			
			File modelFile = new File(TestConstants.MODEL_OUTPUT_DIR + "model.java");
			assertTrue(modelFile.exists());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}

	}
}
