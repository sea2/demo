package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.smt.util.adapter.SearchAdapter;
import com.smt.util.adapter.SearchPeopleAdapter;
import com.smt.util.t.ClearEditText;
import com.smt.util.t.ShowMsg;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NearbyPeopleActivity extends Activity implements OnClickListener,AMapLocationListener
{
    private ArrayList<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
    private View mime_back;
    private ListView search_list;
    private LocationManagerProxy mAMapLocManager;
    private AlertDialog ad;
    private boolean hasGot;//是否已经获得当前位置标记
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_people);
        search_list = (ListView) findViewById(R.id.listview);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("附近的人");
        mime_back = findViewById(R.id.mime_back);
        mime_back.setOnClickListener(this);
        TextView more = (TextView) findViewById(R.id.mime_detail);
        more.setText("更多");
        try
        {
            ad = ShowMsg.showProgressDialog(this, "正在加载附近的人…");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        more.setOnClickListener(this);
        mAMapLocManager = LocationManagerProxy.getInstance(this);
        mAMapLocManager.requestLocationUpdates(
                LocationProviderProxy.AMapNetwork, 5000, 10, this);
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

    @Override
    public void onLocationChanged(AMapLocation location)
    {
        if (location != null) {
            if (!hasGot)
            {
                if (ad.isShowing())
                {
                    ad.dismiss();
                }
                Double geoLat = location.getLatitude();//纬度
                Double geoLng = location.getLongitude();//经度
                
                Toast.makeText(this, "当前经纬度：(" + geoLng + "," + geoLat + ")", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < 10; i++)
                {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("key_name", "附近的人" + i);
                    mlist.add(map);
                }
                search_list.setAdapter(new SearchPeopleAdapter(NearbyPeopleActivity.this, mlist));
            }
            hasGot = true;
        }
    }
    
    @Override
    public void onLocationChanged(Location location){
        
    }
    @Override
    public void onProviderDisabled(String provider){
        
    }

    @Override
    public void onProviderEnabled(String provider){
        
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){
        
    }
}
