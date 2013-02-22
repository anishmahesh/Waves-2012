package com.waves.rss;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

public class CustomPinpoint extends BalloonItemizedOverlay<OverlayItem>{

	private final String TAG = "Custom Pinpoint";
	private ArrayList<OverlayItem> pinpoints = new ArrayList<OverlayItem>();
	private Context c , context;
	
	public CustomPinpoint(Drawable defaultMarker, MapView mapView , Context context) {
		super(boundCenter(defaultMarker), mapView);
		c=mapView.getContext();
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return pinpoints.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return pinpoints.size();
	}
	
	public void insertPinpoint(OverlayItem item){
		pinpoints.add(item);
		this.populate();
	}
	
	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) {
		
		Log.d(TAG, "Just Before creating bundle");
		Bundle locationbundle = new Bundle();
		locationbundle.putString("location", item.getTitle());
		Log.d(TAG, "Just Before creating intent");

		Intent locationIntent = new Intent( context , LocationEventsActivity.class );
		locationIntent.putExtras(locationbundle);
		Log.d(TAG, "Just Before startactivity");
		
		context.startActivity(locationIntent);
		Log.d(TAG, "Just after intent");
		return true;
	}
}
