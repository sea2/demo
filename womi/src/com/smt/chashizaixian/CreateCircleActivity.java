package com.smt.chashizaixian;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CreateCircleActivity extends Activity implements OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_circle);
        
        Button next_step_btn = (Button) findViewById(R.id.next_step_btn);
        next_step_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
        case R.id.next_step_btn:
            startActivity(new Intent(CreateCircleActivity.this, WriteCircleIntroductionActivity.class));
            break;
        
        default:
            break;
        }
    }
}
