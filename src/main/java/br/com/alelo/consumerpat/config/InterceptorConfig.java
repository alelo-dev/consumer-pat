package br.com.alelo.consumerpat.config;

import br.com.alelo.consumerpat.core.exception.ForbiddenException;
import br.com.alelo.consumerpat.core.usecase.AuthorizationTokenUseCase;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class InterceptorConfig implements HandlerInterceptor {

    private AuthorizationTokenUseCase authorizationTokenUseCase;

    public InterceptorConfig(AuthorizationTokenUseCase authorizationTokenUseCase) {
        this.authorizationTokenUseCase = authorizationTokenUseCase;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            String authorization = request.getHeader("Authorization");
            this.authorizationTokenUseCase.validateToken(authorization);

            return true;
        } catch (Exception e) {
            throw new ForbiddenException();
        }
    }
}
