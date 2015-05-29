package com.smt.chashizaixian;

import java.util.List;

import com.smt.util.adapter.CircleExpandAdapter;
import com.smt.util.adapter.SpecialExpandAdapter;
import com.smt.util.t.ClearEditText;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class MyCircleActivity extends Activity implements OnClickListener
{
    
    private ExpandableListView circleList;
    public String[] groups = { "创建的群组", "加入的群租", "申请的群组"};  
    public String[][] child = {{ ""}, { "", ""}, { "", ""}};
    private CircleExpandAdapter circleAdapter;
    private View mime_back;
    private ClearEditText info_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_circle);
        
        circleList = (ExpandableListView) findViewById(R.id.circle_expand_list);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("我的圈组");
        findViewById(R.id.mime_detail).setVisibility(View.INVISIBLE);
        mime_back = findViewById(R.id.mime_back);
        mime_back.setOnClickListener(this);
        circleList.setDivider(null);
        circleList.setGroupIndicator(null);
        circleAdapter = new CircleExpandAdapter(MyCircleActivity.this);
        View view = getLayoutInflater().inflate(R.layout.head_search, null);
        info_edit = (ClearEditText) view.findViewById(R.id.info_edit);
        circleList.addHeaderView(view);
        
        List<CircleExpandAdapter.TreeNode> treeNode = circleAdapter.GetTreeNode();  
        for (int i = 0; i < groups.length; i++)  
        {  
            CircleExpandAdapter.TreeNode node = new CircleExpandAdapter.TreeNode();  
            node.parent = groups[i];
            for (int j = 0; j < child[i].length; j++)  
            {  
                node.childs.add(child[i][j]);  
            }  
            treeNode.add(node);  
        }
        circleAdapter.UpdateTreeNode(treeNode);
        circleList.setAdapter(circleAdapter);
        circleList.expandGroup(0);
        for (int i = 0; i < groups.length; i++) 
        {
            circleList.expandGroup(i);
        };
    }
    
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
        case R.id.mime_back:
            finish();
            break;
        
        default:
            break;
        }
    }
}
