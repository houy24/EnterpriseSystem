package com.xxx.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

// 登录过滤器
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletRequest.setCharacterEncoding("utf-8");
   //     servletRequest.setCharacterEncoding("utf-8");
   //     servletResponse.setCharacterEncoding("utf-8");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String url = request.getRequestURI();
        String contextPath = request.getContextPath();

        System.out.println("请求的URL为：" + url);

        if(url.equals(contextPath + "/login.jsp") || url.equals(contextPath + "/")
                || url.equals(contextPath + "/userAccountLogin")
                || url.equals(contextPath + "/ChangePas")
        ){
            filterChain.doFilter(servletRequest, servletResponse); // 登录页，放行
        }else if(url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".gif") ){ // .css .js 结尾
            //如果发现是css或者js文件，直接放行
            filterChain.doFilter(servletRequest, servletResponse);
        }else {

            if ("".equals(session.getAttribute("userAccount")) || session.getAttribute("userData") == null) {

                PrintWriter out = response.getWriter();

                /* window.parent.location 能跳出 iframe 层  */
                out.write("<script type=\"text/javascript\" charset=\"utf-8\" >alert('请先登录！');" +
                        "window.parent.location.href=\"/\";</script>");

      //          request.setAttribute("msg", "请先登录！");
//                response.sendRedirect(contextPath + "/");


                //out.close();
                return;
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

    }

    @Override
    public void destroy() {
    }
}
