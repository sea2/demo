package com.smt.config;

import com.amap.api.maps.model.LatLng;
import com.smt.imageload.util.ImageLoader;

public class Constants {
    /**
     * 调试模式
     */
    public static final boolean DEBUG = true;
    /**
     * 茶市在线
     */
    public static final String USER_INFO = "userInfo";
    /**
     * ImageLoader对象
     */
    public static ImageLoader mImageLoader;
    /**
     * 定位当前位置返回request code值
     */
    public static final int LOCAL_REQUEST_CODE = 1;
    /**
     * 编辑修改返回request code值
     */
    public static final int EDIT_REQUEST_CODE = 2;
    
    /**
     * 编辑返回页面“request code” key
     */
    public static final String RESULT_CODE = "resultcode";
    /**
     * 编辑返回页面标题 key
     */
    public static final String TITLE = "title";
    /**
     * 编辑返回页面编辑框默认显示 key
     */
    public static final String MIMECONTENT = "mimecontent";
    /**
     * 编辑返回页面编辑可输入文字数目 key
     */
    public static final String EDIT_COUNT_NUM = "edit_number";
    /**
     * 编辑返回页面返回文字 key
     */
    public static final String EDIT_RESULT = "edit_result";
    /**
     * 当前位置返回 key
     */
    public static final String LOCAL_RESULT = "local_result";
    /**
     * 清除当前位置返回 key
     */
    public static final String LOCAL_IF_CLEAR = "local_clear";
    
    public static final LatLng CHENGDU = new LatLng(30.679879, 104.064855);// 成都市经纬度
    /**
     * 新消息提醒方式
     */
    public static final String[] MESSAGE_REMIND = new String[] { "提醒 ", "智能 ", "屏蔽 "};
    
    /**
     * http 请求成功返回status值
     */
    public static final int HTTP_RESPONSE_OK = 1;
    /**
     * http 请求成功失败status值
     */
    public static final int HTTP_RESPONSE_FAIL = 0;
    
    /**
     * 连接超时时间 单位：ms
     */
    public static final int CONNECT_TIME_OUT = 25000;
    
    /**
     * 读取超时时间 单位：ms
     */
    public static final int READ_TIME_OUT = 25000;
    
    /**
     * 读取数据长度 单位：byte
     */
    public static final int READ_DATA_LENGTH = 1024;
    /**
     * 读取数据长度 单位：byte
     */
    public static final int SEND_MESSAGE_DELAY = 40;
}
