package com.zcx.test.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zcx.test.dbmodel.User;

@Repository
public class UserDao extends BaseDao {

	private final String DEFAULT_NAME_SPACE = "com.zcx.test.dao.UserDao";

	@Override
	public String getNamespace() {
		return DEFAULT_NAME_SPACE;
	}

	public User selectByName(Map<String, String> param) {
		return super.selectOne("selectByName", param);
	}

	public int update(User user) {
		return super.update("update", user);
	}

	public int insert(User user) {
		return super.insert("insert", user);
	}

	public int countUser() {
		return super.selectOne("countUser", null);
	}
}
