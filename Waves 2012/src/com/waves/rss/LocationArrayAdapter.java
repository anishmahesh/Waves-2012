package com.waves.rss;

import java.util.List;

import com.waves.rss.RSSArrayAdapter.ViewHolder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LocationArrayAdapter extends ArrayAdapter<String> {
	
	public Activity context;
	List<String> list1;
	List<String> list2;
	List<String> list3;
	
	public LocationArrayAdapter(Activity context, List<String> list1, List<String> list2, List<String> list3 ){
		super(context, R.layout.map_rowitem, list1);
		this.context=context;
		this.list1=list1;
		this.list2=list2;
		this.list3=list3;
	}
	
	static class ViewHolder {
		protected TextView tvevent;
		protected TextView tvvenue;
		protected TextView tvtime;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View view = null;
		if (convertView == null) {
			//create the view as it is null
			//inflate the xml layout
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.map_rowitem, null);
			final ViewHolder viewHolder = new ViewHolder();
			
			viewHolder.tvevent = (TextView) view.findViewById(R.id.tvEvent);
			viewHolder.tvvenue = (TextView) view.findViewById(R.id.tvVenue);
			viewHolder.tvtime = (TextView) view.findViewById(R.id.tvTime);
		    
            view.setTag(viewHolder);
			
		} else {
			view = convertView;
			
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.tvevent.setText(list1.get(position));
		holder.tvvenue.setText(list2.get(position));
		holder.tvtime.setText(list3.get(position));
		
		return view;
	}

}
