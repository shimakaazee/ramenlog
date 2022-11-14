package com.jec.ramenlog.filter;

import com.alibaba.fastjson.JSON;
import com.jec.ramenlog.common.BaseContext;
import com.jec.ramenlog.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * check login status
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter{

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1、get url
        String requestURI = request.getRequestURI();// /backend/index.php

        log.info("get request：{}",requestURI);

        //no need no filter
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/frontend/**"
        };


        //2、check if need fulter
        boolean check = check(urls, requestURI);

        //3、let go
        if(check){
            log.info("request{} no need filter ",requestURI);
            filterChain.doFilter(request,response);
            return;
        }

        //4、if login, let go
        if(request.getSession().getAttribute("employee") != null){
            log.info("user id为：{}",request.getSession().getAttribute("employee"));

            int empId = (int) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request,response);
            return;
        }

        log.info("user not login");
        //5、if not login, go to login page
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));

    }

    /**
     * check if need filter
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
