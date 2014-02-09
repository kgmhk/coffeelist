package com.example.coffelist.adapter;

import java.util.ArrayList;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffelist.R;

public class BaseExpandableAdapter extends BaseExpandableListAdapter{
	
	private ArrayList<String> groupList = null;
	private ArrayList<ArrayList<String>> childList = null;
	private LayoutInflater inflater = null;
	private ViewHolder viewHolder = null;
	
	
	public BaseExpandableAdapter(Context c, ArrayList<String> groupList, 
			ArrayList<ArrayList<String>> childList){
		super();
		this.inflater = LayoutInflater.from(c);
		this.groupList = groupList;
		this.childList = childList;
	}
	
	// 그룹 ?��??�을 반환?�다.
	@Override
	public String getGroup(int groupPosition) {
		return groupList.get(groupPosition);
	}

	// 그룹 ?�이즈�? 반환?�다.
	@Override
	public int getGroupCount() {
		return groupList.size();
	}

	// 그룹 ID�?반환?�다.
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	// 그룹�?각각??ROW 
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
	
		View v = convertView;
		
		if(v == null){
			viewHolder = new ViewHolder();
			v = inflater.inflate(R.layout.list_parent, parent, false);
			viewHolder.tv_groupName = (TextView) v.findViewById(R.id.tv_group);
			viewHolder.iv_image = (ImageView) v.findViewById(R.id.iv_image);
			
			v.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)v.getTag();
		}
		
		// 그룹???�칠?��? ?�을???�이콘을 �?��??�?��.
			if(isExpanded){
				//Drawable drawable = v.getResources().getDrawable(R.id.starbucksicon);
				viewHolder.iv_image.setImageResource(R.drawable.ic_action_expand);
			}else{
				viewHolder.iv_image.setImageResource(R.drawable.ic_action_next_item);
			}
		
			viewHolder.tv_groupName.setText(getGroup(groupPosition));
			//viewHolder.tv_groupName.setBackgroundColor(Color.CYAN);		

		return v;
	}
	
	// 차일?�뷰�?반환?�다.
	@Override
	public String getChild(int groupPosition, int childPosition) {
		return childList.get(groupPosition).get(childPosition);
	}
	
	// 차일?�뷰 ?�이즈�? 반환?�다.
	@Override
	public int getChildrenCount(int groupPosition) {
		return childList.get(groupPosition).size();
	}

	// 차일?�뷰 ID�?반환?�다.
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	// 차일?�뷰 각각??ROW
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		View v = convertView;
		
		if(v == null){
			viewHolder = new ViewHolder();
			v = inflater.inflate(R.layout.list_child, null);
			viewHolder.tv_childName = (TextView) v.findViewById(R.id.tv_child);
			v.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)v.getTag();
		}
		
		viewHolder.tv_childName.setText(getChild(groupPosition, childPosition));
		
		return v;
	}

	@Override
	public boolean hasStableIds() {	return true; }

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) { return true; }
	
	class ViewHolder {
		public ImageView iv_image;
		public TextView tv_groupName;
		public TextView tv_childName;
		
		
	}

}




