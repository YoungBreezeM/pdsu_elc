package com.tgroup.interceptor;


import com.tgroup.mapper.UserMapper;
import com.tgroup.pojo.User;
import com.tgroup.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yqf
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private String options = "OPTIONS";

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if(options.equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");


        String token = request.getHeader("token");
        if (token!=null){
            try {
                User verify = TokenUtil.verify(token, User.class);
                System.out.println(verify);
                if (verify!=null){
                    User oneByUserNameAndPassword = userMapper.findOneByUserNameAndPassword(verify);
                    if(oneByUserNameAndPassword!=null){
                        System.out.println("通过拦截器");
                        return true;
                    }else {
                        System.out.println("用户权限无法通过");
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        try {
            JSONObject json=new JSONObject();
            json.put("msg","token verify fail");
            json.put("code","500");
            response.getWriter().append(json.toString());
            System.out.println("认证失败，未通过拦截器");
        } catch (Exception e) {
            return false;
        }
        /**
         * 还可以在此处检验用户存不存在等操作
         */
        return false;
    }
}
