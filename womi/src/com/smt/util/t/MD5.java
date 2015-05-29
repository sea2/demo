package com.smt.util.t;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class MD5 {
	public final static String MD5ForString(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法�?MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘�?
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 对文件全文生成MD5摘要
	 * 
	 * @param file
	 *            要加密的文件
	 * @return MD5摘要�?
	 */

	static char hexdigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String MD5ForFile(File file) {

		FileInputStream fis = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			fis = new FileInputStream(file);
			byte[] buffer = new byte[2048];
			int length = -1;
			//long s = System.currentTimeMillis();
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			byte[] b = md.digest();
			return byteToHexString(b);
			// 16位加�?
			// return buf.toString().substring(8, 24);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/** */
	/**
	 * 把byte[]数组转换成十六进制字符串表示形式
	 * @param tmp    要转换的byte[]
	 * @return 十六进制字符串表示形�?
	 */
	private static String byteToHexString(byte[] tmp) {
		String s;
		// 用字节表示就�?16 个字�?
		char str[] = new char[16 * 2]; // 每个字节�?16 进制表示的话，使用两个字符，
		// �?��表示�?16 进制�?�� 32 个字�?
		int k = 0; // 表示转换结果中对应的字符位置
		for (int i = 0; i < 16; i++) { // 从第�?��字节�?��，对 MD5 的每�?��字节
			// 转换�?16 进制字符的转�?
			byte byte0 = tmp[i]; // 取第 i 个字�?
			str[k++] = hexdigits[byte0 >>> 4 & 0xf]; // 取字节中�?4 位的数字转换, 
			// >>> 为�?辑右移，将符号位�?��右移
			str[k++] = hexdigits[byte0 & 0xf]; // 取字节中�?4 位的数字转换
		}
		s = new String(str); // 换后的结果转换为字符�?
		return s;
	}
//	public static void main(String[] args) {
//		System.out.print(MD5.MD5("password"));
//	}
//	public static void main(String arg[]) {
//		System.out.println(getMD5(new File("f:/a.txt")));
//	}
}
