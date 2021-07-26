package br.com.alelo.consumer.consumerpat.util;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 17/06/2021 | 07:53
 */

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Applog {

    private Applog() {}

    public static void infoStart(String className, String methodName){
        log.info(
                "starting class: "
                        .concat(className)
                        .concat(" method ")
                        .concat(methodName
                                .concat("()")));
    }

    public static void infoEnd(String className, String methodName){
        log.info(
                "complete request on class: "
                        .concat(className)
                        .concat(" method ")
                        .concat(methodName
                                .concat("()")));
    }
}
