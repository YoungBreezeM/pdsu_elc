package com.tgroup.service;

import com.tgroup.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yqf
 */
@Service
public interface UserService {

    /**
     * user login
     * @param user entity
     * @return string
     * */
    String login(User user);

    /**
     * get all user list
     * @return list
     * */
    List<User> getAllUser();

    /**
     * user register
     * @param user to
     * @return  user rs
     * */
    User register(User user);

    /**
     * user add
     * @param user e
     * @return b
     * */
    Boolean addUser(User user);

    /**
     * user update
     * @param user e
     * @return b
     * */
    Boolean updateUser(User user);

    /**
     * user delete
     * @param id user id
     * @return b
     * */
    Boolean deleteUser(Integer id);

    /***
     * find one by username
     * @param username
     * @return list
     * */
    List<User> findOneByUserName(String username);

    /***
     * find one by id
     * @param id
     * @return user
     * */
    User findOneById(Integer id);
}
