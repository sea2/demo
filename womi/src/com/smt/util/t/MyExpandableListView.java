package com.smt.util.t;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ExpandableListView;

/**
 * @author ScrollView �� GridView ��ExpandableListView��ʾ��ͻ����
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
    
    // ���MyExpandListView�����ǻ�������ض�MyExpandListView�Ļ����¼���ִ�и�View�Ļ����¼�
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
