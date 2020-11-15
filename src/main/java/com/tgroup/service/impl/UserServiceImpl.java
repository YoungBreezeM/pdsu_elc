package com.tgroup.service.impl;

import com.tgroup.mapper.UserMapper;
import com.tgroup.pojo.User;
import com.tgroup.service.UserService;
import com.tgroup.utils.Md5util;
import com.tgroup.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yqf
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public String login(User user) {
        user.setPassword(Md5util.encryption(user.getPassword()));

        User checkUser = userMapper.findOneByUserNameAndPassword(user);

        if(checkUser!=null){
            return TokenUtil.sign(checkUser);
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {


        return userMapper.findAll();
    }

    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public Boolean addUser(User user) {
        user.setPassword(Md5util.encryption(user.getPassword()));
        userMapper.addRecord(user);
        return true;
    }

    @Override
    public Boolean updateUser(User user) {
        User oneById = userMapper.findOneById(user.getId());
        if(!oneById.getPassword().equals(user.getPassword())){
            user.setPassword(Md5util.encryption(user.getPassword()));
        }

        userMapper.updateRecord(user);
        return true;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        userMapper.deleteRecord(id);
        return true;
    }

    @Override
    public List<User> findOneByUserName(String username) {
        List<User> oneByUserName = userMapper.findOneByUserName(username);

        if (oneByUserName.size()>0){
            return oneByUserName;
        }

        return null;
    }

    @Override
    public User findOneById(Integer id) {

        return userMapper.findOneById(id);
    }
}
