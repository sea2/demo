package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.smt.util.adapter.CircleGroupAdapter;
import com.smt.util.adapter.SearchAdapter;
import com.smt.util.t.ClearEditText;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MyCircleGroupActivity extends Activity implements OnClickListener
{
    private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
    private View mime_back;
    private ClearEditText info_edit;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_and_mycircle_group);
        ListView circle_group_list = (ListView) findViewById(R.id.listview);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("我的群联盟");
        findViewById(R.id.mime_detail).setVisibility(View.INVISIBLE);
        mime_back = findViewById(R.id.mime_back);
        mime_back.setOnClickListener(this);
        
        View view = getLayoutInflater().inflate(R.layout.head_search, null);
        info_edit = (ClearEditText) view.findViewById(R.id.info_edit);
        circle_group_list.addHeaderView(view);
        
        for (int i = 0; i < 10; i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("key_name", "群联盟分类名" + i);
            mlist.add(map);
        }
        circle_group_list.setAdapter(new CircleGroupAdapter(MyCircleGroupActivity.this, mlist));
    }
    
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
        case R.id.mime_back:
            finish();
            break;
        
        default:
            break;
        }
    }
}
