package com.bejlka.filter;

import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Log4j
@WebFilter(filterName = "Authorization", urlPatterns = "/products")
public class FilterAuthorization implements Filter {


    private final String login = "admin";
    private final String password = "1234";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        String originalInput = "<" + login + ">:<" + password + ">";
//        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
//        token = "PGFkbWluPjo8MTIzND4="
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Enter authorization");
        String auth = ((HttpServletRequest) servletRequest).getHeader("Authorization");

        if (auth != null) {
            byte[] s = Base64.getDecoder().decode(auth.split(" ")[1]);
            String loginPassword = "<" + login + ">:<" + password + ">";
            if (loginPassword.equals(new String(s))) {
                log.info("authorization success");
                filterChain.doFilter(servletRequest, servletResponse);
                log.info("Exit authorization");
                return;
            }
        }

        log.info("authorization failed.\t Token invalid");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{msg: 'ошибка авторизации'}");
        log.info("Exit authorization");

    }

    @Override
    public void destroy() {

    }
}
