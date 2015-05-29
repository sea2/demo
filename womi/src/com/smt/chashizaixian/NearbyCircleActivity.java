package com.smt.chashizaixian;

import java.util.List;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.smt.pullview.util.RefreshableView;
import com.smt.pullview.util.RefreshableView.RefreshListener;
import com.smt.util.adapter.CircleExpandAdapter;
import com.smt.util.adapter.SpecialExpandAdapter;
import com.smt.util.t.ClearEditText;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class NearbyCircleActivity extends Activity implements OnClickListener,RefreshListener,AMapLocationListener
{
    
    private ExpandableListView circleList;
    public String[] groups = { "中央美地", "书香佳缘", "太微山庄", "国际山庄", "国贸阳光"};  
    public String[][] child = {{ "", "", ""}, { ""}, { "", ""}, { "", ""}, { ""} };
    private CircleExpandAdapter circleAdapter;
    private View mime_back, mime_detail;
    private ClearEditText info_edit;
    private RefreshableView mRefreshableView;
    private boolean hasGot;//是否已经获得当前位置
    private LocationManagerProxy mAMapLocManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_circle);
        
        circleList = (ExpandableListView) findViewById(R.id.circle_expand_list);
        mime_back = findViewById(R.id.mime_back);
        View view = getLayoutInflater().inflate(R.layout.head_search, null);
        info_edit = (ClearEditText) view.findViewById(R.id.info_edit);
        mRefreshableView = (RefreshableView) findViewById(R.id.refresh_root);
        mime_detail = findViewById(R.id.mime_detail);
        
        mAMapLocManager = LocationManagerProxy.getInstance(this);
        mAMapLocManager.requestLocationUpdates(LocationProviderProxy.AMapNetwork, 5000, 10, this);
        mime_back.setOnClickListener(this);
        circleList.setDivider(null);
        circleList.setGroupIndicator(null);
        circleList.addHeaderView(view);
        mRefreshableView.setRefreshListener(this);
        circleAdapter = new CircleExpandAdapter(NearbyCircleActivity.this);
        mime_detail.setOnClickListener(this);
        
        List<CircleExpandAdapter.TreeNode> treeNode = circleAdapter.GetTreeNode();  
        for (int i = 0; i < groups.length; i++)  
        {  
            CircleExpandAdapter.TreeNode node = new CircleExpandAdapter.TreeNode();  
            node.parent = groups[i];
            for (int j = 0; j < child[i].length; j++)  
            {  
                node.childs.add(child[i][j]);  
            }  
            treeNode.add(node);  
        }
        circleAdapter.UpdateTreeNode(treeNode);
        circleList.setAdapter(circleAdapter);
        circleList.expandGroup(0);
        for (int i = 0; i < groups.length; i++) 
        {
            circleList.expandGroup(i);
        };
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
        case R.id.mime_back:
            finish();
            break;
        case R.id.mime_detail:
            startActivity(new Intent(NearbyCircleActivity.this, CreateCircleActivity.class));
            break;
        
        default:
            break;
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
                Toast.makeText(NearbyCircleActivity.this, "加载更多加载完毕",
                        Toast.LENGTH_SHORT).show();
                break;
                
            case 2:
                mRefreshableView.finishRefresh();
                Toast.makeText(NearbyCircleActivity.this, R.string.toast_text, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onLocationChanged(AMapLocation location)
    {
        if (location != null) {
            if (!hasGot)
            {
                Double geoLat = location.getLatitude();//纬度
                Double geoLng = location.getLongitude();//经度
                
                Toast.makeText(this, "当前经纬度：(" + geoLng + "," + geoLat + ")", Toast.LENGTH_SHORT).show();
            }
            hasGot = true;
        }
    }
    @Override
    public void onLocationChanged(Location location)
    {
        // TODO Auto-generated method stub
    }
    @Override
    public void onProviderDisabled(String provider)
    {
        // TODO Auto-generated method stub
    }
    @Override
    public void onProviderEnabled(String provider)
    {
        // TODO Auto-generated method stub
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
        // TODO Auto-generated method stub
    }
}
