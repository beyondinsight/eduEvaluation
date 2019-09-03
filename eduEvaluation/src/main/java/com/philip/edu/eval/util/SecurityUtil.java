package com.philip.edu.eval.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.codec.Hex;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

//import io.jsonwebtoken.SignatureAlgorithm;

public class SecurityUtil {
	private static Properties propConfig = PropertiesUtil.getProperty("config");

	public static byte[] createSalt() {
		byte[] salt = new byte[100];
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.nextBytes(salt);
			return salt;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String md5Hex(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(src.getBytes());
			new Hex();
			return new String(Hex.encode(bs));
		} catch (Exception e) {
			return null;
		}
	}

	public static String createToken(String user_name) throws Exception {
		Date iatDate = new Date();
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.DATE, 10);
		Date expiresDate = nowTime.getTime();

		String secret_key = propConfig.getProperty("secret_key");

		Map<String, Object> map = new HashMap();
		map.put("alg", "HS256");
		map.put("typ", "JWT");

		String token = JWT.create().withHeader(map).withClaim("iss", "Service").withClaim("aud", "APP")
				.withClaim("username", null == user_name ? null : user_name).withIssuedAt(iatDate)
				.withExpiresAt(expiresDate).sign(Algorithm.HMAC256(secret_key));

		return token;
	}

	public static Map<String, Claim> verifyToken(String token) {
		DecodedJWT jwt = null;

		String secret_key = propConfig.getProperty("secret_key");

		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret_key)).build();
		jwt = verifier.verify(token);

		return jwt.getClaims();
	}

	public static void main(String[] args) {
		try {
			String token = SecurityUtil.createToken("candy");
			System.out.println(token);

			Map<String, Claim> claims = SecurityUtil.verifyToken(token);
			Claim user_name_claim = claims.get("username");
			System.out.println(user_name_claim.asString());
			if (null == user_name_claim || StringUtils.isEmpty(user_name_claim.asString())) {
				System.out.println("异常错误！");
			} else {
				System.out.println("验证成功！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
