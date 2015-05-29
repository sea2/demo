package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.smt.pub.util.AlbumHelper;
import com.smt.pub.util.Bimp;
import com.smt.pub.util.FileUtils;
import com.smt.pub.util.ImageItem;
import com.smt.util.adapter.ImageGridAdapter;
import com.smt.util.adapter.ImageGridAdapter.TextCallback;

public class ImageGridActivity extends Activity {
    public static final String EXTRA_IMAGE_LIST = "imagelist";

	// ArrayList<Entity> dataList;
	List<ImageItem> dataList;
	GridView gridView;
	ImageGridAdapter adapter;
	AlbumHelper helper;
	TextView bt;
	
	TextView img_back;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(ImageGridActivity.this, "最多选择9张图片", 400).show();
				break;

            default:
                break;
            }
        }
    };

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_grid);

        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());

        dataList = (List<ImageItem>) getIntent().getSerializableExtra(
                EXTRA_IMAGE_LIST);
      
		initView();
		bt = (TextView) findViewById(R.id.bt);
		bt.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<String>();
                Collection<String> c = adapter.map.values();
                Iterator<String> it = c.iterator();
                for (; it.hasNext();) {
                    list.add(it.next());
                }

				if (Bimp.act_bool) {					
					Intent intent = new Intent(ImageGridActivity.this,PublishedActivity.class);
					Bundle bundle = new Bundle();
		    		bundle.putBoolean("pubsort", true);
					intent.putExtras(bundle);
					startActivity(intent);					
					Bimp.act_bool = false;
				}
				for (int i = 0; i < list.size(); i++) {
					if (Bimp.drr.size() < 9) {
						Bimp.drr.add(list.get(i));
					}
				}
				finish();
			}

        });
    }

	private void initView() {
		
		
		img_back=(TextView)findViewById(R.id.img_back);
		img_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				    Bimp.bmp.clear();
					Bimp.drr.clear();
					Bimp.max = 0;										
					FileUtils.deleteDir();
						
				   if (Bimp.act_bool) {
					
				
					Intent intent = new Intent(ImageGridActivity.this,PublishedActivity.class);
					Bundle bundle = new Bundle();
		    		bundle.putBoolean("pubsort", true);
					intent.putExtras(bundle);
					startActivity(intent);
					Bimp.act_bool=false;
			      }
					
					finish();
					
					
			}
		});
		
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new ImageGridAdapter(ImageGridActivity.this, dataList,
				mHandler);
		gridView.setAdapter(adapter);
		adapter.setTextCallback(new TextCallback() {
			public void onListen(int count) {
				if (count==0) {
				
					bt.setText("完成");
					
				}else {
					bt.setText("完成" + "(" + count + ")");
				}
				
			}
		});

        gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
				// if(dataList.get(position).isSelected()){
				// dataList.get(position).setSelected(false);
				// }else{
				// dataList.get(position).setSelected(true);
				// }
				
				adapter.notifyDataSetChanged();
			}

        });

    }
}
