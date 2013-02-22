package com.waves.rss;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {
	SharedPreferences.Editor editor;
	
	private int count;
	SharedPreferences prefs;
	
	private final String TAG = "Splash";
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		count=prefs.getInt("count", 0);
		
		/*if (count ==0){
			count++;
			editor = prefs.edit();
			editor.putInt("count", count);
			editor.commit();
			new UpdateClass().execute();
			
		}*/
			//{
			
			Thread timer = new Thread(){
				public void run(){
					try{			
						sleep(1000);
					} catch(InterruptedException e){
						e.printStackTrace();
					}finally{
						Intent serviceintent = new Intent(Splash.this, UpdatesService.class);
						startService(serviceintent);
						Intent openStartingPoint = new Intent( Splash.this , WavesRssFeedsActivity.class ); 
						startActivity(openStartingPoint);
					}
				}
			};
			timer.start();
		}
	//}
		

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	/*public class UpdateClass extends AsyncTask<String, Integer, String>{
		int interneterror = 0 ;
		int jsonerror=0;
    	protected void onPreExecute() {   		
    		      super.onPreExecute();    		
    		     // Dialog d = new Dialog(Splash.this);
    		      //d.setTitle("Inserted");
    		      //TextView tv = new TextView(Splash.this);
    		     // tv.setText("Success");
    		     // d.setContentView(tv);
    		     // d.show();   		
    		   }    	
    	@Override
    	protected String doInBackground(String... arg0) {    		
    		// TODO Auto-generated method stub        
    		GetRssFeedData rssData = new GetRssFeedData(Splash.this);
    		GetJSONData jsonData = new GetJSONData(Splash.this);
    		interneterror = rssData.getData();
    		jsonerror = jsonData.storeJSONData();
    		Log.d(TAG, "jsonerror="+Integer.toString(jsonerror));
    		Log.d(TAG, "inside do in background after accessing net internet error value ="+interneterror);
			
    		Log.d("Splash", "databaseloading done");
    		return "All Done";
    	}
    	
    	protected void onProgressUpdate(Integer... values) {
    		      super.onProgressUpdate(values);
    		      
    		   }

    	protected void onPostExecute(String result) {
    		Log.d(TAG, "inside postexecute");
			if ( interneterror==10 || jsonerror == 10){
				count--;
				editor = prefs.edit();
				editor.putInt("count", count);
				editor.commit();
				Log.d(TAG, "inside if interneterror == 10 ");
				new AlertDialog.Builder(Splash.this)
			    .setTitle("Internet Access Not Found")
			    .setMessage("Please check net connection and retry")
			    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			        	Intent openStartingPoint = new Intent( Splash.this , Splash.class ); 
						startActivity(openStartingPoint);
			        }
			     })
			    .setNegativeButton("No", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        	finish();
			        }
			     })
			     .show();

			}
			else{
		    	Intent openStartingPoint = new Intent( Splash.this , WavesRssFeedsActivity.class ); 
				startActivity(openStartingPoint);
			}
			
      	}
    		
 
    }	*/

}
