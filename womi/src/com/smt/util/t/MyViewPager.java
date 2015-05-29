package com.smt.util.t;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class MyViewPager extends ViewPager {
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
    
    public MyViewPager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        
       if((v != this && v instanceof ViewPager)||v != this && v instanceof ScrollView) {
          return true;
       }
       return super.canScroll(v, checkV, dx, x, y);
    }
 
}
