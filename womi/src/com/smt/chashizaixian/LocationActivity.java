package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import com.smt.config.Constants;
import com.smt.util.t.ShowMsg;
import com.smt.util.t.ToastUtil;

/**
 * 定位自己以及身边的主要建筑
 */
public class LocationActivity extends Activity implements LocationSource,
        AMapLocationListener, OnMarkerClickListener, InfoWindowAdapter,
        OnPoiSearchListener, OnInfoWindowClickListener, OnClickListener
{
    private AMap aMap;
    private MapView mapView;
    private ListView listView_place;
    private OnLocationChangedListener mListener;
    private LocationManagerProxy mAMapLocationManager;
    private AlertDialog ad;
    private PoiResult poiResult; // poi返回的结果
    private String keyWord = "";// poi搜索关键字
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    private String currentCity;// 城市
    private PoiSearch poiSearch;// POI搜索
    private boolean hasGot;
    private ArrayList<Data> mlist = new ArrayList<Data>();
    private BaseAdapter baseAdapter;
    private View location_footer;
    private ImageView fullscreen;
    private TextView activity_selectimg_back;
    private TextView activity_selectimg_clear;
    
    class Data
    {
        String title;
        String snippet;
        
        public Data(String title, String snippet)
        {
            super();
            this.snippet = snippet;
            this.title = title;
        }
    }
    
    class ViewHolder
    {
        TextView tv_title;
        TextView tv_snippet;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try
        {
            ad = ShowMsg.showProgressDialog(this, "正在定位中，请稍候…");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        setContentView(R.layout.locationsource_activity);
        
        listView_place = (ListView) findViewById(R.id.listView_place);
        location_footer = getLayoutInflater().inflate(R.layout.listview_footer, null);
        fullscreen = (ImageView) findViewById(R.id.fullscreen);
        activity_selectimg_back=(TextView)findViewById(R.id.activity_selectimg_back);
        activity_selectimg_clear = (TextView) findViewById(R.id.activity_selectimg_clear);
        mapView = (MapView) findViewById(R.id.map);
        
        location_footer.setOnClickListener(this);
        activity_selectimg_clear.setOnClickListener(this);
        activity_selectimg_back.setOnClickListener(this);
        fullscreen.setOnClickListener(this);
        
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
    }
    
    private void init()
    {
        baseAdapter = new MyLoactionAdapter();
        listView_place.addFooterView(location_footer, null, false);
        listView_place.setAdapter(baseAdapter);
        listView_place.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = getIntent();
                intent.putExtra(Constants.LOCAL_RESULT, mlist.get(position).title);
                setResult(Constants.LOCAL_REQUEST_CODE, intent);
                finish();
            }
        });
        
        /**
         * 初始化AMap对象
         */
        if (aMap == null)
        {
            aMap = mapView.getMap();
            setUpMap();
        }
    }
    
    class MyLoactionAdapter extends BaseAdapter
    {
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder = null;
            if (convertView == null)
            {
                convertView = getLayoutInflater().inflate(
                        R.layout.item_location, null);
                holder = new ViewHolder();
                holder.tv_title = (TextView) convertView
                        .findViewById(R.id.tv_title);
                holder.tv_snippet = (TextView) convertView
                        .findViewById(R.id.tv_snippet);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_snippet.setText(mlist.get(position).snippet);
            holder.tv_title.setText(mlist.get(position).title);
            return convertView;
        }
        
        @Override
        public long getItemId(int position)
        {
            return 0;
        }
        
        @Override
        public Object getItem(int position)
        {
            return null;
        }
        
        @Override
        public int getCount()
        {
            return mlist.size();
        }
    }
    
    /**
     * 设置一些amap的属性
     */
    private void setUpMap()
    {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.page_index_selected));// 设置小蓝点的图标
        // myLocationStyle.strokeColor(Color.GRAY);// 设置圆形的边框颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        // aMap.setMyLocationRotateAngle(180);//设置定位图片旋转的角度
        myLocationStyle.radiusFillColor(Color.parseColor("#00ffffff"));// 设置圆形的填充颜色
        myLocationStyle.strokeWidth(0.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setZoomControlsEnabled(false);//设置地图是否显示缩放按钮
        aMap.getUiSettings().setTiltGesturesEnabled(false);//设置地图是否可以倾斜
        aMap.getUiSettings().setRotateGesturesEnabled(false);//设置地图是否可以旋转
        aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示，仿照微米不显示
        aMap.setLocationSource(this);// 设置定位监听
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));//设置地图缩放比例，从4 - 20
        
        aMap.setOnMarkerClickListener(this);// 添加点击marker监听事件
        aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
        aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
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
        deactivate();
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
     * 此方法已经废弃
     */
    @Override
    public void onLocationChanged(Location location)
    {
    }
    
    @Override
    public void onProviderDisabled(String provider)
    {
    }
    
    @Override
    public void onProviderEnabled(String provider)
    {
    }
    
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
    }
    
    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery()
    {
        currentPage = 0;
        query = new PoiSearch.Query(keyWord, "", currentCity);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(8);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }
    
    /**
     * 点击查看更多按键
     */
    private void showMore()
    {
        if (query != null && poiSearch != null && poiResult != null)
        {
            if (poiResult.getPageCount() - 1 > currentPage)
            {
                currentPage++;
                query.setPageNum(currentPage);// 设置查后一页
                poiSearch.searchPOIAsyn();
            }
            else
            {
                ToastUtil.show(LocationActivity.this, R.string.no_result);
            }
        }
    }
    
    /**
     * poi没有搜索到数据，返回一些推荐城市的信息
     */
    private void showSuggestCity(List<SuggestionCity> cities)
    {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++)
        {
            infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }
        ToastUtil.show(LocationActivity.this, infomation);
        
    }
    
    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation aLocation)
    {
        if (mListener != null && aLocation != null)
        {
            if (ad.isShowing())
            {
                ad.dismiss();
            }
            if (!hasGot)
            {
                currentCity = aLocation.getCity();
                String desc = "";
                Bundle locBundle = aLocation.getExtras();
                if (locBundle != null)
                {
                    desc = locBundle.getString("desc");
                    keyWord = desc;

                    desc.length();
                }
                
                if ("".equals(keyWord))
                {
                    keyWord = aLocation.getCity();
                    ToastUtil.show(LocationActivity.this, "对不起，查询不到您的信息");
                    return;
                }
                else
                {
                    doSearchQuery();
                }
                mListener.onLocationChanged(aLocation);// 显示系统小蓝点
                LatLng latlng = new LatLng(aLocation.getLatitude(),
                        aLocation.getLongitude());
                Marker marker = aMap.addMarker(new MarkerOptions()
                        .position(latlng)
                        .title(keyWord).snippet("您的当前位置")
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.map_pin))
                        .perspective(true).draggable(true));
                marker.showInfoWindow();// 设置默认显示一个infowinfow
                
                mlist.add(new Data(keyWord, "您的当前位置"));
            }
            hasGot = true;
        }
    }
    
    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener)
    {
        mListener = listener;
        if (mAMapLocationManager == null)
        {
            mAMapLocationManager = LocationManagerProxy.getInstance(this);
            /*
             * mAMapLocManager.setGpsEnable(false);
             * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
             * API定位采用GPS和网络混合定位方式
             * ，第一个参数是定位provider，第二个参数时间最短是5000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
             */
            mAMapLocationManager.requestLocationUpdates(
                    LocationProviderProxy.AMapNetwork, 5000, 10, this);
        }
    }
    
    /**
     * 停止定位
     */
    @Override
    public void deactivate()
    {
        mListener = null;
        if (mAMapLocationManager != null)
        {
            mAMapLocationManager.removeUpdates(this);
            mAMapLocationManager.destory();
        }
        mAMapLocationManager = null;
        if (ad.isShowing())
        {
            ad.dismiss();
        }
    }
    
    @Override
    public boolean onMarkerClick(Marker marker)
    {
        marker.showInfoWindow();
        return false;
    }
    
    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view)
    {
        ImageView imageView = (ImageView) view.findViewById(R.id.badge);
//        imageView.setImageResource(R.drawable.s_n_dynamic_btn_gps_unselected);
        String title = marker.getTitle();
        TextView titleUi = ((TextView) view.findViewById(R.id.title));
        if (title != null)
        {
            SpannableString titleText = new SpannableString(title);
            titleText.setSpan(new ForegroundColorSpan(Color.BLACK), 0,
                    titleText.length(), 0);
            titleUi.setTextSize(13);
            titleUi.setText(titleText);
            
        }
        else
        {
            titleUi.setText("");
        }
        String snippet = marker.getSnippet();
        TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
        if (snippet != null)
        {
            SpannableString snippetText = new SpannableString(snippet);
            snippetText.setSpan(new ForegroundColorSpan(Color.GRAY), 0,
                    snippetText.length(), 0);
            snippetUi.setTextSize(12);
            snippetUi.setText(snippetText);
        }
        else
        {
             snippetUi.setText("");
        }
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
    public void onPoiItemDetailSearched(PoiItemDetail arg0, int arg1)
    {
        
    }
    
    /**
     * POI查询回调
     */
    @Override
    public void onPoiSearched(PoiResult result, int rCode)
    {
        if (rCode == 0)
        {
            if (result != null && result.getQuery() != null)
            {// 搜索poi的结果
                if (result.getQuery().equals(query))
                {// 判断是否是同一条
                    poiResult = result;
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    
                    if (poiItems != null && poiItems.size() > 0)
                    {
                        // aMap.clear();// 清理之前的图标
                        PoiOverlay poiOverlay = new PoiOverlay(aMap, poiItems);
                        poiOverlay.removeFromMap();
                        poiOverlay.addToMap();
                        // poiOverlay.zoomToSpan();//定位到当前（个人）
                        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
                    }
                    else if (suggestionCities != null
                            && suggestionCities.size() > 0)
                    {
                        showSuggestCity(suggestionCities);
                    }
                    else
                    {
                        ToastUtil.show(LocationActivity.this,
                                R.string.no_result);
                    }
                    
                    for (int i = 0; i < poiItems.size(); i++)
                    {
                        mlist.add(new Data(poiItems.get(i).getTitle(), poiItems
                                .get(i).getSnippet()));
                    }
                    if (ad.isShowing())
                    {
                        ad.dismiss();
                    }
                    baseAdapter.notifyDataSetChanged();
                }
            }
            else
            {
                ToastUtil.show(LocationActivity.this, R.string.no_result);
            }
        }
        else if (rCode == 27)
        {
            ToastUtil.show(LocationActivity.this, R.string.error_network);
        }
        else if (rCode == 32)
        {
            ToastUtil.show(LocationActivity.this, R.string.error_key);
        }
        else
        {
            ToastUtil.show(LocationActivity.this, R.string.error_other);
        }
    }
    
    /**
     * 点击信息窗口调用
     */
    @Override
    public void onInfoWindowClick(Marker marker)
    {
        Intent intent = getIntent();
        intent.putExtra(Constants.LOCAL_RESULT, marker.getTitle());
        setResult(Constants.LOCAL_REQUEST_CODE, intent);
        finish();
    }

    @Override
    public void onClick(View v)
    {
        if (v == location_footer)
        {
            showMore();
        }
        else if (v == fullscreen)
        {
            if (listView_place.isShown())
            {
                fullscreen.setBackgroundResource(R.drawable.max_screen_seletor);
                listView_place.setVisibility(View.GONE);
            }
            else
            {
                fullscreen.setBackgroundResource(R.drawable.min_screen_seletor);
                listView_place.setVisibility(View.VISIBLE);
            }
        }
        else if (v == activity_selectimg_back)
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
}
