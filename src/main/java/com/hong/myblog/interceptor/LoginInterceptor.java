package com.hong.myblog.interceptor;

import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 登录过滤拦截
 * 继承HandlerInterceptorAdapter适配器，重写预处理方法preHandle
 * 对session进行判断，看是否有用户，没有的话重定向到登录页面，给拦截掉
 * 还需要指定拦截的内容
 * @author: hjx
 * @time: 2021年05月10日 19:47
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user")==null){
            response.sendRedirect("/admin");
            return false ;
        }
        return true;
    }
}
