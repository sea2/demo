package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.List;

import com.smt.dbhelper.util.MyDatabase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class provinceList extends Activity {

	ListView lv;
	
	private TextView back;
	
	Context mcontext;
    private	String provincename;
	String provinces[][];//存放省数据 0_id 1_name
	int provinceCount=0;//读取数据库中省的数量
	
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_provincelist);

	        mcontext=this;
	        lv=(ListView)findViewById(R.id.listview_province);
	       
	        back=(TextView)findViewById(R.id.province_btn);
	        back.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					provinceList.this.finish();
				}
			});
	        
	        //开始
	        MyDatabase myDB = new MyDatabase(this);
	        Cursor cProvinces = myDB.getProvinces();
	        provinceCount=cProvinces.getCount();
	        provinces=new String[provinceCount][2];
	        
	        for(int j=0;j<provinceCount;j++)
	        {
	        	provinces[j][0]=cProvinces.getString(0);
	        	provinces[j][1]=cProvinces.getString(1);	
	        	cProvinces.moveToNext();
	        }
	        
	        
 
	        
	        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
	        lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					//Toast.makeText(mcontext,p[position], 1).show();
					Intent i=new Intent(mcontext,cityList.class);
					i.putExtra("provinceid", provinces[position][0]);
					i.putExtra("provincename", provinces[position][1]);
					provincename=provinces[position][1];
					startActivityForResult(i, 1);
					int version = Integer.valueOf(android.os.Build.VERSION.SDK);     
            		if(version  >= 5) {     
            		     //overridePendingTransition(R.anim.zoomin, R.anim.zoomout);  //此为自定义的动画效果，下面两个为系统的动画效果  
            			//overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);    
            		    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.fade_out);  
            		}  
				}
			});
	    }

	
	protected void onActivityResult( int resultCode,int re, Intent data) {
		String cityname;
		
		if(data==null)
		{
			return;
		}
		cityname=data.getStringExtra("cityname");
		returnResult(cityname);	
	}
    
	void returnResult(String sdata) {
		Intent result = new Intent();
		
		if (sdata.equals("北京市")||sdata.equals("天津市")||sdata.equals("上海市")||sdata.equals("重庆市")||sdata.equals("台湾省")||sdata.equals("香港特别行政区")||sdata.equals("澳门特别行政区")) {
			result.putExtra("cityname", sdata);
		}
		else {
			result.putExtra("provincename", provincename);
			result.putExtra("cityname", sdata);
		}
		
		
		setResult(RESULT_OK, result);
		
	
		
		finish();
	}

	public List<String> getData() {

		List<String> ls=new ArrayList<String>();
		ls=asList(provinces);
		return ls;
	}
	
	//字符创数组转化list
	public List<String>  asList(String s[][]){
		List<String> l=new ArrayList<String>();
		for(int i=0;i<provinceCount;i++)
		{
			if(s[i][1]!=null)
				l.add(s[i][1]);
		}
		return l;	
	}
}
