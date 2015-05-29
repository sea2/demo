package com.smt.util.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.smt.chashizaixian.MyCircleActivity;
import com.smt.chashizaixian.R;
import com.smt.config.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CircleExpandAdapter extends BaseExpandableListAdapter implements
        OnItemClickListener
{
    private List<TreeNode> treeNodes = new ArrayList<TreeNode>();
    private Activity parentContext;
    private LayoutInflater layoutInflater;
    static public class TreeNode
    {
        public Object parent;//组名
        public List<Object> childs = new ArrayList<Object>();//该组的item数据List
    }

    class ViewHolder 
    {
        public ImageView info_head;
        public TextView group_name;
        public TextView count_tv;
        public TextView place_tv;
        public TextView info_tv;
    }
    
    public CircleExpandAdapter(Activity activity)
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
        return "哈哈啊哈哈哈";
    }

    public int getChildrenCount(int groupPosition)
    {
//        return 3;
        return treeNodes.get(groupPosition).childs.size();
    }

    /**
     * 可自定义ExpandableListView
     */
    public View getChildView(int groupPosition, int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            layoutInflater = (LayoutInflater) parentContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_circle_group_big, null);
            holder.info_head = (ImageView) convertView.findViewById(R.id.info_head);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        if (parentContext instanceof MyCircleActivity)
        {
            holder.info_head.setImageResource(R.drawable.v1_7_ic_contact_my_group);
        }
        else
        {
            holder.info_head.setImageResource(R.drawable.ic_discover_nearby_group);
        }
        return convertView;
    }

    /**
     * 可自定义list
     */
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent)
    {
        RelativeLayout groupLayout = (RelativeLayout) parentContext.getLayoutInflater().inflate(R.layout.item_nearby_title, null);
        TextView group_area = (TextView) groupLayout.findViewById(R.id.group_area);
        group_area.setText((CharSequence) treeNodes.get(groupPosition).parent);
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