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


public class EventsListGridAdapter  extends BaseAdapter {
	
		private String TAG = "EventsListGridAdapter";
	
		private final List<RSSDescription> list;
		private final Activity context;
		TextView tv;
		int layoutid;
		int textviewid;
		private LayoutInflater  mInflater;
		int index;
		int[][] images;
		
		

		public EventsListGridAdapter(Activity context, List<RSSDescription> list, int index) {
			Log.d(TAG,"Constructor called");
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			// TODO Auto-generated constructor stub
			this.context = context;
			this.list = list;
			this.index=index;
			images = new int[8][8];
			initialize();
			//teventshis.layoutid=layoutid;
			//this.textviewid=textviewid;
		}
		private void initialize() {
			images[0][0]=R.drawable.natyanjali;
			images[0][1]=R.drawable.vogue;
			images[0][2]=R.drawable.mr_miss_waves;
			images[0][3]=0;
			images[0][4]=0;
			images[0][5]=0;
			images[0][6]=0;
			images[0][7]=0;
			
			images[1][0]=R.drawable.dhinchak;
			images[1][1]=R.drawable.step_up;
			images[1][2]=R.drawable.sizzle;
			images[1][3]=0;
			images[1][4]=0;
			images[1][5]=0;
			images[1][6]=0;
			images[1][7]=0;
			
			images[2][0]=R.drawable.silence_of_the_amps;
			images[2][1]=R.drawable.rap_battle;
			images[2][2]=R.drawable.indian_rock;
			images[2][3]=R.drawable.instrumental_solo;
			images[2][4]=R.drawable.solo_singing_western;
			images[2][5]=R.drawable.six_string_showdown;
			images[2][6]=R.drawable.alaap_classic_solo;
			images[2][7]=0;
			
			images[3][0]=R.drawable.portrature;
			images[3][1]=R.drawable.cartooning;
			images[3][2]=R.drawable.street_art;
			images[3][3]=R.drawable.art_marathon;
			images[3][4]=R.drawable.blind_art;
			images[3][5]=R.drawable.misc;
			images[3][6]=R.drawable.mezzotint;
			images[3][7]=0;
			
			images[4][0]=R.drawable.waves_debate;
			images[4][1]=R.drawable.jam;
			images[4][2]=R.drawable.word_games;
			images[4][3]=R.drawable.words_o_worth;
			images[4][4]=R.drawable.manthan_trividha;
			images[4][5]=R.drawable.press_corps;
			images[4][6]=0;
			images[4][7]=0;
			
			images[5][0]=R.drawable.sports_and_entertainment_quiz;
			images[5][1]=R.drawable.vices_quiz;
			images[5][2]=R.drawable.food_quiz;
			images[5][3]=R.drawable.waves_open_quiz;
			images[5][4]=R.drawable.green_quiz;
			images[5][5]=0;
			images[5][6]=0;
			images[5][7]=0;
			
			images[6][0]=R.drawable.rang_manch_stage_play;
			images[6][1]=R.drawable.nukad_nattak;
			images[6][2]=R.drawable.mock_talk_show;
			images[6][3]=R.drawable.drama;
			images[6][4]=R.drawable.skime;
			images[6][5]=0;
			images[6][6]=0;
			images[6][7]=0;
			
			images[7][0]=R.drawable.treasure_hunt;
			images[7][1]=R.drawable.film_making;
			images[7][2]=R.drawable.wall_street;
			images[7][3]=0;
			images[7][4]=0;
			images[7][5]=0;
			images[7][6]=0;
			images[7][7]=0;
			
			
			
			
			
			
			// TODO Auto-generated method stub
			
		}
		static class ViewHolder {
			protected TextView text;
			protected ImageButton ib;
			
		}
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = null;
			if (convertView == null) {
				//create the view as it is null
				Log.d(TAG,"getView");
				//LayoutInflater inflator = context.getLayoutInflater();
				view = mInflater.inflate(R.layout.eventsitem, null);
				final ViewHolder viewHolder = new ViewHolder();
				
				viewHolder.text = (TextView) view.findViewById(R.id.grid_labelEvents);
				viewHolder.ib= (ImageButton) view.findViewById(R.id.grid_imageEvents);
				viewHolder.ib.setOnClickListener(new OnClickListener() {
	                public void onClick(View v) {
	                	
	                	//Toast.makeText(context, position+"", Toast.LENGTH_LONG).show();
	                	Intent eventsIntent = new Intent( context , EventDescriptionActivity.class );
	                	Bundle eventsBundle = new Bundle();
	                	eventsBundle.putInt("categoryindex" , index);
	                	eventsBundle.putInt("eventindex", position);
	                	
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
				
			} else {
				view = convertView;
				
			}
			
			ViewHolder holder = (ViewHolder) view.getTag();
			String event=list.get(position).getDescription();
			holder.text.setText(event);
			if(images[index][position]!=0){
				holder.ib.setBackgroundResource(images[index][position]);
			}
			
			
			/*if(event.equalsIgnoreCase("music")){
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
				holder.ib.setBackgroundResource(R.drawable.misc);*/
						
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
