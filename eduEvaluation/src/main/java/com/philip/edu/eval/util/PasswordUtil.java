package com.philip.edu.eval.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;

import org.apache.shiro.codec.Hex;

import io.jsonwebtoken.SignatureAlgorithm;

public class PasswordUtil {
	public static byte[] createSalt(){
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
			return new String(new Hex().encode(bs));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String createJWT(String id, String issuer, String subject, long ttlMillis){
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		//byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.);
		return null;
	}
	
	public static void main(String[] args){
		String salt = "[B@4e25154f";
		String password = "123";
		String name = "robbin";
		
		String s = PasswordUtil.md5Hex(name + password + salt);
		System.out.println(s);
	}
}
