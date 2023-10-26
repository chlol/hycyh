package com.lnngle.hycyh.db.export;

import com.lnngle.hycyh.db.config.ExporterConfig;

public interface Exporter {

	void export(ExporterConfig exporterConfig);
}
