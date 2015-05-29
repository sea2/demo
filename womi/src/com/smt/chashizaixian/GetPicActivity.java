package com.smt.chashizaixian;

import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;

import com.smt.pub.util.AlbumHelper;
import com.smt.pub.util.Bimp;
import com.smt.pub.util.ImageBucket;
import com.smt.util.adapter.ImageBucketAdapter;

public class GetPicActivity extends Activity implements OnClickListener{
	// ArrayList<Entity> dataList;//����װ������Դ���б�
	List<ImageBucket> dataList;
	GridView gridView;
	ImageBucketAdapter adapter;// �Զ����������
	AlbumHelper helper;
	public static final String EXTRA_IMAGE_LIST = "imagelist";
	public static Bitmap bimap;
	
	private TextView back_img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_bucket);

		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());
       
		initData();
		initView();
	}

	/**
	 * ��ʼ������
	 */
	private void initData() {
		// /**
		// * ������Ǽ����Ѿ���������߱��ؽ����������ݣ�����ֱ��������ģ����10��ʵ���ֱ࣬��װ���б���
		// */
		// dataList = new ArrayList<Entity>();
		// for(int i=-0;i<10;i++){
		// Entity entity = new Entity(R.drawable.picture, false);
		// dataList.add(entity);
		// }
		dataList = helper.getImagesBucketList(false);	
		bimap=BitmapFactory.decodeResource(
				getResources(),
				R.drawable.icon_addpic_unfocused);
	}

	/**
	 * ��ʼ��view��ͼ
	 */
	private void initView() {
		
		
		back_img=(TextView)findViewById(R.id.back_img);
		back_img.setOnClickListener(this);
		
		
		gridView = (GridView) findViewById(R.id.gridview);
		adapter = new ImageBucketAdapter(GetPicActivity.this, dataList);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/**
				 * ����position���������Ի�ø�GridView����View��󶨵�ʵ���࣬Ȼ���������isSelected״̬��
				 * ���ж��Ƿ���ʾѡ��Ч���� ����ѡ��Ч���Ĺ��������������Ĵ����л���˵��
				 */
				// if(dataList.get(position).isSelected()){
				// dataList.get(position).setSelected(false);
				// }else{
				// dataList.get(position).setSelected(true);
				// }
				/**
				 * ֪ͨ���������󶨵����ݷ����˸ı䣬Ӧ��ˢ����ͼ
				 */
				// adapter.notifyDataSetChanged();
				Intent intent = new Intent(GetPicActivity.this,
						ImageGridActivity.class);
				intent.putExtra(GetPicActivity.EXTRA_IMAGE_LIST,
						(Serializable) dataList.get(position).imageList);				
				startActivity(intent);
				finish();
			}

		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back_img:
		   if (Bimp.act_bool){
			Intent intent = new Intent(GetPicActivity.this,PublishedActivity.class);
			Bundle bundle = new Bundle();
    		bundle.putBoolean("pubsort", true);
			intent.putExtras(bundle);
			startActivity(intent);
			Bimp.act_bool=false;
			}
			this.finish();
			
			break;

		default:
			break;
		}
	}
}