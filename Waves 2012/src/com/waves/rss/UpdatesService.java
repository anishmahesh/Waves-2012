package com.waves.rss;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.widget.Toast;

public class UpdatesService extends Service{
	private SQLiteRss info;
	List<RSSDescription> updates;
	public static final String BROADCAST_ACTION = "com.waves.rss";
	Intent intent;
	//private int result = Activity.RESULT_CANCELED;
	//private Messenger outMessenger;
	//private Timer timer = new Timer();
	//private static final long UPDATE_INTERVAL = 60000;
	private int interneterror,jsonerror;
	
	//private ArrayList<String> list = new ArrayList<String>();
	
	GetRssFeedData data;
	GetJSONData json;
	

	private Looper mServiceLooper;
	  private ServiceHandler mServiceHandler;

	  // Handler that receives messages from the thread
	  private final class ServiceHandler extends Handler {
	      public ServiceHandler(Looper looper) {
	          super(looper);
	      }
	      @Override
	      public void handleMessage(Message msg) {
	          // Normally we would do some work here, like download a file.
	          // For our sample, we just sleep for 5 seconds.
	    	  json = new GetJSONData(UpdatesService.this);
	    	  data = new GetRssFeedData(UpdatesService.this);
	    	  jsonerror= json.storeJSONData();
	    	  interneterror= data.getData();
	    	  intent.putExtra("interneterror", interneterror);
	    	  intent.putExtra("jsonerror", jsonerror);
	    	 /* if(interneterror==10 && jsonerror==10){
	    		  stopSelf(msg.arg1);
	    	  }*/
	    	  //else{
	    		  sendBroadcast(intent);
	    	 // }
	    	  
	    	  
	         // long endTime = System.currentTimeMillis() + 5*1000;
	         /* while (System.currentTimeMillis() < endTime) {
	              synchronized (this) {
	                  try {
	                      wait(endTime - System.currentTimeMillis());
	                  } catch (Exception e) {
	                  }
	              }
	          }*/
	          // Stop the service using the startId, so that we don't stop
	          // the service in the middle of handling another job
	          stopSelf(msg.arg1);
	      }
	  }

	  @Override
	  public void onCreate() {
	    // Start up the thread running the service.  Note that we create a
	    // separate thread because the service normally runs in the process's
	    // main thread, which we don't want to block.  We also make it
	    // background priority so CPU-intensive work will not disrupt our UI.
		  intent = new Intent(BROADCAST_ACTION);	
	    HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
	    
	    thread.start();
	    
	    // Get the HandlerThread's Looper and use it for our Handler 
	    mServiceLooper = thread.getLooper();
	    mServiceHandler = new ServiceHandler(mServiceLooper);
	  }

	  @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
	      Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

	      // For each start request, send a message to start a job and deliver the
	      // start ID so we know which request we're stopping when we finish the job
	      Message msg = mServiceHandler.obtainMessage();
	      msg.arg1 = startId;
	      mServiceHandler.sendMessage(msg);
	      
	      // If we get killed, after returning from here, restart
	      return START_REDELIVER_INTENT;
	  }

	  @Override
	  public IBinder onBind(Intent intent) {
	      // We don't provide binding, so return null
	      return null;
	  }
	  
	  @Override
	  public void onDestroy() {
	    Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show(); 
	  }

	public List<RSSDescription> getUpdatesList() {
		updates=new ArrayList<RSSDescription>();
		info = new SQLiteRss(this);
		try {
			info.open();
			updates = info.getData();
			info.close();
		} catch (Exception e) {
		e.printStackTrace();
	}
		return updates;
		
	
	}

}
