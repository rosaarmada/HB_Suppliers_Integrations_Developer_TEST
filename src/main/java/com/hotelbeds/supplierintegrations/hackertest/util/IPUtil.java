package com.hotelbeds.supplierintegrations.hackertest.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {	
	public static String getIpFromRequest(HttpServletRequest request) {
		   String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		   if (ipAddress == null) {  
		   ipAddress = request.getRemoteAddr();  
		   }

	return	 request.getRemoteAddr();


}
	
	public static String getDateFromRequest(HttpServletRequest request) {
		   String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		   if (ipAddress == null) {  
		   ipAddress = request.getRemoteAddr();  
		   }

	return	 request.getRemoteAddr();


}
	

}
