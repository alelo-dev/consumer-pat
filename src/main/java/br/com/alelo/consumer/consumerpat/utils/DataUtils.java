package br.com.alelo.consumer.consumerpat.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class DataUtils {

    public static BigDecimal getCashbackValue(double value) {
        Double cashback  = (value / 100) * 10;
        value = value - cashback;

        return new BigDecimal(value);
    }

    public static BigDecimal increaseTaxValue(double value) {
        Double tax  = (value / 100) * 35;
        value = value + tax;

        return new BigDecimal(value);
    }

    public static String getJsonFromResource(String path) throws IOException {

        ClassPathResource json = new ClassPathResource(path);
        try (InputStream in = json.getInputStream()){
            return StreamUtils.copyToString(in, StandardCharsets.UTF_8);
        }
    }
}
