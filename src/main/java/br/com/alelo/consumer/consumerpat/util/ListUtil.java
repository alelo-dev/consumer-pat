package br.com.alelo.consumer.consumerpat.util;
import java.util.ArrayList;
import java.util.Collection;

public class ListUtil {
    public static <T> Collection<T> nonNull(Collection<T> list) {
        return list != null ? list : new ArrayList<>();
    }
}
