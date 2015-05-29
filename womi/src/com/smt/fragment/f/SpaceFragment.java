package com.smt.fragment.f;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smt.chashizaixian.MimeActivity;
import com.smt.chashizaixian.R;

public class SpaceFragment extends Fragment implements OnClickListener
{
    
    MimeActivity mimeActivity;
    private TextView product, special;
    private RelativeLayout layproduct,layspecial;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        
        View shareView = inflater.inflate(R.layout.space_tab_fragment, null);
        
        product = (TextView) shareView.findViewById(R.id.mime_tv_product);
        special = (TextView) shareView.findViewById(R.id.mime_tv_special);
        
        layproduct = (RelativeLayout) shareView.findViewById(R.id.mime_space_product);
        layspecial = (RelativeLayout) shareView.findViewById(R.id.mime_space_special);
        
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.spaceframe, new ProductFrag()).commit();
        
        shareView.findViewById(R.id.mime_space_product)
                .setOnClickListener(this);
        shareView.findViewById(R.id.mime_space_special)
                .setOnClickListener(this);
        return shareView;
    }
    
    @Override
    public void onResume()
    {
        super.onResume();
    }
    
    @Override
    public void onStart()
    {
        super.onStart();
    }
    
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
        case R.id.mime_space_product:
            product.setTextColor(getResources().getColor(R.color.lightwhite));
            special.setTextColor(getResources().getColor(R.color.black_color));
            layproduct.setBackgroundResource(R.drawable.whisper_btn_bg_all_hover);
            layspecial.setBackgroundResource(0);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.spaceframe, new ProductFrag()).commit();
            break;
        
        case R.id.mime_space_special:
            product.setTextColor(getResources().getColor(R.color.black_color));
            special.setTextColor(getResources().getColor(R.color.lightwhite));
            layproduct.setBackgroundResource(0);
            layspecial.setBackgroundResource(R.drawable.whisper_btn_bg_all_hover);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.spaceframe, new SpecialFrag()).commit();
            break;
        
        default:
            break;
        }
    }
}
