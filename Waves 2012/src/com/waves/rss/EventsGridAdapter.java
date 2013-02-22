package com.waves.rss;



import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class EventsGridAdapter  extends BaseAdapter {
	
		private String TAG = "EventsGridAdapter";
	
		private final List<RSSDescription> list;
		private final Activity context;
		TextView tv;
		int layoutid;
		int textviewid;
		private LayoutInflater  mInflater;
		int flag;
		
		

		public EventsGridAdapter(Activity context, List<RSSDescription> list,int flag) {
			Log.d(TAG,"Constructor called");
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			// TODO Auto-generated constructor stub
			this.context = context;
			this.list = list;
			this.flag=flag;
			//this.layoutid=layoutid;
			//this.textviewid=textviewid;
		}
		static class ViewHolder {
			protected TextView text;
			protected ImageButton ib;
			
		}
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = null;
			//if (convertView == null) {
				//create the view as it is null
				Log.d(TAG,"getView");
				//LayoutInflater inflator = context.getLayoutInflater();
				view = mInflater.inflate(R.layout.categories, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.text = (TextView) view.findViewById(R.id.grid_label);
				viewHolder.ib= (ImageButton) view.findViewById(R.id.grid_image);
				viewHolder.ib.setOnClickListener(new OnClickListener() {
	                public void onClick(View v) {
	                	
	                	//Toast.makeText(context, (position)+"", Toast.LENGTH_LONG).show();
	                	Intent eventsIntent = new Intent( context , EventsActivity.class );
	                	Bundle eventsBundle = new Bundle();
	                	if (flag==0){
	                	eventsBundle.putInt("index", position);
	                	}
	                	else {
		                	eventsBundle.putInt("index", position);
		                	}
	                	eventsIntent.putExtras(eventsBundle);
	                	Log.d(TAG, "just before starting new activity");
	                	context.startActivity(eventsIntent);
	                	Log.d(TAG, "just after starting new activity");
	                	
	                	
	                   // Toast toast = new Toast(TwitterActivity.this);
	                  //  toast.setText("LongClick");
	                   // toast.show();

	                    
	                }
	            });
				
				//Register a callback to be invoked when the checked state of this button changes.
				view.setTag(viewHolder);
				
			//} else {
			//	view = convertView;
			//	
			//}
			
			ViewHolder holder = (ViewHolder) view.getTag();
			String event=list.get(position).getDescription();
			holder.text.setText(event);
			
			
			if(event.equalsIgnoreCase("music")){
				holder.ib.setBackgroundResource(R.drawable.music);
				Log.d(TAG, "inside big3 if");
				
			}
			else if(event.equalsIgnoreCase("fine arts"))
				holder.ib.setBackgroundResource(R.drawable.fine_arts);
			else if(event.equalsIgnoreCase("literary"))
				holder.ib.setBackgroundResource(R.drawable.literary);
			else if(event.equalsIgnoreCase("dramatics"))
				holder.ib.setBackgroundResource(R.drawable.drama);
			else if(event.equalsIgnoreCase("big 3"))
				holder.ib.setBackgroundResource(R.drawable.big3);
			else if(event.equalsIgnoreCase("quiz"))
				holder.ib.setBackgroundResource(R.drawable.quiz);
			else if(event.equalsIgnoreCase("dance"))
				holder.ib.setBackgroundResource(R.drawable.dance);
			else if(event.equalsIgnoreCase("miscellaneous"))
				holder.ib.setBackgroundResource(R.drawable.misc);
						
			return view;
		}
		public int getCount() {
			
			if (list != null) {
	            return list.size();
	        }
			// TODO Auto-generated method stub
			return 0;
		}
		public Object getItem(int position) {
			if (list != null && position >= 0 && position < getCount()) {
	            return list.get(position);
	        }
	        
	        return null;

			// TODO Auto-generated method stub
			
		}
		public long getItemId(int position) {
			
			if (list != null && position >= 0 && position < getCount()) {
	            return position;
	        }
			// TODO Auto-generated method stub
			return 0;
		}
		
			

	

}
