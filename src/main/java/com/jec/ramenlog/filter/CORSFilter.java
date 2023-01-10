package com.jec.ramenlog.filter;


import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by @author 卞凌志
 * on 2022/12/06 15:03
 */
@WebFilter("/*")
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String origin = request.getHeader("Origin");
        if (StringUtils.hasText(origin)) {
            resp.addHeader("Access-Control-Allow-Origin", origin);
        }
        // 允许带有cookie访问
        resp.addHeader("Access-Control-Allow-Credentials", "true");

        // 告诉浏览器允许跨域访问的方法
        resp.addHeader("Access-Control-Allow-Methods", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");

        String headers = request.getHeader("Access-Control-Request-Headers");
        if (StringUtils.hasText(headers)) {
            resp.addHeader("Access-Control-Allow-Headers", headers);
        }

        resp.addHeader("Access-Control-Max-Age", "3600");

        chain.doFilter(request, resp);
    }


}

