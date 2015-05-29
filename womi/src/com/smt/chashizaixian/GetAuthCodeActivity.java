package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.smt.config.Constants;
import com.smt.config.URLInterface;
import com.smt.pub.util.StringUtils;
import com.smt.util.network.HttpUtils;
import com.smt.util.network.NetTask;
import com.smt.util.network.NetTask.INetComplete;
import com.smt.util.t.ToastUtil;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class GetAuthCodeActivity extends Activity implements OnClickListener
{
    
    private EditText phone_edit, auth_code_edit;
    private Button send_message, next_step_btn;
    private Boolean pressSend = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_auth_code);
        
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        auth_code_edit = (EditText) findViewById(R.id.auth_code_edit);
        send_message = (Button) findViewById(R.id.send_message);
        next_step_btn = (Button) findViewById(R.id.next_step_btn);
        
        send_message.setOnClickListener(this);
        next_step_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        final String phone = phone_edit.getText().toString() + "";
        final String authCode = auth_code_edit.getText().toString() + "";
        
        switch (v.getId())
        {
        case R.id.send_message:
            
            if (StringUtils.isEmpty(phone) || !StringUtils.checkPhoneNum(phone))
            {
                ToastUtil.show(GetAuthCodeActivity.this, "��������ȷ��11λ�ֻ���");
                return;
            }
            
            pressSend = true;
            send_message.setEnabled(false);
            send_message.postDelayed(new Runnable()
            {
                int mParseTime = Constants.SEND_MESSAGE_DELAY;
                
                @Override
                public void run()
                {
                    if (mParseTime > 0 && pressSend)
                    {
                        mParseTime--;
                        send_message.setText(mParseTime + "�����");
                    }
                    else
                    {
                        mParseTime = Constants.SEND_MESSAGE_DELAY;
                        send_message.setEnabled(true);
                        send_message.setText("������֤��");
                        pressSend = false;
                    }
                    send_message.postDelayed(this, 1000);
                }
            }, 0);
            
            INetComplete callback = new INetComplete()
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
                        if (1 == status)
                        {
                            ToastUtil.show(GetAuthCodeActivity.this, "��֤���ѷ��ͣ������");
                        }      
                        else
                        {
                            ToastUtil.show(GetAuthCodeActivity.this, "����ʧ�ܣ�������");
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            
            final String urlSend = URLInterface.URL_SEND_MESSAGE;
            final List<NameValuePair> paramsSend = new ArrayList<NameValuePair>();
            paramsSend.add(new BasicNameValuePair("mobile", phone));
            paramsSend.add(new BasicNameValuePair("type", "reg_captcha"));
            new NetTask(GetAuthCodeActivity.this, paramsSend, callback, 
                    HttpUtils.HTTP_GET).execute(urlSend);
            
            break;
        case R.id.next_step_btn:
            
            if (StringUtils.isEmpty(phone) || !StringUtils.checkPhoneNum(phone))
            {
                ToastUtil.show(GetAuthCodeActivity.this, "��������ȷ��11λ�ֻ���");
                return;
            }
            if (authCode.length() < 1)
            {
                ToastUtil.show(GetAuthCodeActivity.this, "��֤�벻��Ϊ��");
                return;
            }
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
                        if (1 == status)
                        {
                            Intent intent = new Intent(GetAuthCodeActivity.this, WriteRegistMsgActivity.class);
                            intent.putExtra("captcha", authCode);
                            intent.putExtra("mobile", phone);
                            startActivity(intent);
                        }      
                        else
                        {
                            ToastUtil.show(GetAuthCodeActivity.this, jsonString.getString("info"));
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            
            final String urlCheck = URLInterface.URL_CHECK_MESSAGE;
            final List<NameValuePair> paramsCheck = new ArrayList<NameValuePair>();
            paramsCheck.add(new BasicNameValuePair("mobile", phone));
            paramsCheck.add(new BasicNameValuePair("captcha", authCode));
            new NetTask(GetAuthCodeActivity.this, paramsCheck, callbackCheck, 
                    HttpUtils.HTTP_GET).execute(urlCheck);
            
            break;
        
        default:
            break;
        }
    }
}