package com.smt.util.network;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.smt.config.Constants;
import com.smt.util.t.NetWorkCheck;
import com.smt.util.t.ToastUtil;

public class NetTask
{
    private Class mClazz;
    
    private Type mType;
    
    private String mUrl;
    
    private int mMethod = 1; // 传输方式 0.get 1.post
    
    private List<NameValuePair> mParams;
    
    private INetComplete mNetComplete;
    
    private Context context;
    
    /**
     * 
     * @param params
     * @param clazz
     * @param iComplete
     * @param method
     */
    public NetTask(Context context, List<NameValuePair> params, 
            INetComplete iComplete, int method)
    {
        this(context, params, null, null, null, iComplete, method, false);
    }
    
    private NetTask(Context context, List<NameValuePair> params,
            Class<?> clazz, Type type, INetProgress iProgress,
            INetComplete iComplete, int method, boolean isProgress)
    {
        this.context = context;
        this.mParams = params;
        this.mClazz = clazz;
        this.mType = type;
        this.mNetComplete = iComplete;
        this.mMethod = method;
    }
    
    @SuppressWarnings("unchecked")
    protected String doInBackground(String... params)
    {
        if (mParams != null)
        {
            mUrl = revertUrl(params[0], mParams, mMethod);
            mParams.add(new BasicNameValuePair("k", "opendoor"));
        }
        else
        {
            mParams = new ArrayList<NameValuePair>();
            mParams.add(new BasicNameValuePair("k", "opendoor"));
        }
        
        String jsonData = HttpUtils.getHttpEntity(mUrl, mParams, mMethod);
        if (Constants.DEBUG && mParams != null)
        {
            System.out.println("params:" + Arrays.toString(mParams.toArray()));
        }
        if (Constants.DEBUG)
        {
            Log.e("get_from_Services", jsonData);
        }
        return jsonData;
    }
    
    public void execute(final String url)
    {
        if (!NetWorkCheck.checkNetWork(context))
        {
            ToastUtil.show(context, "网络处于断开状态，请连接");
            mNetComplete.complete(null);
            return;
        }
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Object obj = doInBackground(url);
                if (mNetComplete != null)
                {
                    Message msg = new Message();
                    msg.obj = obj;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
    
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler()
    {
        
        @Override
        public void handleMessage(Message msg)
        {
            try
            {
                mNetComplete.complete((String) msg.obj);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    };
    
    /**
     * 拼接get方式url参数
     * 
     * @param url
     * @param params
     * @param method
     * @return
     */
    private String revertUrl(String url, List<NameValuePair> params, int method)
    {
        if ("".equals(url) || method != HttpUtils.HTTP_GET)
        {
            return url;
        }
        
        StringBuilder sb = new StringBuilder(url);
        
        sb.append(url.contains("?") ? "&" : "?");
        
        boolean needRemove = false;
        for (NameValuePair pair : params)
        {
            sb.append(pair.getName()).append("=").append(pair.getValue())
                    .append("&");
            needRemove = true;
        }
        
        url = needRemove ? sb.substring(0, sb.length() - 1) : sb.toString();
        return url;
    }
    
    /*******************************************************************/
    public interface INetProgress
    {
        public void progress();
    }
    
    public interface INetComplete
    {
        public void complete(String stringMsg);
    }
    
}
