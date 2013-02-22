package com.waves.rss;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class GetJSONData {
	
	private final String TAG = "GetJSON Class";
	
	String data;
	String fname = "JSON.txt";
	
	final static String URL = "http://www.bits-waves.org/2012/mobile/index.json";
	HttpClient client;
	HttpResponse response;
	HttpEntity e;
	Context mContext;
	
	int status;
	
	public GetJSONData(Context context){
		mContext=context;
	}
	
	
	public int storeJSONData(){
		//categorieslist = new ArrayList<RSSDescription>();
		
        client = new DefaultHttpClient();
    	HttpGet get = new HttpGet(URL);
    	try {
    		Log.d(TAG , "Inside the try for response just before response");
    		response = client.execute(get);		
    		Log.d(TAG , "Inside the try for response just after response");
    		e = response.getEntity();
    		 data = EntityUtils.toString(e);
    		Log.d(TAG , "Inside the try and set data entity utils");
    		status = response.getStatusLine().getStatusCode();
    		Log.d(TAG, status+"");
    	} catch (ClientProtocolException e) {
    	
			// TODO Auto-generated catch block    		
    		e.printStackTrace();
    		return 10;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 10;
		}    	
    	if( status != 200){
    		return 10;
    	}
    	
    	FileOutputStream fos;
        try 
        {
        	// Change Mode to MODE_APPEND
        	fos = mContext.openFileOutput(fname, Context.MODE_PRIVATE );
        	fos.write(data.getBytes());
        	fos.close();
         
        	//Toast.makeText(Internal_storageActivity.this,fileName.getText() +" Saved !",Toast.LENGTH_LONG).show();
         
        } 
        catch (FileNotFoundException e) 
        {
         // TODO Auto-generated catch block
        	e.printStackTrace();
        } 
        catch (IOException e) 
        {
         // TODO Auto-generated catch block
        	e.printStackTrace();
        }   	  	
    	return 1;
    			
	}


	

}