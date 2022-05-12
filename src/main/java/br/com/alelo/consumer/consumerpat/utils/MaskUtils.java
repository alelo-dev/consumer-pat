package br.com.alelo.consumer.consumerpat.utils;

public class MaskUtils {

    public static String removeDocumentNumberMask(String documentNumber) {

        return documentNumber.replaceAll("\\.", "").replaceAll("-", "");
    }

    public static String removeCardNumberMask(String cardNumber) {

        return cardNumber.replaceAll("\\.", "");
    }
}
