package com.smt.config;

public class URLInterface {
    
    
    /**
     * �ӿ������������ַ
     */
    public static final String REQUEST_HEAD = "http://192.168.1.228:8080/cha4online/";
    /**
     * �û���¼
     */
    public static final String URL_LOGIN = REQUEST_HEAD + "index.php?app=api&act=login";
    /**
     * ���Ͷ�����֤��ӿ�
     */
    public static final String URL_SEND_MESSAGE = REQUEST_HEAD + "index.php?app=api&act=sendCaptcha";
    /**
     * ��֤������֤��ӿ�
     */
    public static final String URL_CHECK_MESSAGE = REQUEST_HEAD + "index.php?app=api&act=checkCaptcha";
    /**
     * �û�ע��
     */
    public static final String URL_REGIST = REQUEST_HEAD + "index.php?app=api&act=register";
    /**
     * ��Ա�����ӿ�
     */
    public static final String URL_USER_SEARCH = REQUEST_HEAD + "index.php?app=api&act=searchFriend";
    /**
     * ��ȡ�����б�ӿ�
     */
    public static final String URL_GET_CONTACTS = REQUEST_HEAD + "index.php?app=api&act=getAttentionList";
    /**
     * ��Ӻ����б�ӿ�
     */
    public static final String URL_ADD_FRIENDS = REQUEST_HEAD + "index.php?app=api&act=addAttention";
    /**
     * ɾ�������б�ӿ�
     */
    public static final String URL_DELETE_FRIENDS = REQUEST_HEAD + "index.php?app=api&act=cancelAttention";
}
