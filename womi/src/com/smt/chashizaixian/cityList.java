package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.smt.dbhelper.util.MyDatabase;

public class cityList extends Activity{

	private TextView back;
	String citys[][];
	int cityCount=0;
	ListView lv;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_citylist);

        
        Intent i=getIntent();
        String provinceid=i.getStringExtra("provinceid");
        lv=(ListView)findViewById(R.id.listview_city);
        
        back=(TextView)findViewById(R.id.city_btn);
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cityList.this.finish();
			}
		});
        MyDatabase myDB = new MyDatabase(this);
        Cursor cCity = myDB.getCities(provinceid);
        cityCount=cCity.getCount();
        if(cityCount==0)
        {
        	returnResult(i.getStringExtra("provincename"));
        	
        }
        citys=new String[cityCount][2];
        
        for(int j=0;j<cityCount;j++)
        {
        	citys[j][0]=cCity.getString(0);
        	citys[j][1]=cCity.getString(1);	
        	cCity.moveToNext();
        }
        
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				returnResult(citys[position][1]);
			}
		});
        
    }
	
	void returnResult(String sdata) {
		Intent result = new Intent();
		result.putExtra("cityname", sdata);
		setResult(Activity.RESULT_OK, result);
		finish();
	}

	public List<String> getData() {

		List<String> ls=new ArrayList<String>();
		ls=asList(citys);
		return ls;
	}
	
	//字符创数组转化list
	public List<String>  asList(String s[][]){
		List<String> l=new ArrayList<String>();
		for(int i=0;i<cityCount;i++)
		{
			if(s[i][1]!=null)
				l.add(s[i][1]);
		}
		return l;	
	}
}
