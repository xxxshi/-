package com.erpin.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Package: ${PACKAGE_NAME}
 * <p>
 * Description： TODO
 * <p>
 * Author: 连洁
 * <p>
 * Date: Created in 2018/12/17 23:49
 * <p>
 * Company: FJNU
 * <p>
 * Copyright: Copyright (c) 2017
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
public class CharactorEncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
