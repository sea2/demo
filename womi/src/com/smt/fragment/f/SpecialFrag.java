package com.smt.fragment.f;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.smt.chashizaixian.R;
import com.smt.util.adapter.SpecialExpandAdapter;
import com.smt.util.t.MyExpandableListView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class SpecialFrag extends Fragment implements OnClickListener
{
    private SpecialExpandAdapter specialExpandAdapter;  
    private MyExpandableListView specialGroup;
    public String[] groups = { "八马专辑区", "华祥苑专辑区", "茶市在线专辑区" };  
    public String[][] child = { { ""}, { ""}, { "", ""} };
    
    ArrayList<String> mGroupNameData = new ArrayList<String>();
    ArrayList<ArrayList<Object>> mChildrenData = new ArrayList<ArrayList<Object>>();
//    ArrayList<Map<String, ArrayList<Object>>> mChildrenData = new ArrayList<Map<String, ArrayList<Object>>>();
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.frag_special, null);
        
        specialGroup = (MyExpandableListView) layout.findViewById(R.id.special_group);
        specialGroup.setDivider(null);
        specialGroup.setGroupIndicator(null);
        specialExpandAdapter = new SpecialExpandAdapter(getActivity());  
  
        List<SpecialExpandAdapter.TreeNode> treeNode = specialExpandAdapter.GetTreeNode();  
        for (int i = 0; i < groups.length; i++)  
        {  
            SpecialExpandAdapter.TreeNode node = new SpecialExpandAdapter.TreeNode();  
            node.parent = groups[i];
            for (int ii = 0; ii < child[i].length; ii++)  
            {  
                node.childs.add(child[i][ii]);  
            }  
            treeNode.add(node);  
        }  
  
        specialExpandAdapter.UpdateTreeNode(treeNode);  
        specialGroup.setAdapter(specialExpandAdapter);  
        specialGroup.expandGroup(0);
        return layout;
    }
    
    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onClick(View v)
    {
    }
}
