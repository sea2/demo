package com.smt.chashizaixian;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.smt.config.Constants;
import com.smt.util.t.ShowMsg;

/**
 * 按照所传经纬度，显示地图
 */
public class CirclePlaceActivity extends Activity implements InfoWindowAdapter, OnClickListener
                            , OnMapLoadedListener, OnInfoWindowClickListener
{
    private AMap aMap;
    private MapView mapView;
    private AlertDialog ad;
    private TextView activity_selectimg_back;
    private TextView activity_selectimg_clear;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try
        {
            ad = ShowMsg.showProgressDialog(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        setContentView(R.layout.place_activity);
        
        activity_selectimg_back=(TextView)findViewById(R.id.activity_selectimg_back);
        activity_selectimg_clear = (TextView) findViewById(R.id.activity_selectimg_clear);
        mapView = (MapView) findViewById(R.id.map);
        
        activity_selectimg_clear.setOnClickListener(this);
        activity_selectimg_back.setOnClickListener(this);
        
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
    }
    
    private void init()
    {
        /**
         * 初始化AMap对象
         */
        if (aMap == null)
        {
            aMap = mapView.getMap();
            setUpMap();
        }
    }
    
    /**
     * 设置一些amap的属性
     */
    private void setUpMap()
    {
        aMap.getUiSettings().setZoomControlsEnabled(true);//设置地图是否显示缩放按钮
        aMap.getUiSettings().setTiltGesturesEnabled(false);//设置地图是否可以倾斜
        aMap.getUiSettings().setRotateGesturesEnabled(false);//设置地图是否可以旋转
        aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示，仿照微米不显示
        
        aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
        aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
        aMap.setOnInfoWindowClickListener(this);
        
        addMarkersToMap();
    }
    
    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {
        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
        giflist.add(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        giflist.add(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_RED));
        giflist.add(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(Constants.CHENGDU).title("成都市")
                .snippet("成都市:30.679879, 104.064855").icons(giflist)
                .perspective(true).draggable(true).period(50)).showInfoWindow();
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.CHENGDU, 18));//当前视图移动到定位点，缩放等级为18
    }
    
    /**
     * 方法必须重写
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        mapView.onResume();
    }
    
    /**
     * 方法必须重写
     */
    @Override
    protected void onPause()
    {
        super.onPause();
        mapView.onPause();
    }
    
    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    
    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mapView.onDestroy();
    }
    
    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view)
    {
        TextView titleUi = ((TextView) view.findViewById(R.id.title));
        titleUi.setText("这是标题啊");
        TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
        snippetUi.setText("这是内容，呵呵呵呵呵");
    }
    
    @Override
    public View getInfoContents(Marker marker)
    {
        View infoWindow = getLayoutInflater().inflate(
                R.layout.custom_info_window, null);
        
        render(marker, infoWindow);
        return infoWindow;
    }
    
    @Override
    public View getInfoWindow(Marker marker)
    {
        View infoWindow = getLayoutInflater().inflate(
                R.layout.custom_info_window, null);
        
        render(marker, infoWindow);
        return infoWindow;
    }
    
    @Override
    public void onClick(View v)
    {
        if (v == activity_selectimg_back)
        {
            finish();
        }
        else if (v == activity_selectimg_clear)
        {
            Intent intent = getIntent();
            intent.putExtra(Constants.LOCAL_IF_CLEAR, true);
            setResult(Constants.LOCAL_REQUEST_CODE, intent);
            finish();
        }
    }

    @Override
    public void onMapLoaded()
    {
        if (ad.isShowing())
        {
            ad.dismiss();
        }
    }

    @Override
    public void onInfoWindowClick(Marker arg0)
    {
        
    }

}
