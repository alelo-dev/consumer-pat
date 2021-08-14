package br.com.alelo.consumer.consumerpat.util;

public class Validation {

    /**
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj){
        return obj == null;
    }

    /**
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj){
        return obj != null;
    }
}
