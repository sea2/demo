package com.smt.linearlayout.addview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class LinearLayoutForListView extends LinearLayout {

    private AdapterForLinearLayout adapter;
    private OnClickListener onClickListener = null;

    /**
     * �󶨲���
     */
    public void bindLinearLayout() {
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            View v = adapter.getView(i, null, null);

            v.setOnClickListener(this.onClickListener);
            if (i == count - 1) {
                LinearLayout ly = (LinearLayout) v;
                ly.removeViewAt(2);
            }
            addView(v, i);
        }
    }

    public LinearLayoutForListView(Context context) {
        super(context);

    }

    public LinearLayoutForListView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    /**
     * ��ȡAdapter
     * 
     * @return adapter
     */
    public AdapterForLinearLayout getAdpater() {
        return adapter;
    }

    /**
     * ��������
     * 
     * @param adpater
     */
    public void setAdapter(AdapterForLinearLayout adpater) {
        this.adapter = adpater;
        bindLinearLayout();
    }

    /**
     * ��ȡ����¼�
     * 
     * @return
     */
    public OnClickListener getOnclickListner() {
        return onClickListener;
    }

    /**
     * ���õ���¼�
     * 
     * @param onClickListener
     */
    public void setOnclickLinstener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}
