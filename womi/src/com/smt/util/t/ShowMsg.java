package com.smt.util.t;

import com.smt.chashizaixian.R;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * 显示消息类
 * 
 * @author zxq
 * 
 */
public class ShowMsg
{
    /**
     * 显示带进度条的对话框
     * 
     * @param context
     * @param msg
     * @return
     */
    public static AlertDialog showProgressDialog(Context context, String msg)
    {
        final AlertDialog ad = new AlertDialog.Builder(context).show();
        View view = LayoutInflater.from(context).inflate(
                R.layout.progressdialog, null);
        Window window = ad.getWindow();
        RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(
                (int) (240 * DeviceInfo.getDensity(context)),
                (int) (90 * DeviceInfo.getDensity(context)));
        parms.leftMargin = (int) (90 * DeviceInfo.getDensity(context));
        parms.rightMargin = (int) (90 * DeviceInfo.getDensity(context));
        window.setContentView(view, parms);
        TextView mTextView = (TextView) view.findViewById(R.id.msg);
        mTextView.setText(msg);
        return ad;
    }
    
    public static AlertDialog showProgressDialog(Context context)
    {
        return showProgressDialog(context, "正在定位中,请稍候");
    }
    
    /**
     * 显示单选按钮
     * 
     * @param choices
     * 显示内容
     */
    public static void showChoiceDialog(final Context context,
            final String[] choices, int choosed, ItemSelected listener)
    {
        showChoiceDialog(context, choices, choosed, 310, listener);
    }
    
    private static void showChoiceDialog(final Context context,
            final String[] choices, int choosed, int height,
            final ItemSelected listener)
    {
        final AlertDialog ad = new AlertDialog.Builder(context).show();
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_choice, null);
        Window window = ad.getWindow();
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                (int) (260 * DeviceInfo.getDensity(context)),
                LayoutParams.WRAP_CONTENT);
        window.setContentView(view, lp);
        ListView lv = (ListView) view.findViewById(R.id.choices);
        final MyAdapter adapter = new ShowMsg().new MyAdapter(context, choosed,
                choices);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long arg3)
            {
                // TODO Auto-generated method stub
                adapter.setChoosed(position);
                ad.dismiss();
                listener.itemClick(position);
            }
            
        });
    }
    private class MyAdapter extends BaseAdapter
    {
        public int choosed = 0;
        public String[] choices;
        private Context context;
        
        public MyAdapter(Context context, int choosed, String[] choices)
        {
            this.choices = choices;
            this.choosed = choosed;
            this.context = context;
        }
        
        public void setChoosed(int choosed)
        {
            this.choosed = choosed;
            this.notifyDataSetChanged();
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return choices.length;
        }
        
        @Override
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return choices[position];
        }
        
        @Override
        public long getItemId(int position)
        {
            // TODO Auto-generated method stub
            return position;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // TODO Auto-generated method stub
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.choice_item, null);
            RadioButton rd = (RadioButton) convertView.findViewById(R.id.rd);
            TextView tv = (TextView) convertView.findViewById(R.id.tx);
            if (position == choosed)
            {
                rd.setChecked(true);
            }
            else
            {
                rd.setChecked(false);
            }
            tv.setText(choices[position]);
            return convertView;
        }
    }
    public interface ItemSelected
    {
        public void itemClick(int selected);
    }
}
