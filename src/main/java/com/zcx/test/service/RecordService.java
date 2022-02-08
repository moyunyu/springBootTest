package com.zcx.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcx.test.dao.RecordDao;
import com.zcx.test.dbmodel.Record;

@Service
public class RecordService {

	@Autowired
	private RecordDao recordDao;

	public List<Record> selectUser(Map<String, String> param) {
		return recordDao.selectList(param);
	}

	public void insertRecord(Record record) {
		recordDao.insert(record);
	}
}
