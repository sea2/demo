package com.smt.util.t;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ExpandableListView;

/**
 * @author ScrollView 和 GridView 或ExpandableListView显示冲突问题
 */
public class MyExpandableListView extends ExpandableListView 
{
    public MyExpandableListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    
    public MyExpandableListView(Context context)
    {
        super(context);
    }
    
    public MyExpandableListView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
        MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
    
    // 如果MyExpandListView动作是滑动，则截断MyExpandListView的滑动事件，执行父View的滑动事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        if (ev.getAction() == MotionEvent.ACTION_MOVE)
        {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
