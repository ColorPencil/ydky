package com.hydee.ydky.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES-128-ECB PKSC 加密解密工工具类
 * 
 * ====================================
 * @author 	:LuoF
 * @date 	:2018年3月29日
 * @version :1.0
 * @remark	:
 * ====================================
 */
public class AESUtils {
	
	/**
	  * 随机生成秘钥
	  */
	public static String createAES128Key() {
		try {
		  KeyGenerator kg = KeyGenerator.getInstance("AES");
		  kg.init(128);
		  //要生成多少位，只需要修改这里即可128, 192或256
		  SecretKey sk = kg.generateKey();
		  byte[] b = sk.getEncoded();
		  String s = StringUtils.byteToHexString(b);
		  return s;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
    /**
     * 加密
     * @param content
     * @param enckey
     * @return
     */
    public static byte[] encrypt(byte[] content, byte[] enckey) {  
        try {  
            SecretKeySpec key = new SecretKeySpec(enckey, "AES");  
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器  
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化  
            byte[] result = cipher.doFinal(content);  
            return result; // 加密  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
        } catch (InvalidKeyException e) {  
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    /**
     * 解密
     * @param content
     * @param deckey
     * @return
     */
    public static byte[] decrypt(byte[] content, byte[] deckey) {  
        try {  
            SecretKeySpec key = new SecretKeySpec(deckey, "AES");  
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器  
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化  
            byte[] result = cipher.doFinal(content);  
            return result; // 加密  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
        } catch (InvalidKeyException e) {  
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
}
