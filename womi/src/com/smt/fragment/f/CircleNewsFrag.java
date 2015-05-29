package com.smt.fragment.f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.smt.chashizaixian.R;
import com.smt.pullview.util.RefreshableView;
import com.smt.util.adapter.MimeShareAdapter;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CircleNewsFrag extends Fragment implements OnClickListener,RefreshableView.RefreshListener
{
    private View circle_footer;
    RefreshableView mRefreshableView;
    private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.frag_circle_new, null);
        initData();
        ListView cirNewslist = (ListView) layout.findViewById(R.id.listView);
        mRefreshableView = (RefreshableView) layout.findViewById(R.id.refresh_root);
        mRefreshableView.setRefreshListener(this);
        
        View cercle_header = inflater.inflate(R.layout.item_header_circlenews, null);
        circle_footer = inflater.inflate(R.layout.xlistview_footer, null);
        circle_footer.setOnClickListener(this);
        cirNewslist.addHeaderView(cercle_header, null, false);
        cirNewslist.addFooterView(circle_footer, null, false);
        cirNewslist.setAdapter(new MimeShareAdapter(getActivity(), mlist));
        return layout;
    }
    
    @Override
    public void onResume()
    {
        super.onResume();
    }
    
    @Override
    public void onClick(View v)
    {
        if (v == circle_footer)
        {
            circle_footer.findViewById(R.id.xlistview_footer_progressbar).setVisibility(View.VISIBLE);
            circle_footer.findViewById(R.id.xlistview_footer_hint_textview).setVisibility(View.INVISIBLE);
            Log.e("message.what", "1");
            handler.sendEmptyMessageDelayed(1, 2000);
        }
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

    Handler handler = new Handler()
    {
        @SuppressLint("HandlerLeak")
        public void handleMessage(Message message)
        {
            super.handleMessage(message);
            switch (message.what)
            {
            case 1:
                circle_footer.findViewById(R.id.xlistview_footer_progressbar).setVisibility(View.INVISIBLE);
                circle_footer.findViewById(R.id.xlistview_footer_hint_textview).setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "加载更多加载完毕",
                        Toast.LENGTH_SHORT).show();
                break;
                
            case 2:
                mRefreshableView.finishRefresh();
                Toast.makeText(getActivity(), R.string.toast_text, Toast.LENGTH_SHORT).show();
                break;
            
            default:
                break;
            }
            
            
        };
    };
    
    @Override
    public void onRefresh(RefreshableView view)
    {
        Log.e("message.what", "2");
        handler.sendEmptyMessageDelayed(2, 2000);
    }
}