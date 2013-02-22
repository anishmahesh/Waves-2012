package com.waves.rss;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class JSONClass {

	private final String TAG = "JSON Class";
	List<RSSDescription> categorieslist;
	
	ArrayAdapter<RSSDescription> ap;
	JSONObject json;		
	JSONArray categoriesArray;
	
	Context context;
	
	public JSONClass(Context context){
		this.context=context;
	}
	
	
	public List<RSSDescription> getCategoriesList( ){
		
		
		
		String content = getJSONFileData();
		categorieslist = new ArrayList<RSSDescription>();
        if (content.equalsIgnoreCase("")){
        	return categorieslist;
        }
        else{
    	try {
    		
    		categoriesArray = new JSONArray(content);		
    		
    		
    	}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	int i = 0;
    	String buffer = "";
    	do{
			try {
				json = categoriesArray.getJSONObject(i);
				buffer = json.getString("category");
				categorieslist.add(new RSSDescription(buffer));
				Log.d(TAG, categorieslist.get(i).getDescription());
				i++;
			} catch (JSONException e) {
				json = null;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.d( TAG ,"The string returned by json is "+ buffer);
		}while (json!= null);
        
        
    	return categorieslist;
        }
	}
	public String getJSONFileData(){
		String content="";
		FileInputStream fis;
	    
	    try {
	    	 
	     fis = context.openFileInput("JSON.txt");
	     byte[] input = new byte[fis.available()];
	     while (fis.read(input) != -1) {
	     content += new String(input);}
	    } catch (FileNotFoundException e) {
	     e.printStackTrace();
	    } catch (IOException e) {
	     e.printStackTrace(); 
	    }
	    return content;
	

	}
	@SuppressWarnings("null")
	public Bundle getLocationData(String location){
		
		String JSONFile = getJSONFileData();
		ArrayList<String> events = new ArrayList<String>(),venues = new ArrayList<String>(),times = new ArrayList<String>();
		Log.d(TAG, "in getlocationdata after getting data from file");
		//SimpleDateFormat format  = new SimpleDateFormat("HH:mm");
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = df.format(c.getTime());
		String locationtemp = "";
		try {
			categoriesArray = new JSONArray(JSONFile);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d(TAG, "in getlocationdata after Making a JSON Array from the file");
		//String result = "";
		JSONObject category;
		JSONArray eventsarray;
		JSONObject event;
		int i = 0 , j = 0;
		
		do{
			j = 0 ;
			Log.d(TAG, "in getlocationdata doing iteration no "+i);
			try {
				category = categoriesArray.getJSONObject(i);
				eventsarray = category.getJSONArray("events");
				i++;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				category = null;
				continue;
			}
			
			do{
				try {
					event = eventsarray.getJSONObject(j);
					if(event.getString("end_time").equalsIgnoreCase("None")){
						locationtemp = event.getString("venue");
						if( locationtemp.equalsIgnoreCase(location) || location.regionMatches(true , 0, locationtemp, 0, 4)  ){
							Log.d(TAG, "inside event map IF(before date)");
						//	if( )  write date comparison logic here.
							if( formattedDate.equalsIgnoreCase(event.getString("day"))){
								Log.d(TAG, "inside event map IF");
								//result += event.getString("event_name") + "\n" + event.getString("venue") +"\n" + event.getString("start_time") + "\n" +formattedDate + "\n\n";
								events.add(event.getString("event_name"));
								venues.add(event.getString("venue"));
								times.add(event.getString("start_time"));
							}
							
						}
					}
					else{
						locationtemp = event.getString("venue");
						if( locationtemp.equalsIgnoreCase(location) || location.regionMatches(true , 0, locationtemp, 0, 4)  ){
						//	if( )  write date comparison logic here.
							if( formattedDate.equalsIgnoreCase(event.getString("day"))){
								Log.d(TAG, "inside event map IF");
								//result += event.getString("event_name") + "\n" + event.getString("venue") +"\n" + event.getString("start_time") + "\n" +formattedDate + "\n\n";
								events.add(event.getString("event_name")+"\nElims");
								venues.add(event.getString("venue"));
								times.add(event.getString("start_time"));
							}
							
						}
						locationtemp = event.getString("final_venue");
						if( locationtemp.equalsIgnoreCase(location) || location.regionMatches(true , 0, locationtemp, 0, 4)  ){
						//	if( )  write date comparison logic here.
							if( formattedDate.equalsIgnoreCase(event.getString("final_date"))){
								Log.d(TAG, "inside event map IF");
								//result += event.getString("event_name") + "\n" + event.getString("venue") +"\n" + event.getString("start_time") + "\n" +formattedDate + "\n\n";
								events.add(event.getString("event_name")+"\nFinals");
								venues.add(event.getString("final_venue"));
								times.add(event.getString("end_time"));
							}
							
						}
					}
					j++;
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					event = null;
					continue;
				}
				
			}while(event != null);
		}while(category!=null);
		
		Bundle resultbundle = new Bundle();
		resultbundle.putStringArrayList("eventlist", events);
		resultbundle.putStringArrayList("venuelist", venues);
		resultbundle.putStringArrayList("timelist", times);
		
		return resultbundle;
	}


}
