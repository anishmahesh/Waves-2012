package com.waves.rss;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RSSArrayAdapter extends ArrayAdapter<RSSDescription> {
	
	private final List<RSSDescription> list;
	private final Activity context;
	TextView tv;
	int layoutid;
	int textviewid;

	public RSSArrayAdapter(Activity context, List<RSSDescription> list, int layoutid,int textviewid) {
		super(context, layoutid, list);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		this.layoutid=layoutid;
		this.textviewid=textviewid;
	}
	static class ViewHolder {
		protected TextView text;
		
	}
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			//create the view as it is null
			
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(layoutid, null);
			final ViewHolder viewHolder = new ViewHolder();
			
			viewHolder.text = (TextView) view.findViewById(textviewid);
			
			//Register a callback to be invoked when the checked state of this button changes.
			view.setTag(viewHolder);
			
		} else {
			view = convertView;
			
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text.setText(list.get(position).getDescription());
		return view;
	}
		

}
