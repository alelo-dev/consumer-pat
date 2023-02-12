package br.com.alelo.consumer.consumerpat.security.authservice.interceptor;

import br.com.alelo.consumer.consumerpat.security.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

@Log4j2
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private static final String AUTH_HEADER_PARAMETER_AUTHORIZATION = "authorization";

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Boolean isValidBasicAuthRequest = false;

        try {

            // Grab basic header.
            String basicAuthHeaderValue = request.getHeader(AUTH_HEADER_PARAMETER_AUTHORIZATION);

            String[] authWhiteList = {"/user", "/swagger-ui", "/v3"};

            boolean checkAuth = !Stream.of(authWhiteList)
                    .anyMatch(a -> request.getRequestURI().toLowerCase().startsWith(a.toLowerCase()));

            // Process basic authentication
            if (checkAuth) {
                isValidBasicAuthRequest = authService.validateBasicAuthentication(basicAuthHeaderValue);
            }else{
                isValidBasicAuthRequest = true;
            }

            // Check if is valid
            if (!isValidBasicAuthRequest) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }

        } catch (Exception e) {
            log.error("Error while authenticating request", e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        return isValidBasicAuthRequest;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

}