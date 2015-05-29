package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smt.config.MyApplication;
import com.smt.config.URLInterface;
import com.smt.util.network.HttpUtils;
import com.smt.util.network.NetTask;
import com.smt.util.network.NetTask.INetComplete;
import com.smt.util.t.MyListView;
import com.smt.util.t.ToastUtil;

public class CircleMemberInfoActivity extends Activity implements OnClickListener{

    private View mime_back;
    
    private TextView more_tv;
    
    private Button button_add;
    private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_member_info);
        
        MyListView circle_list = (MyListView) findViewById(R.id.circle_list);
        mime_back = findViewById(R.id.mime_back);
        mime_back.setOnClickListener(this);
        
        more_tv=(TextView)findViewById(R.id.circle_detail_more);
        more_tv.setOnClickListener(this);
        
        button_add=(Button)findViewById(R.id.button_add);
        button_add.setOnClickListener(this);
        
        circle_list.setAdapter(new BaseAdapter()
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = getLayoutInflater().inflate(R.layout.item_circle_group_small, null);
                return view;
            }
            
            @Override
            public long getItemId(int position)
            {
                // TODO Auto-generated method stub
                return 0;
            }
            
            @Override
            public Object getItem(int position)
            {
                // TODO Auto-generated method stub
                return null;
            }
            
            @Override
            public int getCount()
            {
                // TODO Auto-generated method stub
                return 10;
            }
        });
    }
    
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
        case R.id.mime_back:
            finish();
            break;
            
        case R.id.circle_detail_more:
            
        	  final CharSequence[] items = { "拉黑/举报","添加备注名","设置圈子权限","删除好友","发送给茶友帮好友"};  
		        AlertDialog dlg = new AlertDialog.Builder(CircleMemberInfoActivity.this).setTitle("").setItems(items,   
		            new DialogInterface.OnClickListener() {   
		                public void onClick(DialogInterface dialog,int item) {   
		                    //这里item是根据选择的方式,  
		                    //在items数组里面定义了两种方式
		                    switch (item)
                            {
                            case 0:
                                
                                break;
                            case 1:
                                
                                break;
                            case 2:
                                
                                break;
                            case 3:
                                Builder deleteDialog = new AlertDialog.Builder(CircleMemberInfoActivity.this);
                                deleteDialog.setTitle("取消关注").setMessage("取消关注后，同时与该联系人的聊天记录也将被删除").setPositiveButton("确定", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
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
                                                        ToastUtil.show(CircleMemberInfoActivity.this, jsonString.getString("info"));
                                                    }      
                                                    else
                                                    {
                                                        ToastUtil.show(CircleMemberInfoActivity.this, jsonString.getString("info"));
                                                    }
                                                }
                                                catch (JSONException e)
                                                {
                                                    e.printStackTrace();
                                                }
                                            }
                                        };
                                        
                                        final String urlSend = URLInterface.URL_DELETE_FRIENDS;
                                        final List<NameValuePair> paramsSend = new ArrayList<NameValuePair>();
                                        paramsSend.add(new BasicNameValuePair("token", MyApplication.token));
                                        paramsSend.add(new BasicNameValuePair("fid", getIntent().getStringExtra("fid")));
                                        new NetTask(CircleMemberInfoActivity.this, paramsSend, callback, 
                                                HttpUtils.HTTP_GET).execute(urlSend);
                                    }
                                });
                                
                                deleteDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        
                                    }
                                });
                                deleteDialog.show();
                                break;
                            case 4:
                                
                                break;
                            
                            default:
                                break;
                            }
		                }   
		            }).create();   
		        dlg.show();
        	
        	
            break;
            
        case R.id.button_add:
           
        	EditTextDialog();
        	
            break;  
            
        
        default:
            break;
        }
    }
    
    
    public void EditTextDialog() {
    	Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.dialogedit, null);
        dialog.setView(layout);
        et_content = (EditText)layout.findViewById(R.id.content_ett);
        
        dialog.setTitle("打个招呼").setMessage("打个招呼让好友更容易认出你，加你为好友").setPositiveButton("发送", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                String message;
//                if ("".equals(et_content.getText().toString()))
//                {
//                    message = "我想加你好友啊!";
//                }
//                else
//                {
//                    message = et_content.getText().toString();
//                }
                
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
                                ToastUtil.show(CircleMemberInfoActivity.this, jsonString.getString("info"));
                            }      
                            else
                            {
                                ToastUtil.show(CircleMemberInfoActivity.this, jsonString.getString("info"));
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                
                final String urlSend = URLInterface.URL_ADD_FRIENDS;
                final List<NameValuePair> paramsSend = new ArrayList<NameValuePair>();
                paramsSend.add(new BasicNameValuePair("token", MyApplication.token));
                paramsSend.add(new BasicNameValuePair("fid", getIntent().getStringExtra("fid")));
                new NetTask(CircleMemberInfoActivity.this, paramsSend, callback, 
                        HttpUtils.HTTP_GET).execute(urlSend);
            }
        });
        
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	
            }
        });
        dialog.show();
    }
    
    
}
