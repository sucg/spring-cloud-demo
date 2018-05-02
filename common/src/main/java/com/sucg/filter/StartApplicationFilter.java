package com.sucg.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class StartApplicationFilter implements Filter{

    public static String appPort = "";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        appPort = httpServletRequest.getServerPort() + "";
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
