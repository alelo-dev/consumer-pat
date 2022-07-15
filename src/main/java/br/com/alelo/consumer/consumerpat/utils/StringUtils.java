package br.com.alelo.consumer.consumerpat.utils;

public class StringUtils {

    private StringUtils() {}

    public static Boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
    public static Boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

}
