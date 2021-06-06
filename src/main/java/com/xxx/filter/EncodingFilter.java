package com.xxx.filter;

import javax.servlet.*;
import java.io.IOException;

//乱码过滤器
public class EncodingFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        chain.doFilter(request,response);
    }

    public void destroy() {
    }
}
