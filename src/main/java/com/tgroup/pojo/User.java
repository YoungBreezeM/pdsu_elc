package com.tgroup.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yqf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @NotNull(groups = {
            Update.class,
            Delete.class
    },message = "id 不能为空")
    private Integer id;

    @NotNull(groups = {
            Update.class,
            Insert.class,
            Select.class,
    },message = "用户名不能为空")
    private String username;

    @NotNull(groups = {
            Update.class,
            Select.class,
            Insert.class,
    },message = "密码 不能为空")
    private String password;

    @NotNull(groups = {
            Update.class,
            Insert.class,
    },message = "手机不能为空")
    private String phone;

    private String email;
    private String name;

    @NotNull(groups = {
            Update.class,
            Insert.class,
    },message = "角色不能为空")
    private Integer role;

    @NotNull(groups = {
            Update.class,
            Insert.class,
    },message = "部门不能为空")
    private String department;

}
