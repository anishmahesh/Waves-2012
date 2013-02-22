package com.waves.rss;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EventDescriptionActivity extends Activity implements OnClickListener {

	private final String TAG = "Events description Activity";
	JSONObject json;
	JSONObject category; 	
	JSONArray eventsArray;
	JSONArray categoriesArray;
	int categoryindex;
	int eventindex;
	String data;
	JSONObject event;
	JSONClass jsonClass;
	TextView tvdescription;
	TextView tveventheading;
	Button calender,maps,calenderFinals;
	StyleSpan bss,bss1,bss2,bss3,bss4,bss5,bss6,bss7,bss8,bss9,bss10;
	long startMillis,endMillis;
	String eventName, eventDescription, eventElimsDay, eventElimsVenue, eventElimsTime, eventFinalsDay, eventFinalsVenue, eventFinalsTime,minutes,hrtype; 
	int elimsDay, elimsMonth, elimsYear, finalsDay, finalsMonth, finalsYear,hr,min;
	SpannableStringBuilder sbDescription;
	SpannableStringBuilder sbElimsTime;
	String[] date;
	String[] time;
	 SpannableStringBuilder sbEndTime;
	 SpannableStringBuilder sbElimsVenue;
	SpannableStringBuilder sbContacts;
	SpannableStringBuilder sbElimsDate;
	SpannableStringBuilder sbFinalsTime;
	SpannableStringBuilder sbFinalsDate;
	SpannableStringBuilder sbFinalsVenue;
	SpannableStringBuilder sbDate;
	SpannableStringBuilder sbTime;
	SpannableStringBuilder sbVenue;
	long eventId;
	String eventTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventdescription);
		Bundle bundle = getIntent().getExtras();
		calender = (Button) findViewById(R.id.bCalenderElims);
		calenderFinals = (Button) findViewById(R.id.bCalenderFinals);
		maps = (Button) findViewById(R.id.bMaps);
		tvdescription = (TextView)findViewById(R.id.tveventdescription);
		tveventheading = (TextView)findViewById(R.id.tveventheading);
		categoryindex = bundle.getInt("categoryindex");
		eventindex = bundle.getInt("eventindex");
		bss = new StyleSpan(android.graphics.Typeface.BOLD);
		bss1 = new StyleSpan(android.graphics.Typeface.BOLD);
		bss2 = new StyleSpan(android.graphics.Typeface.BOLD);
		bss3 = new StyleSpan(android.graphics.Typeface.BOLD);
		bss4 = new StyleSpan(android.graphics.Typeface.BOLD);
		bss5 = new StyleSpan(android.graphics.Typeface.BOLD);
		bss6 = new StyleSpan(android.graphics.Typeface.BOLD);
		bss7 = new StyleSpan(android.graphics.Typeface.BOLD);
		
		bss8 = new StyleSpan(android.graphics.Typeface.BOLD);
		bss9 = new StyleSpan(android.graphics.Typeface.BOLD);
		bss10 = new StyleSpan(android.graphics.Typeface.BOLD);
		
		date = new String[3];
		time = new String[2];
		calender.setOnClickListener(this);
		maps.setOnClickListener(this);
		calenderFinals.setOnClickListener(this);
		try {
			
    		jsonClass = new JSONClass(this);
    		data = jsonClass.getJSONFileData();
    		Log.d(TAG , "Inside the try and set data entity utils");
    		categoriesArray = new JSONArray(data); 		    		
    		category = categoriesArray.getJSONObject(categoryindex);
    		eventsArray = category.getJSONArray("events");        	
        	Log.d(TAG , "Inside the try and after setting events array");
    		event = eventsArray.getJSONObject(eventindex);
        	
    	}  catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			eventName=event.getString("event_name");
			eventDescription=event.getString("event_description");
			eventElimsDay=event.getString("day");
			eventElimsVenue=event.getString("venue");
			eventElimsTime=event.getString("start_time");
			eventFinalsDay=event.getString("final_date");
			eventFinalsVenue=event.getString("final_date");
			eventFinalsTime=event.getString("end_time");
			
			tveventheading.setText(eventName);
			sbDescription = new SpannableStringBuilder(eventDescription+"\n\n");
			sbDate = new SpannableStringBuilder("Date: "+ eventElimsDay+"\n\n");
			sbTime = new SpannableStringBuilder("Time: "+ eventElimsTime+"\n\n");
			sbVenue = new SpannableStringBuilder("Venue: "+ eventElimsVenue+"\n\n");
			
			sbElimsDate = new SpannableStringBuilder("Date (Eliminations): "+ eventElimsDay+"\n\n");
			sbElimsTime = new SpannableStringBuilder("Time (Elimimations): "+ eventElimsTime+"\n\n");
			// = new SpannableStringBuilder("End Time: "+event.getString("end_time")+"\n\n");
			sbElimsVenue = new SpannableStringBuilder("Venue (Eliminations): "+eventElimsVenue +"\n\n");
			sbFinalsDate = new SpannableStringBuilder("Date (Finals): "+eventFinalsDay +"\n\n");
			sbFinalsVenue = new SpannableStringBuilder("Venue (Finals): "+eventFinalsVenue +"\n\n");
			sbFinalsTime = new SpannableStringBuilder("Time (Finals): "+eventFinalsTime +"\n\n");
			sbContacts = new SpannableStringBuilder("Contacts: \n"+ event.getString("Contacts"));
			sbElimsTime.setSpan(bss, 0, 20, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			//sbEndTime.setSpan(bss1, 0, 9, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			sbElimsVenue.setSpan(bss2, 0, 21, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			sbContacts.setSpan(bss3, 0, 9, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			sbElimsDate.setSpan(bss4, 0, 20, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			sbFinalsDate.setSpan(bss5, 0, 14, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			sbFinalsTime.setSpan(bss6, 0, 15, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			sbFinalsVenue.setSpan(bss7, 0, 14, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			
			sbDate.setSpan(bss8, 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			sbTime.setSpan(bss9, 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			sbVenue.setSpan(bss10, 0, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			
			if(event.getString("final_date").contains("None")){
				tvdescription.setText(TextUtils.concat(sbDescription,sbDate,sbTime,sbVenue,sbContacts));
				Linkify.addLinks(tvdescription, Linkify.PHONE_NUMBERS);
				calenderFinals.setClickable(false);
				calender.setText("Event");
			}
			else{
				tvdescription.setText(TextUtils.concat(sbDescription,sbElimsDate,sbElimsTime,sbElimsVenue,sbFinalsDate,sbFinalsTime,sbFinalsVenue,sbContacts));
				Linkify.addLinks(tvdescription, Linkify.PHONE_NUMBERS);
			}
			//tvdescription.set
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.options2, menu);
	        return true;
	    }
	 
	    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		ContentResolver cr = this.getContentResolver();
		Uri eventUri = Uri.parse("content://com.android.calendar/events");
		Cursor c = cr.query(eventUri, new String[]{"title"}, "title=?",new String[]{eventName}, null);
		try {
			c.moveToFirst();
			//eventId = c.getLong(0);
			eventTitle=c.getString(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			eventTitle=null;
		}
		if(eventTitle==null){
			Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this, eventTitle+"", Toast.LENGTH_SHORT).show();
		}
		
		
		
	}
		@Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
	        switch (item.getItemId()) {
	       
	            
	        case R.id.AboutUs:
	        	Intent i =  new Intent(EventDescriptionActivity.this, ContactInformation.class);
	        	startActivity(i);
	        	
	        	
	        	return true;
	        	
	        case R.id.Map:
	        	Intent i1 = new Intent(EventDescriptionActivity.this, CampusMap.class);
	        	startActivity(i1);
	        
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()){
			
			case R.id.bCalenderElims:
				startCalenderElims();			
				
				break;
				
			case R.id.bCalenderFinals:
				startCalenderFinals();
				break;
				
			case R.id.bMaps:
				break;
			
			}
			
			
		}
		private void startCalenderFinals() {
			
			date = eventFinalsDay.split("/", 3);
			finalsDay = Integer.parseInt(date[0]);
			finalsMonth = Integer.parseInt(date[1]);
			finalsYear = Integer.parseInt(date[2]);
			Log.d(TAG,finalsDay+" "+ finalsMonth+" "+finalsYear);
			
			time = eventFinalsTime.split(":", 2);
			hr = Integer.parseInt(time[0]);
			minutes = time[1].substring(0, 2);
			hrtype = time[1].substring(2);
			hrtype=hrtype.trim();
			if(hrtype.contains("pm"))
			{
				hr+=12;
			}
			min = Integer.parseInt(minutes);
			
	
			 startMillis = 0; 
			 endMillis = 0;     
			Calendar beginTime = Calendar.getInstance();
			beginTime.set(finalsYear, finalsMonth, finalsDay, hr, min);
			startMillis = beginTime.getTimeInMillis();
			Calendar endTime = Calendar.getInstance();
			endTime.set(finalsYear, finalsMonth, finalsDay, hr, min);
			endMillis = endTime.getTimeInMillis();
			

			
			Calendar cal = Calendar.getInstance();              
			Intent intent = new Intent(Intent.ACTION_EDIT);
			intent.setType("vnd.android.cursor.item/event");
			intent.putExtra("beginTime", startMillis);
			intent.putExtra("description", eventDescription);
			intent.putExtra("hasAlarm", true);
			intent.putExtra("endTime", endMillis);
			intent.putExtra("title", eventName);
			intent.putExtra("eventLocation", eventFinalsVenue);
			startActivity(intent);
			// TODO Auto-generated method stub
			
		}
		private void startCalenderElims() {
			
			
			
			date = eventElimsDay.split("/", 3);
			elimsDay = Integer.parseInt(date[0]);
			elimsMonth = Integer.parseInt(date[1]);
			elimsYear = Integer.parseInt(date[2]);
			Log.d(TAG,elimsDay+" "+ elimsMonth+" "+elimsYear);
			
			time = eventElimsTime.split(":", 2);
			hr = Integer.parseInt(time[0]);
			minutes = time[1].substring(0, 2);
			hrtype = time[1].substring(2);
			hrtype=hrtype.trim();
			if(hrtype.contains("pm"))
			{
				hr+=12;
			}
			min = Integer.parseInt(minutes);
			
	
			 startMillis = 0; 
			 endMillis = 0;     
			Calendar beginTime = Calendar.getInstance();
			beginTime.set(elimsYear, elimsMonth, elimsDay, hr, min);
			startMillis = beginTime.getTimeInMillis();
			Calendar endTime = Calendar.getInstance();
			endTime.set(elimsYear, elimsMonth, elimsDay, hr, min);
			endMillis = endTime.getTimeInMillis();
			

			
			Calendar cal = Calendar.getInstance();              
			Intent intent = new Intent(Intent.ACTION_EDIT);
			intent.setType("vnd.android.cursor.item/event");
			intent.putExtra("beginTime", startMillis);
			intent.putExtra("description", eventDescription);
			intent.putExtra("hasAlarm", true);
			intent.putExtra("endTime", endMillis);
			intent.putExtra("title", eventName);
			intent.putExtra("eventLocation", eventElimsVenue);
			int requestCode = 1;
			startActivityForResult(intent, requestCode);
			
			
			
			//Calendar endTime = Calendar.getInstance();
			//endTime.set(2012, 0, 19, 8, 30);
			// TODO Auto-generated method stub
			
		}
		
}
