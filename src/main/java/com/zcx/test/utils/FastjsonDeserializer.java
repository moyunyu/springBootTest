package com.zcx.test.utils;

import com.alibaba.fastjson.JSONObject;

import io.jsonwebtoken.io.DeserializationException;
import io.jsonwebtoken.io.Deserializer;

/**
 * 基于fastjson的反序列化
 * 
 * @author 31808
 *
 * @param <T>
 */
public class FastjsonDeserializer<T> implements Deserializer<T> {

	@SuppressWarnings("unchecked")
	@Override
	public T deserialize(byte[] bytes) throws DeserializationException {
		return (T) JSONObject.parse(bytes);
	}
}
