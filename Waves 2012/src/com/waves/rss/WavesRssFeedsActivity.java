package com.waves.rss;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.viewpager.extensions.FixedTabsView;
import com.astuetz.viewpager.extensions.TabsAdapter;





public class WavesRssFeedsActivity extends Activity   {
	
	
	private ArrayList<ExpandListGroup> ExpListItems;
	
	
	private ViewPager mPager;
	private int count;
	SharedPreferences prefs;
	ProgressDialog pd1;
	
	private FixedTabsView mFixedTabs;
	
	
	private PagerAdapter mPagerAdapter;
	
	private TabsAdapter mFixedTabsAdapter;
	private final String TAG = "WavesRssFeedsActivity";
	List<RSSDescription> updates,categories,reverse;
	String data;
	SQLiteRss info;
	JSONClass jsonClass;
	ArrayAdapter<RSSDescription> ap;
	int flag;
	Intent intent;
	SharedPreferences.Editor editor;
	//ListView lv;
	//ViewGroup vg = (ViewGroup) findViewById (R.id.);
	//List list =(list)findViewById(R.id.)

	UpdatesService s;
	
	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
	        @Override
	        public void onReceive(Context context, Intent intent) {
	        	updateUI(intent);       
	        }
	    };  
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_fixed_tabs);
        //doBindService();
       
        //Toast.makeText(this, "Click On event image to view event details", Toast.LENGTH_LONG).show();
       // Toast.makeText(this, "Click On menu button to view map and currently running events", Toast.LENGTH_LONG).show();
        reverse = new ArrayList<RSSDescription>();
        categories = new ArrayList<RSSDescription>();
        jsonClass = new JSONClass(this);
        categories= jsonClass.getCategoriesList();
        flag=0;
        
    
        
        ExpListItems = intializeMySchedule();
        
    	updates=new ArrayList<RSSDescription>();
    	info = new SQLiteRss(this);
		try {
			info.open();
			updates = info.getData();
			info.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			Toast.makeText(this , "error in access "+e, Toast.LENGTH_SHORT).show();
		}
		initViewPager(3 , updates , categories, flag, ExpListItems );
		mFixedTabs = (FixedTabsView) findViewById(R.id.fixed_tabs);
		
		mFixedTabsAdapter = new FixedTabsAdapter(this);
		mFixedTabs.setAdapter(mFixedTabsAdapter);
		mFixedTabs.setViewPager(mPager);
		
		 prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
			count=prefs.getInt("count", 0);
		if(count==0){
			 pd1 = new ProgressDialog(this);
			pd1.setMessage("Loading...");
			pd1.show();
		}
		else{
			Toast.makeText(this, "Refreshing Data", Toast.LENGTH_SHORT).show();
		}
	
        	
    }
    public ArrayList<ExpandListGroup> intializeMySchedule() {
    	ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
    	ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
        ExpandListGroup gru1 = new ExpandListGroup();
        gru1.setName("Comedy");
        ExpandListChild ch1_1 = new ExpandListChild();
        ch1_1.setName("A movie");
        ch1_1.setTag(null);
        list2.add(ch1_1);
        ExpandListChild ch1_2 = new ExpandListChild();
        ch1_2.setName("An other movie");
        ch1_2.setTag(null);
        list2.add(ch1_2);
        ExpandListChild ch1_3 = new ExpandListChild();
        ch1_3.setName("And an other movie");
        ch1_3.setTag(null);
        list2.add(ch1_3);
        gru1.setItems(list2);
        list2 = new ArrayList<ExpandListChild>();
        
        ExpandListGroup gru2 = new ExpandListGroup();
        gru2.setName("Action");
        ExpandListChild ch2_1 = new ExpandListChild();
        ch2_1.setName("A movie");
        ch2_1.setTag(null);
        list2.add(ch2_1);
        ExpandListChild ch2_2 = new ExpandListChild();
        ch2_2.setName("An other movie");
        ch2_2.setTag(null);
        list2.add(ch2_2);
        ExpandListChild ch2_3 = new ExpandListChild();
        ch2_3.setName("And an other movie");
        ch2_3.setTag(null);
        list2.add(ch2_3);
        gru2.setItems(list2);
        list.add(gru1);
        list.add(gru2);
        
        return list;
    }
	@Override
    public void onResume() {
		super.onResume();		
		//startService(i);
		registerReceiver(broadcastReceiver, new IntentFilter(UpdatesService.BROADCAST_ACTION));
	}
	
	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(broadcastReceiver);
		//stopService(i); 		
	}	
	public void updateUI(Intent intent) {
		
		Dialog d=new Dialog(this);
		TextView tv = new TextView(this);
		d.setTitle("Error");
		
		Bundle bundle = intent.getExtras();
		int interneterror = bundle.getInt("interneterror");
		int jsonerror = bundle.getInt("jsonerror");
		resetList();
		jsonClass = new JSONClass(this);
        categories= jsonClass.getCategoriesList();
		//ap.notifyDataSetChanged();
		initViewPager(3, updates, categories,flag,ExpListItems);
		if (count ==0){
			pd1.dismiss();
		}
		if(interneterror==10 && jsonerror==10){
			tv.setText("Nothing could be refreshed.\nPlease check internet connection\nand retry later");
			d.setContentView(tv);
			d.show();
		}
		else if(interneterror==10){
			tv.setText("Updates Could Not be refreshed.\nPlease check internet connection\nand retry later");
			d.setContentView(tv);
			d.show();
		}
		else if(jsonerror==10){
			tv.setText("EventsList Could Not be refreshed.\nPlease check internet connection\nand retry later");
			d.setContentView(tv);
			d.show();
			
		}
		else{
			
		}
		
			
		if (count ==0){
			pd1.dismiss();
		}
		
		
		
		count++;
		editor = prefs.edit();
		editor.putInt("count", count);
		editor.commit();
    	//Log.d(TAG, counter);
    	//Log.d(TAG, time);
    	
    	
    }
   
   
    

    public void resetList(){
    	Log.d(TAG,"Inside Resetlist");
    	info = new SQLiteRss(this);
		try {
			Log.d(TAG,"Inside Try Resetlist");	    	
			info.open();
			updates = info.getData();
			info.close();
		} catch (Exception e) {
			
		}
	
		
		

    }
  
    
    public class UpdateClass extends AsyncTask<ArrayAdapter<List>, Integer, String>{
    	int jsonerror;
    	Dialog d = new Dialog(WavesRssFeedsActivity.this);
    	ProgressDialog pd = new ProgressDialog(WavesRssFeedsActivity.this);
    	int interneterror = 0 ;
    	protected void onPreExecute() {    		
    		      super.onPreExecute();    		
    		     // Dialog d = new Dialog(WavesRssFeedsActivity.this);
    		     pd.setMessage("Downloading....");
    		      pd.show();
    		      //d.setTitle("Downloading.....");
    		      //d.show();    		
    		   }

    	
    	@Override
    	protected String doInBackground(ArrayAdapter<List>... arg0) {    		
    		// TODO Auto-generated method stub        
    		GetRssFeedData rssData = new GetRssFeedData(WavesRssFeedsActivity.this);
    		interneterror =  rssData.getData();
    		GetJSONData jsonData = new GetJSONData(WavesRssFeedsActivity.this);
    		jsonerror = jsonData.storeJSONData();    		
    		
    		publishProgress(interneterror,jsonerror);
    		
    		jsonClass = new JSONClass(WavesRssFeedsActivity.this);
            categories= jsonClass.getCategoriesList();
           
    		
    		
    		
    		Log.d(TAG,"end of do in background");
    		
    		return "All Done";
    	}
    	
    	protected void onProgressUpdate(Integer... values) {
    			super.onProgressUpdate(values);
    			pd.dismiss();
    			if(values[0]==10 || values[1]==10){
    				Log.d(TAG, "inside if interneterror == 10 ");
    				new AlertDialog.Builder(WavesRssFeedsActivity.this)
    			    .setTitle("Internet Access Not Found")
    			    .setMessage("Data could not be refreshed.")
    			    .show();

    				Log.d(TAG,"on progress update error case ended");
    	    		
    			}
    			else
    			{
    				
    				Log.d(TAG,"Reset is getting called from inside on progress update");
    				resetList();
    				Log.d(TAG,"Notify data change is getting called from on progress update");
    				flag=1;
    				//ap = new RSSArrayAdapter(WavesRssFeedsActivity.this, updates);
    				initViewPager(3, updates, categories,flag, ExpListItems);
    				//setListAdapter(ap);
    				
    				
    	    		
    			}
    			
    			Log.d(TAG,"on progress update ended");
        		
    		    
    		      
    		   }

    	protected void onPostExecute(String result) {
    		super.onPostExecute(result);
    		//setListAdapter(ap);
    		Log.d(TAG,"Post Execute before change to adapter");
  
    		
    		//resetList();
    		/*WavesRssFeedsActivity.this.runOnUiThread(new Runnable(){
    			public void run(){
    				Log.d(TAG, "in runonuithread");
    				ap.notifyDataSetChanged();
    				
    				
    			}
    		});*/
    		
    		
    		pd.dismiss();
    		

    		
    		
    		Log.d(TAG, "Completed UI Thread");
    		//setListAdapter(ap);
    		//showDialog("Downloading done");
    		Log.d(TAG,"Post Execute after change to adapter");
    			   }
    		
    		
    }	
    private void initViewPager(int pageCount, List<RSSDescription> updates, List<RSSDescription> categories ,int flag,ArrayList<ExpandListGroup> ExpListItems ) {
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ExamplePagerAdapter(this, pageCount,updates, categories, flag,ExpListItems);
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(1);
		mPager.setPageMargin(1);
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        
        case R.id.Refresh:
        	Log.d("CustomTag","RefreshSelected selected");
        	try {
    			new UpdateClass().execute();    				
    		} catch (Exception e) {
    			Toast.makeText(this , "error in intent "+e, Toast.LENGTH_LONG).show();
    			}finally{
    				   		    	
    			}            
            return true;
            
        case R.id.AboutUs:
        	Intent i =  new Intent(WavesRssFeedsActivity.this, ContactInformation.class);
        	startActivity(i);
        	
        	
        	return true;
        	
        case R.id.Map:
        	Intent i1 = new Intent(WavesRssFeedsActivity.this, CampusMap.class);
        	startActivity(i1);
        
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	/* void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId()){
	case R.id.button1:
		try {
			new UpdateClass().execute();
			/*Thread timer = new Thread(){
				public void run(){
					try{
//						WavesRssFeedsActivity waves = new WavesRssFeedsActivity();
//						waves.startUpClass();
						sleep(1000);
						Log.d(TAG,"Sleep Done");
						resetList();
						Log.d(TAG,"After Resetlist");
				    	
					} catch(InterruptedException e){
						e.printStackTrace();
					}finally {
						}
				}
				
	
			};
					timer.start();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this , "error in intent "+e, Toast.LENGTH_LONG).show();
			}finally{
				//Log.d(TAG,"Calling Resetlist");
		    	
				//Log.d(TAG,"After Resetlist");
		    	
			}
		break;
		}	*/

	

	/*@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}*/

	
		// TODO Auto-generated method stub
		
	
    	
   
	
	
}