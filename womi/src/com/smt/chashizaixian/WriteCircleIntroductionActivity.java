package com.smt.chashizaixian;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class WriteCircleIntroductionActivity extends Activity implements OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_circle_info);
        
        findViewById(R.id.button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
        case R.id.button1:
            startActivity(new Intent(WriteCircleIntroductionActivity.this, ChoosePlaceActivity.class));
            break;
        
        default:
            break;
        }
    }
}
