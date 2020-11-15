package com.tgroup.domain;

/**
 * @author yqf
 */



public enum ResultType {
    /**基础错误类型*/
    Success(0,"查询成功"),
    AddSuccess(0,"添加成功"),
    UpdateSuccess(0,"更新成功"),
    DeleteSuccess(0,"删除成功"),
    /**注册*/
    Has_Registered(100,"对象已经存在"),
    Unregistered(101,"未注册"),
    UnRole(102,"角色错误"),
    WrongPassWord(103,"密码错误"),
    NotFind(104,"无法找到对象"),
    EmptyData(105,"查找数据为空"),
    AddFail(106,"添加失败"),
    UpdateFail(107,"更新成功"),
    DeleteFail(108,"删除失败"),
    /**group*/
    EmailHasRegistered(201,"邮箱已被注册"),
    UserNameHasRegistered(202,"用户名已经存在"),
    /**home*/
    ResolveSuccess(0,"解析成功"),
    /**file */
    UploadFileSuccess(0,"文件上传成功"),
    DeleteFileSuccess(0,"文件删除成功"),
    UploadFileFail(400,"文件上传失败"),
    DeleteFileFail(400,"文件删除成功"),
    /**mail*/
    CheckSuccess(0,"校验成功"),
    CheckFail(301,"验证码无效"),
    CheckCaptchaPassTime(302,"验证码过期了"),
    /**Permission**/
    NotPermission(400,"您没有相关权限"),
    NotToken(500,"not token"),
    /**init*/
    NotInit(501,"未配置参数");


    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
