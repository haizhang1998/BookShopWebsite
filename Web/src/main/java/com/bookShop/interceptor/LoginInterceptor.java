package com.bookShop.interceptor;

import com.haizhang.entity.UserInfo;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    static final String IGNORE_URL[]={"login","regist"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Login_preHandle"+request.getRequestURI()+":"+request.getRequestURL());

        HttpSession session= request.getSession();
        for(int i=0;i<IGNORE_URL.length;i++){
            if(request.getRequestURI().contains(IGNORE_URL[i]))return true;
        }
        UserInfo userInfo =(UserInfo) session.getAttribute("user");
        if(userInfo!=null)return true;
        request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Login_postHandle"+request.getRequestURI()+":"+request.getRequestURL());
//        System.out.println("modelAndView"+modelAndView.getViewName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Login_afterCompletion"+request.getRequestURI());
    }
}
