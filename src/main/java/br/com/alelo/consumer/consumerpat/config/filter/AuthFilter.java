package br.com.alelo.consumer.consumerpat.config.filter;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 10:41
 */

import br.com.alelo.consumer.consumerpat.entity.AuthToken;
import br.com.alelo.consumer.consumerpat.respository.AuthTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Component
@Order(1)
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String recevedToken = httpServletRequest.getHeader("token");
        if(Objects.isNull(recevedToken)){
            httpServletResponse.sendRedirect("/auth/error/MISSING_TOKEN");
        }else{
            Optional<AuthToken> optionalAuthToken = this.authTokenRepository.findByToken(recevedToken);
            if(optionalAuthToken.isPresent()){
                AuthToken authToken = optionalAuthToken.get();
                LocalDateTime tokenValidation = authToken.getCreatedAt().plusHours(8L);
                if(LocalDateTime.now().isAfter(tokenValidation)){
                    httpServletResponse.sendRedirect("/auth/error/EXPIRED");
                }else{
                    doFilter(httpServletRequest, httpServletResponse, filterChain);
                }
            }else{
                httpServletResponse.sendRedirect("/auth/error/EXPIRED");
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return !path.startsWith("/consumers");
    }

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        System.out.printf(servletRequest.getServletContext().get("/consumers"));
//        if(servletRequest.getRequestDispatcher("/consumers").toString().contains("/consumers")){
//            String recevedToken = servletRequest.getParameter("token");
//            if(Objects.isNull(recevedToken)){
//                throw new AuthenticationException();
//            }
//
//            Optional<AuthToken> optionalAuthToken = this.authTokenRepository.findByToken(recevedToken);
//            if(optionalAuthToken.isPresent()){
//                AuthToken authToken = optionalAuthToken.get();
//                LocalDateTime tokenValidation = authToken.getCreatedAt().plusHours(8L);
//                if(authToken.getCreatedAt().isAfter(tokenValidation)){
//                    throw new AuthenticationException();
//                }
//            }
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
}
