package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.smt.config.Constants;
import com.smt.config.URLInterface;
import com.smt.util.network.HttpUtils;
import com.smt.util.network.NetTask;
import com.smt.util.network.NetTask.INetComplete;
import com.smt.util.t.SharePreferenceUtil;
import com.smt.util.t.ToastUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class WriteRegistMsgActivity extends Activity implements OnClickListener
{
    
    private EditText user_name, password_tv;
    private SharePreferenceUtil sp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_regist_msg);
        
        user_name = (EditText) findViewById(R.id.user_name);
        password_tv = (EditText) findViewById(R.id.password_tv);
        Button registBtn = (Button) findViewById(R.id.regist_btn);
        registBtn.setOnClickListener(this);
        sp = new SharePreferenceUtil(WriteRegistMsgActivity.this, Constants.USER_INFO);
    }

    @Override
    public void onClick(View v)
    {
        final String userName = user_name.getText().toString() + "";
        final String password = password_tv.getText().toString() + "";
        
        switch (v.getId())
        {
        case R.id.regist_btn:
            
            INetComplete callbackCheck = new INetComplete()
            {
                @Override
                public void complete(String stringMsg)
                {
                    if (stringMsg == null)
                    {
                        return;
                    }
                    try
                    {
                        JSONObject jsonString = new JSONObject(stringMsg);
                        int status = jsonString.getInt("status");
                        System.out.println(jsonString.toString());
                        if (1 == status)
                        {
                            ToastUtil.show(WriteRegistMsgActivity.this, "恭喜您已经成功注册");
                            sp.setName(userName);
                            sp.setPasswd(password);
                            startActivity(new Intent(WriteRegistMsgActivity.this, MainActivity.class));
                        }      
                        else
                        {
                            ToastUtil.show(WriteRegistMsgActivity.this, jsonString.getString("info"));
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            
            String phone = getIntent().getStringExtra("mobile");
            String authCode = getIntent().getStringExtra("captcha");
            final String urlRegist = URLInterface.URL_REGIST;
            final List<NameValuePair> paramsRegist = new ArrayList<NameValuePair>();
            paramsRegist.add(new BasicNameValuePair("agree", "1"));
            paramsRegist.add(new BasicNameValuePair("captcha", authCode));
            paramsRegist.add(new BasicNameValuePair("user_name", userName));
            paramsRegist.add(new BasicNameValuePair("mobile", phone));
            paramsRegist.add(new BasicNameValuePair("pwd", password));
            new NetTask(WriteRegistMsgActivity.this, paramsRegist, callbackCheck, 
                    HttpUtils.HTTP_POST).execute(urlRegist);
            
            break;
        
        default:
            break;
        }
    }
}
