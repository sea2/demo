package com.smt.config;

import com.amap.api.maps.model.LatLng;
import com.smt.imageload.util.ImageLoader;

public class Constants {
    /**
     * ����ģʽ
     */
    public static final boolean DEBUG = true;
    /**
     * ��������
     */
    public static final String USER_INFO = "userInfo";
    /**
     * ImageLoader����
     */
    public static ImageLoader mImageLoader;
    /**
     * ��λ��ǰλ�÷���request codeֵ
     */
    public static final int LOCAL_REQUEST_CODE = 1;
    /**
     * �༭�޸ķ���request codeֵ
     */
    public static final int EDIT_REQUEST_CODE = 2;
    
    /**
     * �༭����ҳ�桰request code�� key
     */
    public static final String RESULT_CODE = "resultcode";
    /**
     * �༭����ҳ����� key
     */
    public static final String TITLE = "title";
    /**
     * �༭����ҳ��༭��Ĭ����ʾ key
     */
    public static final String MIMECONTENT = "mimecontent";
    /**
     * �༭����ҳ��༭������������Ŀ key
     */
    public static final String EDIT_COUNT_NUM = "edit_number";
    /**
     * �༭����ҳ�淵������ key
     */
    public static final String EDIT_RESULT = "edit_result";
    /**
     * ��ǰλ�÷��� key
     */
    public static final String LOCAL_RESULT = "local_result";
    /**
     * �����ǰλ�÷��� key
     */
    public static final String LOCAL_IF_CLEAR = "local_clear";
    
    public static final LatLng CHENGDU = new LatLng(30.679879, 104.064855);// �ɶ��о�γ��
    /**
     * ����Ϣ���ѷ�ʽ
     */
    public static final String[] MESSAGE_REMIND = new String[] { "���� ", "���� ", "���� "};
    
    /**
     * http ����ɹ�����statusֵ
     */
    public static final int HTTP_RESPONSE_OK = 1;
    /**
     * http ����ɹ�ʧ��statusֵ
     */
    public static final int HTTP_RESPONSE_FAIL = 0;
    
    /**
     * ���ӳ�ʱʱ�� ��λ��ms
     */
    public static final int CONNECT_TIME_OUT = 25000;
    
    /**
     * ��ȡ��ʱʱ�� ��λ��ms
     */
    public static final int READ_TIME_OUT = 25000;
    
    /**
     * ��ȡ���ݳ��� ��λ��byte
     */
    public static final int READ_DATA_LENGTH = 1024;
    /**
     * ��ȡ���ݳ��� ��λ��byte
     */
    public static final int SEND_MESSAGE_DELAY = 40;
}
