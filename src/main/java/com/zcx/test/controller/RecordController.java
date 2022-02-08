package com.zcx.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zcx.test.dbmodel.Record;
import com.zcx.test.service.RecordService;
import com.zcx.test.utils.DateUtil;
import com.zcx.test.utils.ResponseBuilder;

@RestController
@RequestMapping("/consumer/record")
public class RecordController {

	private static final Logger logger = LoggerFactory.getLogger(RecordController.class);
	@Autowired
	private RecordService recordService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/gameRecord" })
	public HashMap<String, Object> gameRecord(@RequestBody HashMap<String, Object> requestMap,
			HttpServletRequest request) {
		logger.info("查询记录开始");
		try {
			String gameId = (String) requestMap.get("gameId");
			Map<String, Object> headers = (Map<String, Object>) request.getAttribute("payload");

			Map<String, String> param = new HashMap<>();
			param.put("gameId", gameId);
			param.put("userId", headers.get("userId").toString());
			List<Record> resultList = recordService.selectUser(param);

			return ResponseBuilder.success(resultList);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseBuilder.error("查询游戏记录异常");
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/insertRecord" })
	public HashMap<String, Object> insertRecord(@RequestBody HashMap<String, Object> requestMap,
			HttpServletRequest request) {
		logger.info("插入记录开始");
		try {
			String gameId = (String) requestMap.get("gameId");
			int score = (int) requestMap.get("score");
			Float time = (Float) requestMap.get("time");
			Map<String, Object> headers = (Map<String, Object>) request.getAttribute("payload");

			Record record = new Record();
			record.setGameId(gameId);
			record.setUserId(headers.get("userId").toString());
			record.setScore(score);
			record.setTime(time);
			record.setCreateTime(DateUtil.getCurrentDate(null));
			recordService.insertRecord(record);

			return ResponseBuilder.success();
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseBuilder.error("查询游戏记录异常");
		}
	}
}
