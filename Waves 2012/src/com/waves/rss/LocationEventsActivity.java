package com.waves.rss;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LocationEventsActivity extends Activity {

	TextView tvEvents, tvVenue, tvTime, tvTitle;
	ListView lv;
	private final String TAG = "Location Events Activity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.today_event);
		//Toast.makeText(this, "Please wait while map loads", Toast.LENGTH_LONG).show();
		//setContentView(R.layout.locationevents);
		
		lv = (ListView) findViewById(R.id.list_today_event);
		tvEvents =(TextView)findViewById(R.id.tvTitleForTodaysEvents);
		tvVenue =(TextView)findViewById(R.id.tvTitleForTodaysVenue);
		tvTime =(TextView)findViewById(R.id.tvTitleForTodaysTime);
		tvTitle =(TextView)findViewById(R.id.tvTitle_Todays_Events);
		
		Bundle locationBundle = getIntent().getExtras();
		String location = locationBundle.getString("location");
		Log.d(TAG, "After Getting extras");
		Bundle result = new JSONClass(this).getLocationData(location);
		ArrayList<String> event = result.getStringArrayList("eventlist");
		ArrayList<String> venue = result.getStringArrayList("venuelist");
		ArrayList<String> time = result.getStringArrayList("timelist");
		//Log.d(TAG,event.get(0));
		//Log.d(TAG,venue.get(0));
		//Log.d(TAG,time.get(0));
		Log.d(TAG, "After getting locations for events");
		if(event.isEmpty() && venue.isEmpty() && time.isEmpty()){
			event = new ArrayList<String>();
			venue = new ArrayList<String>();
			time = new ArrayList<String>();
			event.add("No events");
			venue.add("scheduled");
			time.add("for today");
			
		}
		
		ArrayAdapter<String> adapter = new LocationArrayAdapter(this, event, venue, time);
		lv.setAdapter(adapter);
		
		//setListAdapter(adapter);
		
//		tvevents = (TextView)findViewById(R.id.tvlocationevents);
//		tvevents.setText(result);
	}
}
