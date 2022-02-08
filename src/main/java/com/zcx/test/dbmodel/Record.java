package com.zcx.test.dbmodel;

import lombok.Data;

@Data
public class Record {

	private String recordId;

	private String userId;

	private String gameId;

	private String createTime;

	private int score;

	private Float time;
}
