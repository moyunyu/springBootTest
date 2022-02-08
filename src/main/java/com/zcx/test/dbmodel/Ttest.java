package com.zcx.test.dbmodel;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Ttest {

	private BigDecimal id1;
	
	private int text;
	
	private String type;
	
	private BigDecimal amt;
	
	@Override
	public String toString() {
		return "id1:"+this.id1.toString()+",test:"+this.text+",type:"+this.type+",amt:"+this.amt.toString();
	}

}
