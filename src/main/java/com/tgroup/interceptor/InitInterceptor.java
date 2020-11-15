package com.tgroup.interceptor;


import com.tgroup.config.DataSourceConfig;
import com.tgroup.domain.MysqlConfig;
import com.tgroup.domain.Result;
import com.tgroup.domain.ResultType;
import com.tgroup.mapper.UserMapper;
import com.tgroup.pojo.User;
import com.tgroup.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

/**
 * @author yqf
 */
@Slf4j
@Component
public class InitInterceptor implements HandlerInterceptor {

   @Autowired
   private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        List<User> all = userMapper.findAll();

        if(all.size()>0){
            return true;

        }else {
            JSONObject json=new JSONObject();
            json.put("msg","参数未配置");
            json.put("code","501");
            response.getWriter().append(json.toString());
        }

        return false;

    }
}
