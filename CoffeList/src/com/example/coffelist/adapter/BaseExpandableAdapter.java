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
	
	// ê·¸ë£¹ ?¬ì??˜ì„ ë°˜í™˜?œë‹¤.
	@Override
	public String getGroup(int groupPosition) {
		return groupList.get(groupPosition);
	}

	// ê·¸ë£¹ ?¬ì´ì¦ˆë? ë°˜í™˜?œë‹¤.
	@Override
	public int getGroupCount() {
		return groupList.size();
	}

	// ê·¸ë£¹ IDë¥?ë°˜í™˜?œë‹¤.
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	// ê·¸ë£¹ë·?ê°ê°??ROW 
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
		
		// ê·¸ë£¹???¼ì¹ ?Œì? ?«ì„???„ì´ì½˜ì„ ë³?²½??ì¤?‹¤.
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
	
	// ì°¨ì¼?œë·°ë¥?ë°˜í™˜?œë‹¤.
	@Override
	public String getChild(int groupPosition, int childPosition) {
		return childList.get(groupPosition).get(childPosition);
	}
	
	// ì°¨ì¼?œë·° ?¬ì´ì¦ˆë? ë°˜í™˜?œë‹¤.
	@Override
	public int getChildrenCount(int groupPosition) {
		return childList.get(groupPosition).size();
	}

	// ì°¨ì¼?œë·° IDë¥?ë°˜í™˜?œë‹¤.
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	// ì°¨ì¼?œë·° ê°ê°??ROW
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




