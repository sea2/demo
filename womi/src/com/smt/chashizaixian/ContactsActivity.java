package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smt.config.MyApplication;
import com.smt.config.URLInterface;
import com.smt.model.SortModel;
import com.smt.util.adapter.SearchUserAdapter;
import com.smt.util.adapter.SortAdapter;
import com.smt.util.listview.XListView;
import com.smt.util.listview.XListView.IXListViewListener;
import com.smt.util.network.HttpUtils;
import com.smt.util.network.NetTask;
import com.smt.util.network.NetTask.INetComplete;
import com.smt.util.t.CharacterParser;
import com.smt.util.t.ClearEditText;
import com.smt.util.t.MD5;
import com.smt.util.t.PinyinComparator;
import com.smt.util.t.SideBar;
import com.smt.util.t.SideBar.OnTouchingLetterChangedListener;
import com.smt.util.t.ToastUtil;


public class ContactsActivity extends Activity implements OnClickListener,IXListViewListener {
	private XListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;
	private ArrayList<SortModel> mCheckList = new ArrayList<SortModel>();
	
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	
	private RelativeLayout new_lay;
	private RelativeLayout mygroup_rl;
	private RelativeLayout add_rl;
	
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;
	
	private TextView contacts_add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts);
		initViews();
	}

	private void initViews() {
		//实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();
		
		pinyinComparator = new PinyinComparator();
		
		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		
		//设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			
			@Override
			public void onTouchingLetterChanged(String s) {
				//该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if(position != -1){
					sortListView.setSelection(position+2);
				}
			    if (position==-1) {
					sortListView.setSelection(0);
				}
				
				
			}
		});
		
		sortListView = (XListView) findViewById(R.id.friend_list);
		sortListView.setPullLoadEnable(false);
		sortListView.setXListViewListener(this);
		sortListView.setRefreshTime(new Date().toLocaleString());
		
		sortListView.addHeaderView(getheadView());
		sortListView.addFooterView(getFootView());
		
		contacts_add=(TextView)findViewById(R.id.contacts_add);
		contacts_add.setOnClickListener(this);

		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//这里要利用adapter.getItem(position)来获取当前position所对应的对象
				Intent intent1=new Intent(ContactsActivity.this,CircleMemberInfoActivity .class);
				intent1.putExtra("fid", ((SortModel)adapter.getItem(position-2)).getFid());
				startActivity(intent1);
				Toast.makeText(getApplication(), ((SortModel)adapter.getItem(position-2)).getName(), Toast.LENGTH_SHORT).show();
			}
		});
		
		SourceDateList = new ArrayList<SortModel>();
		adapter = new SortAdapter(ContactsActivity.this, SourceDateList);
        sortListView.setAdapter(adapter);
		
		initData();
	}

    private void initData()
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
                        JSONArray listDataJson = jsonString.getJSONArray("data");
                        
                        mCheckList.clear();
                        SourceDateList.clear();
                        for (int position = 0; position< listDataJson.length(); position++)
                        {
                            JSONObject userData = (JSONObject) listDataJson.get(position);
                            SortModel sortModel = new SortModel();
                            sortModel.setName(userData.getString("user_name"));
                            sortModel.setFid(userData.getString("fid"));
                            mCheckList.add(sortModel);
                        }
                        SourceDateList = filledData(mCheckList);
                        // 根据a-z进行排序源数据
                        Collections.sort(SourceDateList, pinyinComparator);
                        adapter.updateListView(SourceDateList);
                        sortListView.stopRefresh();
                        
                        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
                        //根据输入框输入值的改变来过滤搜索
                        mClearEditText.addTextChangedListener(new TextWatcher() {
                            
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                                filterData(s.toString());
                            }
                            
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count,
                                    int after) {
                                
                            }
                            
                            @Override
                            public void afterTextChanged(Editable s) {
                            }
                        });
                    }      
                    else
                    {
                        ToastUtil.show(ContactsActivity.this, jsonString.getString("info"));
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };
        
        final String url = URLInterface.URL_GET_CONTACTS;
        final List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", MyApplication.token));
        new NetTask(ContactsActivity.this, params, callback, 
                HttpUtils.HTTP_GET).execute(url);
    }


	/**
	 * 为ListView填充数据
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(ArrayList<SortModel> list){
		List<SortModel> mSortList = new ArrayList<SortModel>();
		
		for(int i=0; i<list.size(); i++){
			SortModel sortModel = new SortModel();
			sortModel.setName(list.get(i).getName());
			sortModel.setFid(list.get(i).getFid());
			//汉字转换成拼音
			String pinyin = characterParser.getSelling(list.get(i).getName());
			String sortString = pinyin.substring(0, 1).toUpperCase();
			
			// 正则表达式，判断首字母是否是英文字母
			if(sortString.matches("[A-Z]")){
				sortModel.setSortLetters(sortString.toUpperCase());
			}else{
				sortModel.setSortLetters("#");
			}
			mSortList.add(sortModel);
		}
		return mSortList;
	}
	
	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr){
		List<SortModel> filterDateList = new ArrayList<SortModel>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = SourceDateList;
		}else{
			filterDateList.clear();
			for(SortModel sortModel : SourceDateList){
				String name = sortModel.getName();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(sortModel);
				}
			}
		}
		
		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

	
	    private View getheadView(){
			
			
		  View view =LayoutInflater.from(ContactsActivity.this).inflate(R.layout.contact_head_layout, null);
		  
		  
		     mClearEditText = (ClearEditText) view.findViewById(R.id.filter_edit);
			
			
			new_lay=(RelativeLayout)view.findViewById(R.id.new_lay);
			new_lay.setOnClickListener(this);
			
			
		   	
			mygroup_rl=(RelativeLayout)view.findViewById(R.id.group_lay);
			mygroup_rl.setOnClickListener(this);

			
		   	
			return view;
	   }
	    
	    
	
	    private View getFootView(){
	    	
	    	 View fview =LayoutInflater.from(ContactsActivity.this).inflate(R.layout.contact_foot_layout, null);
			 
	    	 add_rl=(RelativeLayout)fview.findViewById(R.id.add_lay);
	    	 add_rl.setOnClickListener(this);
	    	
			return fview;
	    	
	    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add_lay:
			
	
			Intent intent=new Intent(ContactsActivity.this,AddFriendsActivity.class);
			startActivity(intent);
			
			break;
			
		case R.id.contacts_add:
			
			Intent intent3=new Intent(ContactsActivity.this,AddFriendsActivity.class);
			startActivity(intent3);
				
				
				break;
			
		case R.id.group_lay:
			
			Intent intent4=new Intent(ContactsActivity.this,MyCircleActivity.class);
			startActivity(intent4);
				
				
				break;	
			
        case R.id.new_lay:
			
			Intent intent1=new Intent(ContactsActivity.this,MimeNewFriendsActivity.class);
			startActivity(intent1);
				
				
				break;	
		default:
			break;
		}
	}

	@Override
	public void onRefresh() {
	    initData();
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}
	
}
