package com.smt.util.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smt.chashizaixian.R;
import com.smt.util.t.MyGridView;

public class SpecialExpandAdapter extends BaseExpandableListAdapter implements
        OnItemClickListener
{
    private MyGridView specialGrid;
    private List<TreeNode> treeNodes = new ArrayList<TreeNode>();
    private Activity parentContext;
    private LayoutInflater layoutInflater;
    static public class TreeNode
    {
        public Object parent;//组名
        public List<Object> childs = new ArrayList<Object>();//该组的item数据List
    }

    public SpecialExpandAdapter(Activity activity)
    {
        this.parentContext = activity;
    }

    public List<TreeNode> GetTreeNode()
    {
        return treeNodes;
    }

    public void UpdateTreeNode(List<TreeNode> nodes)
    {
        treeNodes = nodes;
    }

    public void RemoveAll()
    {
        treeNodes.clear();
    }

    public Object getChild(int groupPosition, int childPosition)
    {
        return treeNodes.get(groupPosition).childs.get(childPosition);
    }

    public int getChildrenCount(int groupPosition)
    {
        return treeNodes.get(groupPosition).childs.size();
    }

    /**
     * 可自定义ExpandableListView
     */
    public View getChildView(int groupPosition, int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            layoutInflater = (LayoutInflater) parentContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_specia_list, null);

            specialGrid = (MyGridView) convertView
                    .findViewById(R.id.list_item_grid);
            specialGrid.setNumColumns(3);
            specialGrid.setAdapter(new SpecialGridAdapter(parentContext));// 设置菜单Adapter
            specialGrid.setOnItemClickListener(this);
        }
        return convertView;
    }

    /**
     * 可自定义list
     */
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent)
    {
        RelativeLayout groupLayout = (RelativeLayout) parentContext.getLayoutInflater().inflate(R.layout.item_special_title, null);
        TextView specialName = (TextView) groupLayout.findViewById(R.id.textView1);
        specialName.setText((CharSequence) treeNodes.get(groupPosition).parent);
        return groupLayout;
    }

    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    public Object getGroup(int groupPosition)
    {
        return treeNodes.get(groupPosition).parent;
    }

    public int getGroupCount()
    {
        return treeNodes.size();
    }

    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id)
    {
        Toast.makeText(parentContext, "当前选中的是:" + position, Toast.LENGTH_SHORT)
                .show();
    }
}