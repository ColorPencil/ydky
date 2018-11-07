package com.hydee.ydky.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理文本字符工具类
 * 
 * ====================================
 * @author 	:LuoF
 * @date 	:2018年3月29日
 * @version :1.0
 * @remark	:
 * ====================================
 */
public class StringUtils {
	private static final String AES_PUBLIC_KEY = "yvbbMVSS@eGBz%5L";
	
	/**
	 * 根据公钥加密对面待加密明文获取sign签名密文
	 * @param content	:对方私钥
	 * @return
	 */
	public static String encodeSign(String content) {
		try {
			byte[] b = AESUtils.encrypt(content.getBytes("utf-8"), AES_PUBLIC_KEY.getBytes("utf-8"));
			return Base64.getEncoder().encodeToString(b).trim();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
    /**
     * 转16进制
     * @param bytes
     * @return
     */
    public static String byteToHexString(byte[] bytes) {  
        StringBuffer sb = new StringBuffer(bytes.length);  
        String sTemp;  
        for (int i = 0; i < bytes.length; i++) {  
            sTemp = Integer.toHexString(0xFF & bytes[i]);  
            if (sTemp.length() < 2)  
                sb.append(0);  
            sb.append(sTemp.toUpperCase());  
        }  
        return sb.toString();  
    }  
    
    /**
     * 将十六进制转换为字节数组 
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBinary(String hexString){  
		//hexString的长度对2取整，作为bytes的长度  
		int len = hexString.length()/2;  
		byte[] bytes = new byte[len];  
		byte high = 0;//字节高四位  
		byte low = 0;//字节低四位  
		for(int i=0;i<len;i++){  
			//右移四位得到高位  
			high = (byte)((hexString.indexOf(hexString.charAt(2*i)))<<4);  
			low = (byte)hexString.indexOf(hexString.charAt(2*i+1));  
			bytes[i] = (byte) (high|low);//高地位做或运算  
		}  
		return bytes;  
    }
	
    /**
    * 获取异常的堆栈信息
    * @param t
    * @return
    */
    public static String getStackTrace(Throwable t) {
	    StringWriter sw = null;
	    PrintWriter pw = null;
	    try {
	    	sw = new StringWriter();
	    	pw = new PrintWriter(sw);
	    	t.printStackTrace(pw);
	    	return sw.toString();
	    } catch(Exception ex) {
	    	return null;
	    } finally {
	    	try {
				sw.close();
				pw.close();
				sw = null;
				pw = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
    }
    
    /**
     * 判断是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
    	return (str == null || "".equals(str));
    }
    
    /**
     * 判断多个字符串是否存在一个为空
     * @param args
     * @return
     */
    public static boolean isAnyEmpty(String...args) {
    	for (String str : args) {
			if(isEmpty(str)) return true;
		}
    	return false;
    }
    
    /**
     * 判断多个字符串是否全部为空
     * @param args
     * @return
     */
    public static boolean isAllEmpty(String...args) {
    	for (String str : args) {
			if(!isEmpty(str)) return false;
		}
    	return true;
    }
    
    /**
     * 利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     */
    public static String encodeMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(str.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 转16进制
     * @param bytes
     * @return
     */
    public static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    /**
     * 匹配api接口地址uri
     * @param requestURI
     * @return
     */
    private static final Pattern API_URI_PATTERN = Pattern.compile("/api(/\\w+){1,2}");
	public static String getApiUrl(String requestURI) {
		if(isEmpty(requestURI)) return requestURI;
		Matcher m = API_URI_PATTERN.matcher(requestURI);
		if(m.find()) return m.group();
		return null;
	}
	
}
