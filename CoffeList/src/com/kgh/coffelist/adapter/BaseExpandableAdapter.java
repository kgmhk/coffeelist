package com.kgh.coffelist.adapter;

import java.util.ArrayList;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kgh.coffelist.R;

public class BaseExpandableAdapter extends BaseExpandableListAdapter{
	//??? ?????	
	private Typeface tfsmall;
	private Typeface tfbold;
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
		
		tfsmall = Typeface.createFromAsset(c.getAssets(),"fontbold.ttf");
		tfbold = Typeface.createFromAsset(c.getAssets(), "fontbold.ttf");
	}
	
	// 洹�?�?????�� �?���?��.
	@Override
	public String getGroup(int groupPosition) {
		return groupList.get(groupPosition);
	}

	// 洹�?�???��?�? �?���?��.
	@Override
	public int getGroupCount() {
		return groupList.size();
	}

	// 洹�?�?ID??�?���?��.
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	// 洹�?�???�?���??ROW 
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
	
		View v = convertView;
	
		if(v == null){
			viewHolder = new ViewHolder();
			v = inflater.inflate(R.layout.list_parent, parent, false);
			viewHolder.tv_groupName = (TextView) v.findViewById(R.id.tv_group);
			//?��? ???
			viewHolder.tv_groupName.setTypeface(tfbold);
			//myNewFace = Typeface.createFromAsset(getAssets(), "fonts/");
			viewHolder.iv_image = (ImageView) v.findViewById(R.id.iv_image);
			
			v.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)v.getTag();
		}
		
		// 洹�?�????��?�? ??��???��??���?�?�??�?�?
			if(isExpanded){
				//Drawable drawable = v.getResources().getDrawable(R.id.starbucksicon);
				viewHolder.iv_image.setImageResource(R.drawable.ic_action_expand);
			}else{
				viewHolder.iv_image.setImageResource(R.drawable.coffe);
			}
		
			viewHolder.tv_groupName.setText(getGroup(groupPosition));
			//viewHolder.tv_groupName.setBackgroundColor(R.drawable.parent_background);		

		return v;
	}
	
	// 李�?�?��???�?���?��.
	@Override
	public String getChild(int groupPosition, int childPosition) {
		return childList.get(groupPosition).get(childPosition);
	}
	
	// 李�?�?��? ??��?�? �?���?��.
	@Override
	public int getChildrenCount(int groupPosition) {
		return childList.get(groupPosition).size();
	}

	// 李�?�?��? ID??�?���?��.
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	// 李�?�?��? �?���??ROW
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		View v = convertView;
		
		if(v == null){
			viewHolder = new ViewHolder();
			v = inflater.inflate(R.layout.list_child, null);
			viewHolder.child_image = (ImageView) v.findViewById(R.id.child_image);
			viewHolder.tv_childName = (TextView) v.findViewById(R.id.tv_child);
			viewHolder.tv_childName.setTypeface(tfsmall);
			v.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)v.getTag();
		}
		viewHolder.child_image.setImageResource(R.drawable.child_listview_image);
		viewHolder.tv_childName.setText(getChild(groupPosition, childPosition));
		
		return v;
	}

	@Override
	public boolean hasStableIds() {	return true; }

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) { return true; }
	
	class ViewHolder {
		public ImageView iv_image;
		public ImageView child_image;
		public TextView tv_groupName;
		public TextView tv_childName;
		
		
	}

}




