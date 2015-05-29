package com.smt.config;

public class URLInterface {
    
    
    /**
     * 接口请求服务器地址
     */
    public static final String REQUEST_HEAD = "http://192.168.1.228:8080/cha4online/";
    /**
     * 用户登录
     */
    public static final String URL_LOGIN = REQUEST_HEAD + "index.php?app=api&act=login";
    /**
     * 发送短信验证码接口
     */
    public static final String URL_SEND_MESSAGE = REQUEST_HEAD + "index.php?app=api&act=sendCaptcha";
    /**
     * 验证短信验证码接口
     */
    public static final String URL_CHECK_MESSAGE = REQUEST_HEAD + "index.php?app=api&act=checkCaptcha";
    /**
     * 用户注册
     */
    public static final String URL_REGIST = REQUEST_HEAD + "index.php?app=api&act=register";
    /**
     * 会员搜索接口
     */
    public static final String URL_USER_SEARCH = REQUEST_HEAD + "index.php?app=api&act=searchFriend";
    /**
     * 获取好友列表接口
     */
    public static final String URL_GET_CONTACTS = REQUEST_HEAD + "index.php?app=api&act=getAttentionList";
    /**
     * 添加好友列表接口
     */
    public static final String URL_ADD_FRIENDS = REQUEST_HEAD + "index.php?app=api&act=addAttention";
    /**
     * 删除好友列表接口
     */
    public static final String URL_DELETE_FRIENDS = REQUEST_HEAD + "index.php?app=api&act=cancelAttention";
}
