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

public class ResetStep2Activity extends Activity implements OnClickListener
{
    
    private EditText password_new, password_tv;
    private SharePreferenceUtil sp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        
        password_new = (EditText) findViewById(R.id.password_new);
        password_tv = (EditText) findViewById(R.id.password_tv);
        Button registBtn = (Button) findViewById(R.id.regist_btn);
        registBtn.setOnClickListener(this);
        sp = new SharePreferenceUtil(ResetStep2Activity.this, Constants.USER_INFO);
    }

    @Override
    public void onClick(View v)
    {
        final String newPassword = password_new.getText().toString() + "";
        final String surePassword = password_tv.getText().toString() + "";
        
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
                            ToastUtil.show(ResetStep2Activity.this, "恭喜您已经成功注册");
                            sp.setName(newPassword);
                            sp.setPasswd(surePassword);
                            startActivity(new Intent(ResetStep2Activity.this, MainActivity.class));
                        }      
                        else
                        {
                            ToastUtil.show(ResetStep2Activity.this, jsonString.getString("info"));
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
            paramsRegist.add(new BasicNameValuePair("pwd", newPassword));
            paramsRegist.add(new BasicNameValuePair("cpwd", surePassword));
            paramsRegist.add(new BasicNameValuePair("mobile", phone));
            paramsRegist.add(new BasicNameValuePair("captcha", authCode));
            new NetTask(ResetStep2Activity.this, paramsRegist, callbackCheck, 
                    HttpUtils.HTTP_POST).execute(urlRegist);
            
            break;
        
        default:
            break;
        }
    }
}
