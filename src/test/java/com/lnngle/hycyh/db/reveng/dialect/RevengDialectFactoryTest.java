package com.lnngle.hycyh.db.reveng.dialect;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.lnngle.hycyh.db.TestData;

class RevengDialectFactoryTest {
	@Test
	void testCreate() {
		RevengDialect revengDialect = RevengDialectFactory.create(TestData.getDatabaseConfig());
		assertNotNull(revengDialect);
		Iterator<Map<String, Object>> tables = revengDialect.getTables(null, null, "tbl_role");
		assertNotNull(tables);
		while (tables.hasNext()) {
			System.out.println(tables.next());
			Iterator<Map<String, Object>> columns = revengDialect.getColumns(null, null, "tbl_role", null);
			while (columns.hasNext()) {
				System.out.println(columns.next());				
			}
		}
		revengDialect.close();
	}
}
