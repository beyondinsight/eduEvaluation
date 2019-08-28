package com.philip.edu.eval.util;


import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public  class Code
{
//private Properties propConfig = PropertiesUtil.getProperty("config");
//私钥： AES固定格式为128/192/256 bits.即：16/24/32bytes。DES固定格式为128bits，即8bytes。
static String key = "tianma_shcim_api";  
//初始化向量参数，AES 为16bytes. DES 为8bytes.
static String ivs   = "shanghai_tianma_"; 
//2：客户端密码加密算法：
public static String encrypt(String plainText,String key,String ivs){
	try {
		Key keySpec = new SecretKeySpec(key.getBytes(), "AES");//两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES  			
		IvParameterSpec ivSpec = new IvParameterSpec(ivs.getBytes());  // 初始化向量  
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 实例化加密类，参数为加密方式，要写全
		// 初始化，此方法可以采用三种方式，按服务器要求来添加。  
		//（1）无第三个参数  
		//（2）第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)  
		//（3）采用此代码中的IVParameterSpec
		cipher.init(Cipher.ENCRYPT_MODE,  keySpec, ivSpec);
		//加密操作,返回加密后的字节数组，然后需要编码。主要编解码方式有Base64, HEX, UUE,7bit等等。  
		byte [] b = cipher.doFinal(plainText.getBytes());
		BASE64Encoder encoder = new BASE64Encoder(); 
		String ret = encoder.encode(b);   //Base64、HEX等编解码  
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//3：服务端密码解密算法：
  public static String deEncrypt(String textDeCipher,String keySpec,String ivs){
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			//先用Base64解码  
			byte [] byte1 = decoder.decodeBuffer(textDeCipher);
			IvParameterSpec ivSpec = new IvParameterSpec(ivs.getBytes());
			Key key = new SecretKeySpec(keySpec.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			//与加密时不同MODE:Cipher.DECRYPT_MODE
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
			byte [] ret = cipher.doFinal(byte1);
			return new String(ret, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
  	return null;
	}
  
  
  public static void main(String[] args) throws UnsupportedEncodingException {  
    System.out.println(deEncrypt("FmdVAK5EZDPS62TqSp9enw==",key,ivs));
    System.out.println(encrypt("Bi123456",key,ivs));
       
  }  
}