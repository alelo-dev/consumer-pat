package br.com.alelo.consumer.consumerpat.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Guilherme de Castro
 * @created 01/09/2021 - 14:13
 */
@Component
public final class RequestUtils {
    private RequestUtils() {
    }

    private static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static String getContextPath() {
        String var10000 = getCurrentRequest().getContextPath();
        return var10000 + getCurrentRequest().getServletPath();
    }
}
