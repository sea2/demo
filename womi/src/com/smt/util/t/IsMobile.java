package com.smt.util.t;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsMobile {
	
	
	  //��������ʽƥ��������ֻ������Ƿ���ȷ
    public static boolean isMobileNO(String mobiles) {
   	
   	String regExp = "^13[0-9]{1}[0-9]{8}$|15[0125689]{1}[0-9]{8}$|18[0-3,5-9]{1}[0-9]{8}$";
   	 Pattern p = Pattern.compile(regExp);
       Matcher m = p.matcher(mobiles); 
       return m.matches();  
   }
}
