package com.erp.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @program: book
 * @description: 验证登录
 * @author: xxxshi
 * @create: 2018-11-26 20:27
 * @Version:
 **/

public class CharsetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
