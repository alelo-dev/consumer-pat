package br.com.alelo.consumer.consumerpat.util;

public class UtilRequests {

	public static String getBeginMethod() {
		return "BEGIN: " + Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	public static String getEndMethod() {
		return "END:  " + Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
	public static String getBeginMethod(Object params) {
		
		if(params == null)
			return getBeginMethod();
		return "BEGIN: " + Thread.currentThread().getStackTrace()[2].getMethodName() + ", PARAMS: " + params.toString();
	}

	public static String getEndMethod(Object result) {
		
		if(result == null)
			return getBeginMethod();
		return "END:  " + Thread.currentThread().getStackTrace()[2].getMethodName() + ", RESULT: " + result.toString();
	}
}
