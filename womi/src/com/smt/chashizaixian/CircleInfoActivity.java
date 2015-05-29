package com.smt.chashizaixian;

import com.smt.config.Constants;
import com.smt.util.t.ShowMsg;
import com.smt.util.t.ShowMsg.ItemSelected;
import com.smt.util.t.SlipButton;
import com.smt.util.t.SlipButton.OnChangedListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class CircleInfoActivity extends Activity implements OnClickListener
{
    
    private View place, mime_back, new_msg_lay, touxiang_layout;
    private TextView detail_setting, new_message;
    private SlipButton slipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_info);
        init();
    }

    private void init()
    {
        place = findViewById(R.id.place);
        detail_setting = (TextView) findViewById(R.id.detail_setting);
        new_msg_lay = findViewById(R.id.new_msg_lay);
        new_message = (TextView)findViewById(R.id.new_message);
        slipButton = (SlipButton) findViewById(R.id.message_shouye);
        touxiang_layout = findViewById(R.id.touxiang_layout);
        mime_back = findViewById(R.id.mime_back);
        
        place.setOnClickListener(this);
        detail_setting.setOnClickListener(this);
        new_msg_lay.setOnClickListener(this);
        touxiang_layout.setOnClickListener(this);
        mime_back.setOnClickListener(this);
        slipButton.SetOnChangedListener(new OnChangedListener()
        {
            //slipButton滑动开关滑动或点击事件调用接口
            @Override
            public void OnChanged(boolean CheckState)
            {
                Log.e("CheckState", "CheckState------>" + CheckState);
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
        case R.id.place:
            startActivity(new Intent(CircleInfoActivity.this, CirclePlaceActivity.class));
            break;
            
        case R.id.detail_setting:
            startActivity(new Intent(CircleInfoActivity.this, CircleDetailSettingActivity.class));
            break;
            
        case R.id.new_msg_lay:
            ShowMsg.showChoiceDialog(this, Constants.MESSAGE_REMIND, 1 ,new ItemSelected()
            {
                @Override
                public void itemClick(int selected) 
                {
                    new_message.setText(Constants.MESSAGE_REMIND[selected]);
                }
            });
            break;
        case R.id.touxiang_layout:
            startActivity(new Intent(CircleInfoActivity.this, CircleMemberActivity.class));
            break;
            
        case R.id.mime_back:
            finish();
            break;
        
        default:
            break;
        }
    }
}
