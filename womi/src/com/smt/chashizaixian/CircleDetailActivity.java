package com.smt.chashizaixian;

import com.smt.fragment.f.CircleNewsFrag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class CircleDetailActivity extends FragmentActivity implements OnClickListener
{
    
    private RadioGroup tabRadioGroup;
    private ViewPager circlePager;
    private TextView mime_detail, mime_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_detail);
        
        circlePager = (ViewPager) findViewById(R.id.circle_pager);
        circlePager.setAdapter(new CircleAdapter(getSupportFragmentManager()));
        tabRadioGroup = (RadioGroup) findViewById(R.id.tab_selector);
        mime_detail = (TextView) findViewById(R.id.mime_detail);
        mime_back = (TextView) findViewById(R.id.mime_back);
        
        setListener();
    }

    private void setListener()
    {
        mime_detail.setOnClickListener(this);
        mime_back.setOnClickListener(this);
        circlePager.setOnPageChangeListener(new OnPageChangeListener()
        {
            @Override
            public void onPageSelected(int arg0)
            {
                if (arg0 == 0)
                {
                    tabRadioGroup.check(R.id.radio001);
                }
                else if (arg0 == 1)
                {
                    tabRadioGroup.check(R.id.radio002);
                }
                else if (arg0 == 2)
                {
                    tabRadioGroup.check(R.id.radio003);
                }
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2){
            }
            
            @Override
            public void onPageScrollStateChanged(int arg0){
            }
        });
        
        tabRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch (checkedId)
                {
                case R.id.radio001:
                    circlePager.setCurrentItem(0);
                    break;
                    
                case R.id.radio002:
                    circlePager.setCurrentItem(1);
                    break;
                    
                case R.id.radio003:
                    circlePager.setCurrentItem(2);
                    break;
                
                default:
                    
                    break;
                }
            }
        });
    }
    
    class CircleAdapter extends FragmentStatePagerAdapter
    {
        public CircleAdapter(FragmentManager fm)
        {
            super(fm);
        }
        
        @Override
        public Fragment getItem(int position)
        {
            return new CircleNewsFrag();
        }
        
        @Override
        public int getCount()
        {
            return 3;
        }
    }

    @Override
    public void onClick(View v)
    {
        if (v == mime_detail)
        {
            startActivity(new Intent(CircleDetailActivity.this, CircleInfoActivity.class));
        }
        else if (v == mime_back)
        {
            finish();
        }
    }
}
