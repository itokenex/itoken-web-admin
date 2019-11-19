package com.funtl.itoken.web.admin.interceptor;

import com.funtl.itoken.web.admin.domain.User;
import com.funtl.itoken.web.admin.service.RedisService;
import com.funtl.itoken.web.admin.util.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request,"token");
        String url = request.getRequestURI();
        System.out.println("url:"+url);
        if(StringUtils.isBlank(token)){
            response.sendRedirect("http://localhost:9503/login?url=http://localhost:9601/getUser");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            if(modelAndView!=null){//已登录状态
                modelAndView.addObject("user",user);
            }
        }else{//未登录状态,去redis找
            String token = CookieUtils.getCookieValue(request, "token");
            if(token!=null) {
                String accountId = redisService.getData(token);
                if(accountId!=null){
                    String name = redisService.getData(accountId);
                    if(name!=null){//已登录
                        user=new User();
                        user.setAccountId(accountId);
                        user.setName(name);
                        session.setAttribute("user",user);


                        if(modelAndView!=null){//已登录状态
                            modelAndView.addObject("user",user);
                        }
                    }
                }
            }
        }

        //二次确认
        if(user==null){
            response.sendRedirect("http://localhost:9503/login?url=http://localhost:9601/getUser");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
