package com.zcx.test.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zcx.test.dao.TtestDao;
import com.zcx.test.dbmodel.Ttest;
import com.zcx.test.utils.ResponseBuilder;

@RestController
@RequestMapping("/test")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private TtestDao testDao;

	@RequestMapping(value = { "/test1" })
	public HashMap<String, Object> login(@RequestBody HashMap<String, Object> request, HttpServletRequest httpRequest) {
		try {
			Ttest test = new Ttest();
			BigDecimal id = new BigDecimal("10");
			test.setId1(id);
			test.setType(null);
			testDao.update(test);

			test = testDao.select(id);
			System.out.println(test.toString());

			return ResponseBuilder.success();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseBuilder.error("登录异常");
		}
	}
}
