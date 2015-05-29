package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.smt.model.PersonInfo;
import com.smt.util.adapter.AddFromContactsAdapter;
import com.smt.util.listview.XListView;


public class AddFromContactsActivity extends Activity implements OnClickListener {

	private List<PersonInfo> contactList=null;
	private XListView addfrom_list;
    private AddFromContactsAdapter adapter;
    private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();

	private TextView mime_addfrom_back;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addfromcontacts);
		
	
		
		contactList = new ArrayList<PersonInfo>();
		
		addfrom_list=(XListView)findViewById(R.id.addfrom_list);

		addfrom_list.setPullLoadEnable(false);
		addfrom_list.setPullRefreshEnable(false);

		mime_addfrom_back=(TextView)findViewById(R.id.activity_maddfrom_back);
		mime_addfrom_back.setOnClickListener(this);
		

		
	
		
		setContactList();
		
		
		
		for (int i = 0; i <contactList.size(); i++) {
		
				
				String phone=contactList.get(i).getPhone().get(0);
				String name=contactList.get(i).getName();
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("phone", phone);
				map.put("name", name);
			    mlist.add(map); 	
				
		}
				
	
		adapter=new AddFromContactsAdapter(AddFromContactsActivity.this, mlist);	
		addfrom_list.setAdapter(adapter);
		
		addfrom_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				 Intent intent=new Intent(AddFromContactsActivity.this, CircleMemberInfoActivity.class);
				 startActivity(intent);
			}
		});		
		
	}
	
	
	
	
	/**
	 * 获取通讯录列表
	 */
	private void setContactList() {
		String[] projection = { Phone.DISPLAY_NAME, Phone.NUMBER,
				Phone.PHOTO_ID };
		Cursor cur = getContentResolver().query(Phone.CONTENT_URI, projection,
				null, null, Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
		cur.moveToFirst();
		while (cur.getCount() > cur.getPosition()) {
			PersonInfo person = new PersonInfo(AddFromContactsActivity.this);
			List<String> phone = new ArrayList<String>();
			String number = cur.getString(cur.getColumnIndex(Phone.NUMBER));
			String name = cur.getString(cur.getColumnIndex(Phone.DISPLAY_NAME));
			String photo_id = cur.getString(cur.getColumnIndex(Phone.PHOTO_ID));
			
			person.setName(name);
			person.setPhotoId(photo_id);
			phone.add(number);
			person.setPhone(phone);
			add2List(contactList, person);
			cur.moveToNext();
		}
		cur.close();
	}

	public void add2List(List<PersonInfo> list, PersonInfo person) {
		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i).getName().equals(person.getName())) {
				for (int k = 0; k < person.getPhone().size(); ++k)
					list.get(i).addPhone(person.getPhone().get(k));
				return;
			}
		}
		list.add(person);
			
	}
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		   case R.id.activity_maddfrom_back:
	        	
			   this.finish();
	        
		        break;  
		
		  
	      
		default:
			break;
		}
	}

	
	
	
	

	
}
