package com.lnngle.hycyh.generator.config;

import java.io.File;
import java.io.FileFilter;

public class FtlFileFilter implements FileFilter {
	public static final String FTL_EXT = ".ftl";
	
	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		}
		
		return file.getName().toLowerCase().endsWith(FTL_EXT);
	}

}
