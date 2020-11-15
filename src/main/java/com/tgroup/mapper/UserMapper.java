package com.tgroup.mapper;

import com.tgroup.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * find all user
     * @return list about user
     * */
    @Select("select * from tb_user")
    List<User> findAll();

    /**
     * find user  by id
     * @param id userId
     * @return user
     * */
    @Select("select * from tb_user where id =#{id}")
    User findOneById(@Param("id") Integer id);

    /**
     * find user  by username
     * @param username
     * @return list
     * */
    @Select("select * from tb_user where username =#{username}")
    List<User> findOneByUserName(@Param("username") String username);

    /**
     * find user by username and password
     * @param user entity
     * @return user
     * **/
    @Select("select * from tb_user where username =#{username} and password =#{password}")
    User findOneByUserNameAndPassword(User user);

    /**
     * add user
     * @param user entity
     * */
    @Insert("insert into tb_user(username,password,phone,email,name,role,department) values(#{username},#{password},#{phone},#{email},#{name},#{role},#{department})")
    void addRecord(User user);

    /**
     * delete user
     * @param id user id
     * */
    @Delete("delete from tb_user where id =#{id}")
    void deleteRecord(@Param("id") Integer id);

    /**
     * update user
     * @param user entity
     * */
    @Update("update tb_user set username=#{username},password=#{password},phone=#{phone},email=#{email},name=#{name},role=#{role},department=#{department} where id =#{id}")
    void updateRecord(User user);

}
