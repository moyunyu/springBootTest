package com.zcx.test.utils;

import java.util.HashMap;

public class ResponseBuilder {

	private static final String RET_CODE = "retCode";
	private static final String RET_MSG = "retMsg";
	private static final String DATA = "data";
	private static final String CURRENT_PAGE = "currentPage";
	private static final String TOTAL_NUM = "totalNum";

	private static final String SUCCESS_CODE = "000000";
	private static final String SUCCESS_MSG = "success";
	private static final String ERROR_CODE = "999999";

	public static HashMap<String, Object> success() {
		HashMap<String, Object> result = new HashMap<>();
		result.put(RET_CODE, SUCCESS_CODE);
		result.put(RET_MSG, SUCCESS_MSG);
		return result;
	}

	public static HashMap<String, Object> success(Object object) {
		HashMap<String, Object> result = new HashMap<>();
		result.put(RET_CODE, SUCCESS_CODE);
		result.put(RET_MSG, SUCCESS_MSG);
		result.put(DATA, object);
		return result;
	}

	public static HashMap<String, Object> success(Object object, int currentPage, int totalNum) {
		HashMap<String, Object> result = new HashMap<>();
		result.put(RET_CODE, SUCCESS_CODE);
		result.put(RET_MSG, SUCCESS_MSG);
		result.put(DATA, object);
		result.put(CURRENT_PAGE, currentPage);
		result.put(TOTAL_NUM, totalNum);
		return result;
	}

	public static HashMap<String, Object> error(String retMsg) {
		HashMap<String, Object> result = new HashMap<>();
		result.put(RET_CODE, ERROR_CODE);
		result.put(RET_MSG, retMsg);
		return result;
	}

	public static HashMap<String, Object> error(String retCode, String retMsg) {
		HashMap<String, Object> result = new HashMap<>();
		result.put(RET_CODE, retCode);
		result.put(RET_MSG, retMsg);
		return result;
	}

	public static HashMap<String, Object> error(MyException e) {
		HashMap<String, Object> result = new HashMap<>();
		result.put(RET_CODE, e.getErrorCode());
		result.put(RET_MSG, e.getErrorMsg());
		return result;
	}

	public static HashMap<String, Object> error(Throwable e) {
		HashMap<String, Object> result = new HashMap<>();
		result.put(RET_CODE, ERROR_CODE);
		result.put(RET_MSG, e.getMessage());
		return result;
	}
}
