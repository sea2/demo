package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.smt.util.adapter.MessageAdapter;
import com.smt.util.t.MyListView;

public class MessageActivity extends Activity implements OnClickListener {

	private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
	private MessageAdapter adapter;
	private MyListView mListView;
	private TextView message_back;
	private TextView message_friend_name;
	private TextView message_setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);

		InitView();

		initData();

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MessageActivity.this,
						CircleDetailActivity.class);
				startActivity(intent);

			}
		});

	}

	private void InitView() {

		message_back = (TextView) findViewById(R.id.activity_message_back);
		message_back.setOnClickListener(this);
		mListView = (MyListView) findViewById(R.id.message_list);

		message_setting = (TextView) findViewById(R.id.activity_message_setting);
		message_setting.setOnClickListener(this);

		message_friend_name = (TextView) findViewById(R.id.activity_message_friend_name);
		message_friend_name.setText("茶市团队");

	}

	public void initData() {
		for (int i = 0; i < 15; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("friend_or_mi", i % 2); // 0 朋友 1自己
			map.put("message",
					"usernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusername"
							+ i);
			map.put("img", "");
			mlist.add(map);

		}

		adapter = new MessageAdapter(MessageActivity.this, mlist);
		mListView.setDivider(null);
		mListView.setAdapter(adapter);
	}

	// @Override
	// public boolean onTouchEvent(MotionEvent event){
	// top_lay.setVisibility(View.GONE);
	// return true;
	// }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.activity_message_back:
			//
			// Intent backIntent = new Intent(MessageActivity.this,
			// InfoSearchActivity.class);
			// startActivity(backIntent);
			MessageActivity.this.finish();
			break;
		case R.id.activity_message_setting:
			//
			// Intent settingIntent = new Intent(MessageActivity.this,
			// InfoSearchActivity.class);
			// startActivity(settingIntent);
			System.out.println(2);
			break;

		}

	}
}
