package com.zcx.test.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public abstract String getNamespace();

	public int insert(String sqlMap, Object object) {
		return sqlSessionTemplate.insert(getNamespace() + "." + sqlMap, object);
	}

	public int delete(String sqlMap, Object object) {
		return sqlSessionTemplate.delete(getNamespace() + "." + sqlMap, object);
	}

	public int update(String sqlMap, Object object) {
		return sqlSessionTemplate.update(getNamespace() + "." + sqlMap, object);
	}

	public <T> T selectOne(String sqlMap, Object object) {
		if (object == null) {
			return selectOne(sqlMap);
		}
		return sqlSessionTemplate.selectOne(getNamespace() + "." + sqlMap, object);
	}

	public <T> T selectOne(String sqlMap) {
		return sqlSessionTemplate.selectOne(getNamespace() + "." + sqlMap);
	}

	public <T> List<T> selectList(String sqlMap, Object object) {
		return sqlSessionTemplate.selectList(getNamespace() + "." + sqlMap, object);
	}

	public <T> List<T> selectList(String sqlMap, Object object, Integer currentNum, Integer pageSize) {
		return sqlSessionTemplate.selectList(getNamespace() + "." + sqlMap, object,
				new RowBounds(currentNum, pageSize));
	}
}
