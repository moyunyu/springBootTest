package com.zcx.test.dao;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.zcx.test.dbmodel.Ttest;

@Repository
public class TtestDao extends BaseDao {

	private final String DEFAULT_NAME_SPACE = "com.zcx.test.dao.TtestDao";

	@Override
	public String getNamespace() {
		return DEFAULT_NAME_SPACE;
	}


	public int insert(Ttest test) {
		return super.insert("insert", test);
	}

	public Ttest select(BigDecimal id) {
		return super.selectOne("selectOne", id);
	}

	public Ttest update(Ttest test) {
		return super.selectOne("update", test);
	}
}
