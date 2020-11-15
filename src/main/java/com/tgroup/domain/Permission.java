package com.tgroup.domain;

import com.tgroup.pojo.User;
import com.tgroup.utils.TokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yqf
 */
public class Permission {

    public static Boolean checkGeneralAdmin(HttpServletRequest request) {
        String token = request.getHeader("token");
        User verify = TokenUtil.verify(token, User.class);

        if (verify != null) {
            return verify.getRole().equals(Role.GENERAL_ADMIN)||verify.getRole()>Role.GENERAL_ADMIN;
        }
        return false;
    }

    public static Boolean checkSystemAdmin(HttpServletRequest request, User user){
        String token = request.getHeader("token");
        User verify = TokenUtil.verify(token, User.class);

        if (verify != null) {
            return verify.getRole().equals(Role.SYSTEM_ADMIN) && verify.getRole() > user.getRole();
        }
        return false;
    }

    public static Boolean checkSystemAdmin(HttpServletRequest request){
        String token = request.getHeader("token");
        User verify = TokenUtil.verify(token, User.class);

        if (verify != null) {
            return verify.getRole().equals(Role.SYSTEM_ADMIN);
        }
        return false;
    }

    public static Boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        User verify = TokenUtil.verify(token, User.class);

        if (verify != null) {
            return true;
        }
        return false;
    }
}
