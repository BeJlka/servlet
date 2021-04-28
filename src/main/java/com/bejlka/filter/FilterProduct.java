package com.bejlka.filter;

import com.bejlka.util.ServletUtil;
import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j
@WebFilter(filterName = "Product", urlPatterns = "/*")
public class FilterProduct implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Enter logger");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        MultipleReadHttpRequest wrappedRequest = new MultipleReadHttpRequest(request);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Method: [").append(request.getMethod()).append("]\t");
        stringBuilder.append("URL: ").append(request.getRequestURL());
        if (request.getParameter("id") != null) {
            stringBuilder.append("?id=").append(request.getParameter("id"));
        }
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            stringBuilder.append("\tBody: ").append(ServletUtil.getBody(wrappedRequest));
        }
        log.debug(stringBuilder);
        filterChain.doFilter(wrappedRequest, servletResponse);
        log.info("Exit logger");
    }


    @Override
    public void destroy() {

    }
}
