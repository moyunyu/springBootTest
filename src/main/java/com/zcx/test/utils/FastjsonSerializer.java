package com.zcx.test.utils;

import com.alibaba.fastjson.JSONObject;

import io.jsonwebtoken.io.SerializationException;
import io.jsonwebtoken.io.Serializer;

public class FastjsonSerializer<T> implements Serializer<T> {

	@Override
	public byte[] serialize(T t) throws SerializationException {
		return JSONObject.toJSONBytes(t);
	}
}
