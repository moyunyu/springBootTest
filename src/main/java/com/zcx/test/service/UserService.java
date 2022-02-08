package com.zcx.test.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcx.test.dao.UserDao;
import com.zcx.test.dbmodel.User;
import com.zcx.test.utils.JwtHelper;
import com.zcx.test.utils.MyException;

@Service
public class UserService {
	private final int MAX_USER_NUM = 10;

	@Autowired
	private UserDao userDao;

	public User selectUser(Map<String, String> param) {
		return userDao.selectByName(param);
	}

	public HashMap<String, Object> getToken(String userId, String password, String username, String ip) {
		Map<String, Object> payload = new HashMap<>();
		payload.put("userId", userId);
		payload.put("username", username);
		payload.put("ip", ip);
		Date issuedAt = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 14000);
		Date expiration = cal.getTime();
		String token = JwtHelper.builder(payload, expiration, issuedAt);
		HashMap<String, Object> result = new HashMap<>();
		result.put("token", token);
		result.put("refreshKey", "222222");
		result.put("userId", userId);
		result.put("username", username);
		return result;
	}

	public void modify(User user) {
		userDao.update(user);
	}

	public void add(User user) {
		int count = userDao.countUser();
		if (count > MAX_USER_NUM) {
			throw new MyException("999999", "用户名额已用完");
		}
		userDao.insert(user);
	}
}
