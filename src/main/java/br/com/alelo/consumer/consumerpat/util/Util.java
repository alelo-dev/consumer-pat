package br.com.alelo.consumer.consumerpat.util;

public class Util {

	public static boolean isNull(final Object obj) {
		return obj == null;
	}
	
	public static boolean isEmpty(final Object obj) {
		if( obj instanceof String) {
			return isEmptyString((String) obj);
		}
		
		if( obj instanceof String) {
			return isEmptyNumber((Number) obj);
		}
		
		return isNull(obj);
	}
	
	public static boolean isEmptyString(final String value) {
		return value.isEmpty();
	}
	
	public static boolean isEmptyNumber(final Number value) {
		return Double.doubleToRawLongBits(value.doubleValue()) == 0;
	}
}
