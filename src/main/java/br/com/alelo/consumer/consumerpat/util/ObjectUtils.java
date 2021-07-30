package br.com.alelo.consumer.consumerpat.util;

import br.com.alelo.consumer.consumerpat.exception.ValidationException;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public final class ObjectUtils {

    public static void shouldNotBeNull(Object value, String field) {
        if (org.springframework.util.ObjectUtils.isEmpty(value)) {
            throw new ValidationException(String.format(Constants.FIELD_CANNOT_BE_NULL, field));
        }
    }

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getPropertyNamesWithNullValue(source));
    }

    public static <T> T copyTo(Object source, Class<T> targetClass) {
        return Optional.ofNullable(source)
                .map(it -> {
                    T instance = getInstance(targetClass);
                    BeanUtils.copyProperties(source, instance);
                    return instance;
                })
                .orElse(null);
    }

    @SneakyThrows
    public static <T> T getInstance(Class<T> targetClass) {
        return targetClass.getConstructor().newInstance();
    }

    public static <T> List<T> copyListTo(List<?> source, Class<T> targetClass) {
        return Optional.ofNullable(source).orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(it -> copyTo(it, targetClass))
                .collect(Collectors.toList());
    }


    private static String[] getPropertyNamesWithNullValue(Object source) {
        final BeanWrapperImpl wrapper = new BeanWrapperImpl(source);
        return Arrays.stream(wrapper.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(name -> isNull(wrapper.getPropertyValue(name)))
                .collect(Collectors.toSet())
                .toArray(new String[]{});
    }
}