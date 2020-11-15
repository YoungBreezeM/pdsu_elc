package com.tgroup.controller;

import com.tgroup.domain.Permission;
import com.tgroup.domain.Result;
import com.tgroup.domain.ResultType;
import com.tgroup.domain.Role;
import com.tgroup.pojo.User;
import com.tgroup.service.UserService;
import com.tgroup.utils.TokenUtil;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @author yqf
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @deprecated
     * 此例子为模板用例，请完善
     * */
    @PostMapping("/login")
    public ResponseEntity<Result> login(@Validated(Select.class) @RequestBody User user){
        String login = userService.login(user);

        HashMap<String, String> rsJson = new HashMap<>(10);
        rsJson.put("token",login);

        if(login==null){
            return new ResponseEntity<>(new Result(ResultType.WrongPassWord,rsJson), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.Success,rsJson),HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<Result> getAllUser(HttpServletRequest request){

        if(Permission.checkSystemAdmin(request)){
            List<User> allUser = userService.getAllUser();
            return new ResponseEntity<>(new Result(ResultType.Success,allUser),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.NotPermission),HttpStatus.OK);
    }

    @PutMapping("/addUser")
    public ResponseEntity<Result> addUser(@Validated(Insert.class) @RequestBody User user,HttpServletRequest request){

        if(!Permission.checkSystemAdmin(request,user)){
            return new ResponseEntity<>(new Result(ResultType.NotPermission),HttpStatus.OK);
        }

        List<User> oneByUserName = userService.findOneByUserName(user.getUsername());

        if(oneByUserName!=null){
            return new ResponseEntity<>(new Result(ResultType.UserNameHasRegistered),HttpStatus.OK);
        }

        Boolean aBoolean = userService.addUser(user);

        if(aBoolean){
            return new ResponseEntity<>(new Result(ResultType.AddSuccess),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.AddFail),HttpStatus.OK);

    }

    @PostMapping("/updateUser")
    public ResponseEntity<Result> updateUser(@Validated(Update.class) @RequestBody User user,HttpServletRequest request){


        if(!Permission.checkSystemAdmin(request)){
            return new ResponseEntity<>(new Result(ResultType.NotPermission),HttpStatus.OK);
        }

        User oneById = userService.findOneById(user.getId());

        if(oneById!=null){
            if(user.getUsername().equals(oneById.getUsername())){
                Boolean aBoolean = userService.updateUser(user);

                if(aBoolean){
                    return new ResponseEntity<>(new Result(ResultType.UpdateSuccess),HttpStatus.OK);
                }
            }else {
                List<User> oneByUserName = userService.findOneByUserName(user.getUsername());

                if(oneByUserName!=null){
                    return new ResponseEntity<>(new Result(ResultType.UserNameHasRegistered),HttpStatus.OK);
                }
            }
        }


        return new ResponseEntity<>(new Result(ResultType.UpdateFail),HttpStatus.OK);

    }

    @PutMapping("/addAdmin")
    public ResponseEntity<Result> addAdmin(@RequestBody User user){


        List<User> allUser = userService.getAllUser();

        if(allUser.size()==0){

            user.setRole(Role.SYSTEM_ADMIN);
            Boolean aBoolean = userService.addUser(user);

            if (aBoolean){
                return new ResponseEntity<>(new Result(ResultType.AddSuccess),HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(new Result(ResultType.AddFail),HttpStatus.OK);

    }

    @GetMapping("/checkAdmin")
    public ResponseEntity<Result> checkAdmin(){


        List<User> allUser = userService.getAllUser();

        if(allUser.size()==0){
            return new ResponseEntity<>(new Result(ResultType.Success,false),HttpStatus.OK);
        }


        return new ResponseEntity<>(new Result(ResultType.Success,true),HttpStatus.OK);

    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Result> deleteUser(@Validated(Delete.class) @RequestBody User user, HttpServletRequest request){


        if(!Permission.checkSystemAdmin(request,user)){
            return new ResponseEntity<>(new Result(ResultType.NotPermission),HttpStatus.OK);
        }

        User oneById = userService.findOneById(user.getId());

        if(oneById!=null){
            Boolean aBoolean = userService.deleteUser(user.getId());
            if (aBoolean){
                return new ResponseEntity<>(new Result(ResultType.DeleteSuccess),HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(new Result(ResultType.DeleteFail),HttpStatus.OK);

    }

    @GetMapping("/checkToken")
    public ResponseEntity<Result> checkToken(HttpServletRequest request){
        if(!Permission.checkToken(request)){
            return new ResponseEntity<>(new Result(ResultType.NotToken),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.Success),HttpStatus.OK);
    };


}
