package com.smt.chashizaixian;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smt.config.ViewHolder;
import com.smt.util.adapter.ChooseChattingAdapter;
import com.smt.util.t.ClearEditText;
import com.smt.util.t.ToastUtil;

public class StartChattingActivity extends Activity implements OnClickListener{

	
	private ClearEditText mClearEditText;
	private RelativeLayout group_chatting_rl;
	private ListView chooseListView;
	private TextView choose_back;
	private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
	private ChooseChattingAdapter adapter;
		
	private TextView choosecheck_tv;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choosecontacts);
		
		choose_back=(TextView)findViewById(R.id.activity_choose_back);
		choose_back.setOnClickListener(this);
		
		choosecheck_tv=(TextView)findViewById(R.id.choosecheck_tv);
		
		
		chooseListView=(ListView)findViewById(R.id.choose_list);
		chooseListView.addHeaderView(getheadView());
		
		
		choosecheck_tv.setText("添加"+"("+ChooseChattingAdapter.state.size()+"/99"+")");
		
		chooseListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				
				
				if (arg2>0) {	
				
				ViewHolder holder = (ViewHolder) arg1.getTag();
				holder.choose_cb.toggle();// 在每次获取点击的item时改变checkbox的状态
				ChooseChattingAdapter.state.put(arg2-1, holder.choose_cb.isChecked()); // 同时修改map的值保存状态
				if (holder.choose_cb.isChecked() == true) {
					ChooseChattingAdapter.state.put(arg2-1,holder.choose_cb.isChecked());
				} else {
					ChooseChattingAdapter.state.remove(arg2-1);
				}
				
				
				choosecheck_tv.setText("添加"+"("+ChooseChattingAdapter.state.size() +"/99"+")");
				
			}
			
			}
		});
		
		
		
		
		initData();
		
	}

	public void initData()
	{
		for (int i = 0; i < 3; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("key_name", "username" + i);
		    mlist.add(map);
		    adapter=new ChooseChattingAdapter(StartChattingActivity.this, mlist);	
		    chooseListView.setAdapter(adapter);
		}
	}
	
	private View getheadView(){
		
		
		    View view =LayoutInflater.from(StartChattingActivity.this).inflate(R.layout.choose_head_layout, null);
		  
		  
		     mClearEditText = (ClearEditText) view.findViewById(R.id.choose_edit);
			
			
		     group_chatting_rl=(RelativeLayout)view.findViewById(R.id.group_chatting_lay);
		     group_chatting_rl.setOnClickListener(this);
			

			return view;
	   }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.activity_choose_back:
			
			this.finish();
			
			break;

		default:
			break;
		}
	}
}
