package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.smt.util.adapter.SearchAdapter;
import com.smt.util.t.ClearEditText;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class CategorySearchActivity extends Activity implements OnClickListener
{
    private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
    private View mime_back;
    private ClearEditText info_edit;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_and_mycircle_group);
        
        ListView search_list = (ListView) findViewById(R.id.listview);
        mime_back = findViewById(R.id.mime_back);
        findViewById(R.id.mime_detail).setVisibility(View.INVISIBLE);
        
        View view = getLayoutInflater().inflate(R.layout.head_search, null);
        info_edit = (ClearEditText) view.findViewById(R.id.info_edit);
        search_list.addHeaderView(view);
        
        mime_back.setOnClickListener(this);
        for (int i = 0; i < 10; i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("key_name", "∑÷¿‡À—À˜" + i);
            mlist.add(map);
        }
        search_list.setAdapter(new SearchAdapter(CategorySearchActivity.this, mlist));
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
