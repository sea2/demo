package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
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
 * ��λ�Լ��Լ���ߵ���Ҫ����
 */
public class ChoosePlaceActivity extends Activity implements LocationSource,
        AMapLocationListener, OnMarkerClickListener, InfoWindowAdapter,
        OnPoiSearchListener, OnInfoWindowClickListener, OnClickListener
{
    private AMap aMap;
    private MapView mapView;
    private ListView listView_place;
    private OnLocationChangedListener mListener;
    private LocationManagerProxy mAMapLocationManager;
    private AlertDialog ad;
    private PoiResult poiResult; // poi���صĽ��
    private String keyWord = "";// poi�����ؼ���
    private int currentPage = 0;// ��ǰҳ�棬��0��ʼ����
    private PoiSearch.Query query;// Poi��ѯ������
    private String currentCity;// ����
    private PoiSearch poiSearch;// POI����
    private boolean hasGot;
    private ArrayList<Data> mlist = new ArrayList<Data>();
    private BaseAdapter baseAdapter;
    private View location_footer;
    private ImageView fullscreen;
    private TextView activity_selectimg_back;
    private TextView activity_selectimg_clear;
    private LatLonPoint lp;
    private AMapLocation aLocation;
    private RadioGroup tabRadioGroup;
    int poiType = 0;
    int lastType = 0;//��¼�ϴ�poi��������
    
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
            ad = ShowMsg.showProgressDialog(this, "���ڶ�λ�У����Ժ�");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        setContentView(R.layout.choose_place_activity);
        
        listView_place = (ListView) findViewById(R.id.listView_place);
        location_footer = getLayoutInflater().inflate(R.layout.listview_footer, null);
        fullscreen = (ImageView) findViewById(R.id.fullscreen);
        activity_selectimg_back=(TextView)findViewById(R.id.activity_selectimg_back);
        activity_selectimg_clear = (TextView) findViewById(R.id.activity_selectimg_clear);
        mapView = (MapView) findViewById(R.id.map);
        tabRadioGroup = (RadioGroup) findViewById(R.id.tab_selector);
        
        location_footer.setOnClickListener(this);
        activity_selectimg_clear.setOnClickListener(this);
        activity_selectimg_back.setOnClickListener(this);
        fullscreen.setOnClickListener(this);
        
        mapView.onCreate(savedInstanceState);// �˷���������д
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
        
        tabRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                lastType = poiType;
                switch (checkedId)
                {
                case R.id.radio001:
                    poiType = 0;
                    
                    break;
                    
                case R.id.radio002:
                    poiType = 1;
                    break;
                    
                case R.id.radio003:
                    poiType = 2;
                    break;
                    
                case R.id.radio004:
                    poiType = 3;
                    break;
                
                default:
                    poiType = 0;
                    break;
                }
                doSearchQuery(poiType);
            }
        });
        
        /**
         * ��ʼ��AMap����
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
     * ����һЩamap������
     */
    private void setUpMap()
    {
        // �Զ���ϵͳ��λС����
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.page_index_selected));// ����С�����ͼ��
        // myLocationStyle.strokeColor(Color.GRAY);// ����Բ�εı߿���ɫ
        // myLocationStyle.anchor(int,int)//����С�����ê��
        // aMap.setMyLocationRotateAngle(180);//���ö�λͼƬ��ת�ĽǶ�
        myLocationStyle.radiusFillColor(Color.parseColor("#00ffffff"));// ����Բ�ε������ɫ
        myLocationStyle.strokeWidth(0.0f);// ����Բ�εı߿��ϸ
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setZoomControlsEnabled(false);//���õ�ͼ�Ƿ���ʾ���Ű�ť
        aMap.getUiSettings().setTiltGesturesEnabled(false);//���õ�ͼ�Ƿ������б
        aMap.getUiSettings().setRotateGesturesEnabled(false);//���õ�ͼ�Ƿ������ת
        aMap.getUiSettings().setMyLocationButtonEnabled(false);// ����Ĭ�϶�λ��ť�Ƿ���ʾ������΢�ײ���ʾ
        aMap.setLocationSource(this);// ���ö�λ����
        aMap.setMyLocationEnabled(true);// ����Ϊtrue��ʾ��ʾ��λ�㲢�ɴ�����λ��false��ʾ���ض�λ�㲢���ɴ�����λ��Ĭ����false
        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));//���õ�ͼ���ű�������4 - 20
        
        aMap.setOnMarkerClickListener(this);// ��ӵ��marker�����¼�
        aMap.setInfoWindowAdapter(this);// �����Զ���InfoWindow��ʽ
        aMap.setOnInfoWindowClickListener(this);// ���õ��infoWindow�¼�������
    }
    
    /**
     * ����������д
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        mapView.onResume();
    }
    
    /**
     * ����������д
     */
    @Override
    protected void onPause()
    {
        super.onPause();
        mapView.onPause();
        deactivate();
    }
    
    /**
     * ����������д
     */
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    
    /**
     * ����������д
     */
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mapView.onDestroy();
    }
    
    /**
     * �˷����Ѿ�����
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
     * ��ʼ����poi����
     */
    protected void doSearchQuery(int poiType)
    {
        currentPage = 0;
        switch (poiType)
        {
        case 0:
            query = new PoiSearch.Query("", "С��", currentCity);// ��һ��������ʾ�����ַ������ڶ���������ʾpoi�������ͣ�������������ʾpoi�������򣨿��ַ�������ȫ����
            break;
            
        case 1:
            query = new PoiSearch.Query("", "����¥", currentCity);// ��һ��������ʾ�����ַ������ڶ���������ʾpoi�������ͣ�������������ʾpoi�������򣨿��ַ�������ȫ����
            break;
            
        case 2:
            query = new PoiSearch.Query("", "ѧУ", currentCity);// ��һ��������ʾ�����ַ������ڶ���������ʾpoi�������ͣ�������������ʾpoi�������򣨿��ַ�������ȫ����
            
            break;
        case 3:
            query = new PoiSearch.Query(keyWord, "", currentCity);// ��һ��������ʾ�����ַ������ڶ���������ʾpoi�������ͣ�������������ʾpoi�������򣨿��ַ�������ȫ����
            break;
        
        default:
            break;
        }
        
        query.setPageSize(10);// ����ÿҳ��෵�ض�����poiitem
        query.setPageNum(currentPage);// ���ò��һҳ
        poiSearch = new PoiSearch(this, query);    
        if (poiType == 0 || poiType == 1 || poiType == 2)
        {
            poiSearch.setBound(new SearchBound(lp, 2000, true));
        }
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }
    
    /**
     * ����鿴���ఴ��
     */
    private void showMore()
    {
        if (query != null && poiSearch != null && poiResult != null)
        {
            if (poiResult.getPageCount() - 1 > currentPage)
            {
                currentPage++;
                query.setPageNum(currentPage);// ���ò��һҳ
                poiSearch.searchPOIAsyn();
            }
            else
            {
                ToastUtil.show(ChoosePlaceActivity.this, R.string.no_result);
            }
        }
    }
    
    /**
     * poiû�����������ݣ�����һЩ�Ƽ����е���Ϣ
     */
    private void showSuggestCity(List<SuggestionCity> cities)
    {
        String infomation = "�Ƽ�����\n";
        for (int i = 0; i < cities.size(); i++)
        {
            infomation += "��������:" + cities.get(i).getCityName() + "��������:"
                    + cities.get(i).getCityCode() + "���б���:"
                    + cities.get(i).getAdCode() + "\n";
        }
        ToastUtil.show(ChoosePlaceActivity.this, infomation);
        
    }
    
    /**
     * ��λ�ɹ���ص�����
     */
    @Override
    public void onLocationChanged(AMapLocation aLocation)
    {
        if (mListener != null && aLocation != null)
        {
            this.aLocation = aLocation;
            if (ad.isShowing())
            {
                ad.dismiss();
            }
            if (!hasGot)
            {
                lp = new LatLonPoint(aLocation.getLatitude(), aLocation.getLongitude());
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
                    ToastUtil.show(ChoosePlaceActivity.this, "�Բ��𣬲�ѯ����������Ϣ");
                    return;
                }
                else
                {
                    doSearchQuery(0);
                }
                mListener.onLocationChanged(aLocation);// ��ʾϵͳС���㲢��λ����ǰλ��
                mlist.add(new Data(keyWord, "���ĵ�ǰλ��"));
                LatLng latlng = new LatLng(aLocation.getLatitude(),
                        aLocation.getLongitude());
                Marker marker = aMap.addMarker(new MarkerOptions()
                        .position(latlng)
                        .title(keyWord).snippet("���ĵ�ǰλ��")
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.map_pin))
                        .perspective(true).draggable(true));
                marker.showInfoWindow();// ����Ĭ����ʾһ��infowinfow
            }
            hasGot = true;
        }
    }
    
    /**
     * ���λ
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
             * 1.0.2�汾��������������true��ʾ��϶�λ�а���gps��λ��false��ʾ�����綨λ��Ĭ����true Location
             * API��λ����GPS�������϶�λ��ʽ
             * ����һ�������Ƕ�λprovider���ڶ�������ʱ�������5000���룬������������������λ���ף����ĸ������Ƕ�λ������
             */
            mAMapLocationManager.requestLocationUpdates(
                    LocationProviderProxy.AMapNetwork, 5000, 10, this);
        }
    }
    
    /**
     * ֹͣ��λ
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
     * �Զ���infowinfow����
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
     * POI��ѯ�ص�
     */
    @Override
    public void onPoiSearched(PoiResult result, int rCode)
    {
        if (rCode == 0)
        {
            if (result != null && result.getQuery() != null)
            {// ����poi�Ľ��
                if (result.getQuery().equals(query))
                {// �ж��Ƿ���ͬһ��
                    poiResult = result;
                    // ȡ����������poiitems�ж���ҳ
                    List<PoiItem> poiItems = poiResult.getPois();// ȡ�õ�һҳ��poiitem���ݣ�ҳ��������0��ʼ
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// ����������poiitem����ʱ���᷵�غ��������ؼ��ֵĳ�����Ϣ
                    
                    if (lastType != poiType)//�ж�poi�ϴ��������ͣ������ǰpoi���͸��ϴβ�һ���������mList����
                    {
                        mlist.clear();
                        aMap.clear();// ����֮ǰ��ͼ��
                        mlist.add(new Data(keyWord, "���ĵ�ǰλ��"));
                        LatLng latlng = new LatLng(aLocation.getLatitude(),
                                aLocation.getLongitude());
                        Marker marker = aMap.addMarker(new MarkerOptions()
                                .position(latlng)
                                .title(keyWord).snippet("���ĵ�ǰλ��")
                                .icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.map_pin))
                                .perspective(true).draggable(true));
                        marker.showInfoWindow();// ����Ĭ����ʾһ��infowinfow
                        lastType = poiType;
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
                ToastUtil.show(ChoosePlaceActivity.this, R.string.no_result);
            }
        }
        else if (rCode == 27)
        {
            ToastUtil.show(ChoosePlaceActivity.this, R.string.error_network);
        }
        else if (rCode == 32)
        {
//            ToastUtil.show(ChoosePlaceActivity.this, R.string.error_key);
        }
        else
        {
            ToastUtil.show(ChoosePlaceActivity.this, R.string.error_other);
        }
    }
    
    /**
     * �����Ϣ���ڵ���
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
