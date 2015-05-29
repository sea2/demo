package com.smt.fragment.f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.smt.chashizaixian.ProductDetailActivity;
import com.smt.chashizaixian.R;
import com.smt.util.adapter.ProductAdapter;
import com.smt.util.t.MyGridView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ProductFrag extends Fragment implements OnClickListener
{
    private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
    private View loading;
    private View more;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.frag_product, null);
        Activity activity = getActivity();
        MyGridView products = (MyGridView) layout
                .findViewById(R.id.product_item_grid);
        products.setNumColumns(2);
        initData();
        products.setAdapter(new ProductAdapter(activity, mlist));
        loading = layout.findViewById(R.id.loading);
        more = layout.findViewById(R.id.more);
        more.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loading.setVisibility(View.VISIBLE);
                more.setVisibility(View.INVISIBLE);
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        });
        
        products.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {
                startActivity(new Intent(getActivity(),
                        ProductDetailActivity.class));
            }
        });
        return layout;
    }
    
    Handler handler = new Handler()
    {
        @SuppressLint("HandlerLeak")
        public void handleMessage(Message message)
        {
            super.handleMessage(message);
            loading.setVisibility(View.INVISIBLE);
            more.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "加载更多加载完毕",
                    Toast.LENGTH_SHORT).show();
        };
    };
    
    @Override
    public void onResume()
    {
        super.onResume();
    }
    
    @Override
    public void onClick(View v)
    {
        
    }
    
    public void initData()
    {
        for (int i = 0; i < 15; i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("key_name", "username" + i);
            mlist.add(map);
        }
    }
}