package com.zcx.test.utils;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;

/**
 * .令牌工具类, 内置一个默认密钥(HmacSHA256)
 * 
 */
public class JwtHelper {

	/**
	 * 默认的密钥， HmacSHA256
	 */
	private final static String base64Key = "YQnv6NyP8Kwi6mxwMs++CyjfI4g+N+T8FJcG1QmDo2s=";

	/**
	 * 采用默认的密钥创建token
	 * 
	 * @param payload
	 *            token携带的信息
	 * @param expiration
	 *            过期时间
	 * @param issuedAt
	 *            创建时间
	 * @return
	 */
	public static String builder(Map<String, Object> payload, Date expiration, Date issuedAt) {
		SecretKey key = toHmacSHA256(base64Key);
		return builder(key, payload, expiration, issuedAt);
	}

	/**
	 * 
	 * @param key
	 *            密钥
	 * @param payload
	 *            token携带的信息
	 * @param expiration
	 *            过期时间
	 * @param issuedAt
	 *            创建时间
	 * @return
	 */
	public static String builder(SecretKey key, Map<String, Object> payload, Date expiration, Date issuedAt) {
		Claims claims = new DefaultClaims(payload);
		claims.setIssuedAt(issuedAt);
		claims.setExpiration(expiration);
		JwtBuilder builder = Jwts.builder();
		builder.setClaims(claims);
		String token = builder.signWith(key).compact();
		return token;
	}

	/**
	 * 验证、解析token, 采用默认的密钥验签
	 * 
	 * @param token
	 * @return
	 */
	public static Map<String, Object> parser(String token) {
		SecretKey key = toHmacSHA256(base64Key);
		return parser(token, key);
	}

	/**
	 * 验证、解析token
	 * 
	 * @param token
	 * @param key
	 *            验签密钥
	 * @return
	 */
	public static Map<String, Object> parser(String token, SecretKey key) {
		JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
		Jws<Claims> jws = parser.parseClaimsJws(token);
		Claims claims = jws.getBody();
		return claims;
	}

	public static SecretKey toHmacSHA256(String base64Key) {
		byte[] bytekey = Base64.getDecoder().decode(base64Key.getBytes());
		SecretKey key = new SecretKeySpec(bytekey, "HmacSHA256");
		return key;
	}
}
