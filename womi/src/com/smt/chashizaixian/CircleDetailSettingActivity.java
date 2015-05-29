package com.smt.chashizaixian;

import com.smt.config.Constants;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class CircleDetailSettingActivity extends Activity implements OnClickListener
{
    private View mime_back;
    private View my_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_detail_setting);
        initView();
    }

    private void initView()
    {
        mime_back = findViewById(R.id.mime_back);
        my_nickname = findViewById(R.id.my_nickname);
        mime_back.setOnClickListener(this);
        my_nickname.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
        case R.id.mime_back:
            finish();
            break;
            
        case R.id.my_nickname:
            Intent intent = new Intent(CircleDetailSettingActivity.this, UpdateSignatureActivity.class);
            startActivityForResult(intent , Constants.EDIT_REQUEST_CODE);
        default:
            break;
        }
    }
}
