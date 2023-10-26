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

class AppProcesserTest {

	@SuppressWarnings("unchecked")
	@Test
	void testProcess() {

		AppProcesser process = new AppProcesser();
		try {
			ProcesserConfig config = new ProcesserConfig();
			Map<String, Object> data = new HashMap<>();
			
			JSON json = JSONUtil.readJSON(new ClassPathResource(TestConstants.APP_DATA_FILE).getFile(), Charset.defaultCharset());
			Map<String, Object> appData = json.toBean(Map.class);
			data.put(TemplateKeys.APP_DATA, appData);
			config.setData(data);
			
			File templateDir = new ClassPathResource(TestConstants.APP_TEMPLATE_DIR).getFile();
			config.setTemplateDir(templateDir);
			
			File outputDir = new File(TestConstants.APP_OUTPUT_DIR);
			if (!outputDir.exists()) {
				outputDir.mkdir();
			}
			config.setOutputDir(outputDir.getAbsoluteFile());

			process.process(config);
			
			File pomFile = new File(TestConstants.APP_OUTPUT_DIR + "demo/pom.xml");
			assertTrue(pomFile.exists());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}

	}

}
