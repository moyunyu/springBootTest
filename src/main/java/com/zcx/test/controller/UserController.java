package com.zcx.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zcx.test.dbmodel.User;
import com.zcx.test.service.UserService;
import com.zcx.test.utils.IPUtil;
import com.zcx.test.utils.MyException;
import com.zcx.test.utils.ResponseBuilder;

@RestController
@RequestMapping("/consumer/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/login" })
	public HashMap<String, Object> login(@RequestBody HashMap<String, Object> request, HttpServletRequest httpRequest) {
		logger.info("登录开始");
		try {
			String userId = (String) request.get("userId");
			String password = (String) request.get("password");
			Map<String, String> param = new HashMap<>();
			param.put("userId", userId);
			User user = userService.selectUser(param);
			if (user == null || !user.getPassword().equals(password)) {
				return ResponseBuilder.error("用户名或密码错误");
			}
			HashMap<String, Object> result = userService.getToken(userId, password, user.getUsername(),
					IPUtil.getIpFromRequest(httpRequest));
			return ResponseBuilder.success(result);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseBuilder.error("登录异常");
		}
    }

	@RequestMapping(value = { "/add" })
	public HashMap<String, Object> add(@RequestBody HashMap<String, Object> request) {
		logger.info("注册开始");
		try {
			String userId = (String) request.get("userId");
			String password = (String) request.get("password");
			String username = (String) request.get("username");
			Map<String, String> param = new HashMap<>();
			param.put("userId", userId);
			User user = userService.selectUser(param);
			if (user != null) {
				return ResponseBuilder.error("用户名已存在");
			}
			user = new User();
			user.setUserId(userId);
			user.setUsername(username);
			user.setPassword(password);
			userService.add(user);
			return ResponseBuilder.success();
		} catch (MyException e) {
			logger.info(e.getErrorMsg());
			return ResponseBuilder.error(e);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseBuilder.error("注册异常");
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/getInfo" })
	public HashMap<String, Object> getInfo(@RequestBody HashMap<String, Object> requestMap,
			HttpServletRequest request) {
		logger.info("查询个人信息开始");
		try {
			Map<String, Object> headers = (Map<String, Object>) request.getAttribute("payload");
			Map<String, String> param = new HashMap<>();
			param.put("userId", headers.get("userId").toString());
			User user = userService.selectUser(param);
			HashMap<String, Object> result = new HashMap<>();
			result.put("userId", user.getUserId());
			result.put("username", user.getUsername());
			return ResponseBuilder.success(result);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseBuilder.error("查询异常");
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/modify" })
	public HashMap<String, Object> modify(@RequestBody HashMap<String, Object> requestMap, HttpServletRequest request) {
		logger.info("修改密码开始");
		try {
			Map<String, Object> headers = (Map<String, Object>) request.getAttribute("payload");
			String oldPassword = (String) requestMap.get("oldPassword");
			String newPassword = (String) requestMap.get("newPassword");
			Map<String, String> param = new HashMap<>();
			param.put("userId", headers.get("userId").toString());
			User user = userService.selectUser(param);
			if (!user.getPassword().equals(oldPassword)) {
				return ResponseBuilder.error("原密码错误");
			}
			if (oldPassword.equals(newPassword)) {
				return ResponseBuilder.error("修改密码与原密码一致");
			}
			user.setPassword(newPassword);
			userService.modify(user);
			return ResponseBuilder.success();
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseBuilder.error("修改异常");
		}
	}

}