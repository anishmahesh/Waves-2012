package com.waves.rss;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;


public class EventsActivity extends Activity {

	private final String TAG = "Events Activity";
	List<RSSDescription> eventslist;
	String data;
	
	
	
	JSONObject json;
	JSONObject category; 	
	JSONArray eventsArray;
	JSONArray categoriesArray;
	int index;
	JSONObject event;
	JSONClass jsonClass;
	String cat;
	TextView tv;
	GridView gv;
	EventsListGridAdapter evg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d(TAG , "Just before Setcontentview");
		setContentView(R.layout.eventsnew);
		
		tv =(TextView) findViewById(R.id.tvEvents);
		gv = (GridView) findViewById(R.id.grid_viewEvents);
		
		
     	eventslist=new ArrayList<RSSDescription>();     	
    	Bundle bundle = getIntent().getExtras();
    	
    	index = bundle.getInt("index");
     	try {
    		
    			
    		jsonClass = new JSONClass(this);
    		data = jsonClass.getJSONFileData();
    		Log.d(TAG , "Inside the try and set data entity utils");
    		categoriesArray = new JSONArray(data); 		  
    		
    		category = categoriesArray.getJSONObject(index);
    		cat = category.getString("category");
    		
    		
    		eventsArray = category.getJSONArray("events");        	
        	Log.d(TAG , "Inside the try and after setting events array");
    		
        	
    	}  catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	int i = 0 ;
//		String buffer = "";
		do{
			try {
				json = eventsArray.getJSONObject(i);
				eventslist.add(new RSSDescription(json.getString("event_name")));
				Log.d(TAG , "Inside do while and json string returned is "+ json.getString("event_name"));
	    		
				i++;
			} catch (JSONException e) {
				json = null;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Log.d( TAG ,"The string returned by json is "+buffer );
		}while (json!= null);
		tv.setText(cat);
		evg = new EventsListGridAdapter(EventsActivity.this, eventslist,index);
		gv.setAdapter(evg);
    	
		//new Read().execute("event_name");
	}
	
		/*public class Read extends AsyncTask<String, Integer, List<RSSDescription> >{

		@Override
		protected List<RSSDescription> doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			int i = 0 ;
//			String buffer = "";
			do{
				try {
					json = eventsArray.getJSONObject(i);
					eventslist.add(new RSSDescription(json.getString(params[0])));
					Log.d(TAG , "Inside do while and json string returned is "+ json.getString(params[0]));
		    		
					i++;
				} catch (JSONException e) {
					json = null;
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				Log.d( TAG ,"The string returned by json is "+buffer );
			}while (json!= null);
			
		return eventslist;
		}
		protected void onPostExecute(List<RSSDescription> result) {
			// TODO Auto-generated method stub
//			httpStuff.setText(result);
			//Log.d(TAG , "The value of tvid is "+ tvid);
			tv.setText(cat);
			evg = new EventsListGridAdapter(EventsActivity.this, result,index);
			gv.setAdapter(evg);

		}

 	}*/
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.options2, menu);
	        return true;
	    }
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
	        switch (item.getItemId()) {
	       
	            
	        case R.id.AboutUs:
	        	Intent i =  new Intent(EventsActivity.this, ContactInformation.class);
	        	startActivity(i);
	        	
	        	
	        	return true;
	        	
	        case R.id.Map:
	        	Intent i1 = new Intent(EventsActivity.this, CampusMap.class);
	        	startActivity(i1);
	        
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
}