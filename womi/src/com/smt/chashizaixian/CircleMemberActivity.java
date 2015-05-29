package com.smt.chashizaixian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smt.model.SortModel;
import com.smt.util.adapter.CircleMemberAdapter;
import com.smt.util.listview.XListView;
import com.smt.util.listview.XListView.IXListViewListener;
import com.smt.util.t.CharacterParser;
import com.smt.util.t.PinyinComparator;
import com.smt.util.t.SideBar;
import com.smt.util.t.SideBar.OnTouchingLetterChangedListener;
import com.smt.util.t.ToastUtil;


public class CircleMemberActivity extends Activity implements OnClickListener,IXListViewListener {
	private XListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private CircleMemberAdapter adapter;
	
	/**
	 * ����ת����ƴ������
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	
	/**
	 * ����ƴ��������ListView�����������
	 */
	private PinyinComparator pinyinComparator;
    private View back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circle_member);
		initViews();
	}

	private void initViews() {
		//ʵ��������תƴ����
		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();
		
		back = findViewById(R.id.back);
		back.setOnClickListener(this);
		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		
		sortListView = (XListView) findViewById(R.id.friend_list);
		sortListView.setPullLoadEnable(false);
		sortListView.setXListViewListener(this);
		sortListView.setRefreshTime(new Date().toLocaleString());
		
		SourceDateList = filledData(getResources().getStringArray(R.array.date));
		// ����a-z��������Դ����
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new CircleMemberAdapter(this, SourceDateList);
		sortListView.setAdapter(adapter);
		
		//�����Ҳഥ������
        sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
            
            @Override
            public void onTouchingLetterChanged(String s) {
                //����ĸ�״γ��ֵ�λ��
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    sortListView.setSelection(position+2);
                }
                if (position==-1) {
                    sortListView.setSelection(0);
                }
                
                
            }
        });
		sortListView.setOnItemClickListener(new OnItemClickListener() {
		    
		    @Override
		    public void onItemClick(AdapterView<?> parent, View view,
		            int position, long id) {
		        //����Ҫ����adapter.getItem(position)����ȡ��ǰposition����Ӧ�Ķ���
		        Toast.makeText(getApplication(), ((SortModel)adapter.getItem(position - 1)).getName(), Toast.LENGTH_SHORT).show();
		        startActivity(new Intent(CircleMemberActivity.this, CircleMemberInfoActivity.class));
		    }
		});
	}

	/**
	 * ΪListView�������
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String [] date){
		List<SortModel> mSortList = new ArrayList<SortModel>();
		for(int i=0; i<date.length; i++)
		{
		    //�жϵõ���dateְֵλ������ǹ����� ��ӵ�mSortList��ǰ����
		}
		for(int i=0; i<date.length; i++){
		    //�жϵõ���dateְֵλ������ǹ��������������ݣ����������return
		    
			SortModel sortModel = new SortModel();
			sortModel.setName(date[i]);
			//����ת����ƴ��
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();
			
			// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
			if(sortString.matches("[A-Z]")){
				sortModel.setSortLetters(sortString.toUpperCase());
			}else{
				sortModel.setSortLetters("#");
			}
			
			mSortList.add(sortModel);
		}
		return mSortList;
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
		    finish();
		    break;
		    
		default:
			break;
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}
	
}
