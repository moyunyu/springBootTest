package com.zcx.test.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zcx.test.dbmodel.Record;

@Repository
public class RecordDao extends BaseDao {

	private final String DEFAULT_NAME_SPACE = "com.zcx.test.dao.RecordDao";

	@Override
	public String getNamespace() {
		return DEFAULT_NAME_SPACE;
	}

	public List<Record> selectList(Map<String, String> param) {
		return super.selectList("selectList", param);
	}

	public int insert(Record record) {
		return super.insert("insert", record);
	}
}
