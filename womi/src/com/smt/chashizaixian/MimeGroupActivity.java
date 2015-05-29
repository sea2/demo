package com.smt.chashizaixian;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MimeGroupActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group);
		
		View info_more = findViewById(R.id.info_more);
		View group_layout_01 = findViewById(R.id.group_layout_01);
		View group_layout_02 = findViewById(R.id.group_layout_02);
		View group_layout_03 = findViewById(R.id.group_layout_03);
		View group_layout_04 = findViewById(R.id.group_layout_04);
		View group_layout_05 = findViewById(R.id.group_layout_05);
		View group_layout_06 = findViewById(R.id.group_layout_06);
		View group_layout_07 = findViewById(R.id.group_layout_07);
		info_more.setOnClickListener(this);
		group_layout_01.setOnClickListener(this);
		group_layout_02.setOnClickListener(this);
		group_layout_03.setOnClickListener(this);
		group_layout_04.setOnClickListener(this);
		group_layout_05.setOnClickListener(this);
		group_layout_06.setOnClickListener(this);
		group_layout_07.setOnClickListener(this);
	}

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
        case R.id.info_more:
            startActivity(new Intent(MimeGroupActivity.this, CreateCircleActivity.class));
            break;
        case R.id.group_layout_01:
            startActivity(new Intent(MimeGroupActivity.this, NearbyCircleActivity.class));
            break;
        case R.id.group_layout_02:
            startActivity(new Intent(MimeGroupActivity.this, CategorySearchActivity.class));
            break;
        case R.id.group_layout_03:
            startActivity(new Intent(MimeGroupActivity.this, MyCircleActivity.class));
            break;
        case R.id.group_layout_04:
            startActivity(new Intent(MimeGroupActivity.this, MyCircleGroupActivity.class));
            break;
        case R.id.group_layout_05:
            startActivity(new Intent(MimeGroupActivity.this, NearbyPeopleActivity.class));
            break;
        case R.id.group_layout_06:
            startActivity(new Intent(MimeGroupActivity.this, RadarActivity.class));
            break;
        case R.id.group_layout_07:
            startActivity(new Intent(MimeGroupActivity.this, CaptureActivity.class));
            break;
        
        default:
            break;
        }
    }
}
