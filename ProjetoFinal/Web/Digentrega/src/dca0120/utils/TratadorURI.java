package dca0120.utils;

import javax.servlet.http.HttpServletRequest;

public class TratadorURI {
	
	public static String getRaizURL(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}
	
	public static String getFuncaoURL(HttpServletRequest request) {
		return request.getContextPath();
	}
	

}
