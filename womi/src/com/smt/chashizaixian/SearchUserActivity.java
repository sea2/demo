package com.smt.chashizaixian;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smt.config.MyApplication;
import com.smt.config.URLInterface;
import com.smt.model.SortModel;
import com.smt.util.adapter.InfoAdapter;
import com.smt.util.adapter.SearchUserAdapter;
import com.smt.util.network.HttpUtils;
import com.smt.util.network.NetTask;
import com.smt.util.network.NetTask.INetComplete;
import com.smt.util.t.ToastUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchUserActivity extends Activity implements OnClickListener{

	
	private TextView mime_back, search_btn;
	private EditText sear_et;
	private SearchUserAdapter adapter;
	private ListView mListView;
	private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_search);
		initView();
	}

	private void initView(){
		sear_et=(EditText)findViewById(R.id.info_search_edit);
		sear_et.requestFocus();
		mListView=(ListView)findViewById(R.id.info_search_list);
		mime_back=(TextView)findViewById(R.id.mime_back);
		search_btn=(TextView)findViewById(R.id.search_btn);
		
		mime_back.setOnClickListener(this);
		search_btn.setOnClickListener(this);
		mListView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {
                Intent intent =new Intent(SearchUserActivity.this,CircleMemberInfoActivity .class);
                intent.putExtra("fid", (String) mlist.get(position).get("fid"));
                startActivity(intent);
            }
        });
	}
	
	
	private void initData()
	{
	    String keyWord = "";
	    keyWord = sear_et.getText().toString();
	    INetComplete callback = new INetComplete()
        {
            @Override
            public void complete(String stringMsg)
            {
                mlist.clear();
                if (stringMsg == null)
                {
                    return;
                }
                System.out.println(stringMsg);
                try
                {
                    JSONObject jsonString = new JSONObject(stringMsg);
                    int status = jsonString.getInt("status");
                    if (1 == status)
                    {
                        JSONObject dataJson = jsonString.getJSONObject("data");
                        JSONArray listDataJson = dataJson.getJSONArray("list");
                        for (int position = 0; position< listDataJson.length(); position++)
                        {
                            JSONObject userData = (JSONObject) listDataJson.get(position);
                            String real_name = userData.getString("real_name");
                            String fid = userData.getString("user_id");
                            
                            HashMap<String, Object> map = new HashMap<String, Object>();
                            map.put("key_name", real_name);
                            map.put("fid", fid);
                            mlist.add(map);
                        }
                        adapter=new SearchUserAdapter(SearchUserActivity.this, mlist);    
                        mListView.setAdapter(adapter);
                    }      
                    else
                    {
                        ToastUtil.show(SearchUserActivity.this, jsonString.getString("info"));
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };
        
        final String url = URLInterface.URL_USER_SEARCH;
        final List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", MyApplication.token));
        params.add(new BasicNameValuePair("fn", keyWord));
        new NetTask(SearchUserActivity.this, params, callback, 
                HttpUtils.HTTP_GET).execute(url);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mime_back:
		    finish();
		    break;
		    
		case R.id.search_btn:
		    initData();
			break;

		default:
			break;
		}
	}
}
