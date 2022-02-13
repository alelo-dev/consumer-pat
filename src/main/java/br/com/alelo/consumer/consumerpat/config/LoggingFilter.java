package br.com.alelo.consumer.consumerpat.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.core.log.LogMessage;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoggingFilter extends DispatcherServlet {

    private static final String HTTP_REQUEST_LOG_TEMPLATE = "[REQ] Method: %s | Path: %s | Headers: %s ";
    private static final String HTTP_RESPONSE_LOG_TEMPLATE = "[RES] Status: %s | Payload: %s";
    private static final List<HttpStatus> SUCCESS_STATUS = Arrays.asList(HttpStatus.OK, HttpStatus.CREATED, HttpStatus.NO_CONTENT);

    private final Log logger = LogFactory.getLog(getClass());

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }
        HandlerExecutionChain handler = getHandler(request);

        try {
            super.doDispatch(request, response);
        } finally {
            log(request, response, handler);
            updateResponse(response);
        }
    }

    private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache, HandlerExecutionChain handler) {

        logger.info(String.format(HTTP_REQUEST_LOG_TEMPLATE, requestToCache.getMethod(), requestToCache.getRequestURI(), getRequestHeaders(requestToCache)));

        if(SUCCESS_STATUS.stream().noneMatch(s -> s.equals(HttpStatus.valueOf(responseToCache.getStatus()))))
            logger.error(String.format(HTTP_RESPONSE_LOG_TEMPLATE, responseToCache.getStatus(), getResponsePayload(responseToCache)));
        else
            logger.info(String.format(HTTP_RESPONSE_LOG_TEMPLATE, responseToCache.getStatus(), getResponsePayload(responseToCache)));


    }

    private String getResponsePayload(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = Math.min(buf.length, 5120);
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    // NOOP
                }
            }
        }
        return "[unknown]";
    }

    private String getRequestHeaders(HttpServletRequest request){
        List<Pair> headers = new ArrayList<Pair>();

        request.getHeaderNames().asIterator().forEachRemaining(h -> headers.add(Pair.of(h, request.getHeader(h))));

        return headers.toString();
    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        responseWrapper.copyBodyToResponse();
    }

}
