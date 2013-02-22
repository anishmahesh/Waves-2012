package com.waves.rss;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.maps.OnSingleTapListener;
import com.readystatesoftware.maps.TapControlledMapView;

public class CampusMap extends MapActivity {

	TapControlledMapView map;
	MapController controller;
	GeoPoint origPoint;
	GeoPoint point1,point2,point3,point4,point5,point6,point7,point8,point9;
	List<Overlay> overlayList;
	Drawable d;
	CustomPinpoint custom;
	//String[] str;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        Toast.makeText(this, "Please wait while map loads", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Click on green icon and then tap the bubble to view today's events and their schedule", Toast.LENGTH_LONG).show();
        
        d = getResources().getDrawable(R.drawable.marker2);
        
        map=(TapControlledMapView)findViewById(R.id.mvMain);
        map.setBuiltInZoomControls(true);
        
        map.setOnSingleTapListener(new OnSingleTapListener() {
			
			@Override
			public boolean onSingleTap(MotionEvent e) {
				// TODO Auto-generated method stub
				custom.hideAllBalloons();
				return false;
			}
		});
        
        overlayList = map.getOverlays();
        map.setStreetView(false);
        map.setSatellite(true);
        controller=map.getController();
        origPoint = new GeoPoint(15392566,73880052);
        controller.animateTo(origPoint);
        controller.setZoom(18);
        
        custom = new CustomPinpoint(d, map , this);
		custom.setShowClose(false);
		custom.setShowDisclosure(true);
		custom.setSnapToCenter(false);
        
        point1 = new GeoPoint(15393110,73879816);
        OverlayItem overlayItem1 = new OverlayItem(point1, "A - Wing", null);
        custom.insertPinpoint(overlayItem1);

        point2 = new GeoPoint(15393616,73879944);
        OverlayItem overlayItem2 = new OverlayItem(point2, "LT-4", null);
        custom.insertPinpoint(overlayItem2);

        point3 = new GeoPoint(15393565,73880234);
        OverlayItem overlayItem3 = new OverlayItem(point3, "LT-3", null);      
		custom.insertPinpoint(overlayItem3);
        
        point4 = new GeoPoint(15393027,73880535);
        OverlayItem overlayItem4 = new OverlayItem(point4, "Auditorium", null);
		custom.insertPinpoint(overlayItem4);
		
		point5 = new GeoPoint(15392313,73880642);
        OverlayItem overlayItem5 = new OverlayItem(point5, "C - Wing", null);
		custom.insertPinpoint(overlayItem5);
		
		point6 = new GeoPoint(15392727,73881092);
        OverlayItem overlayItem6 = new OverlayItem(point6, "LT-2", null);
		custom.insertPinpoint(overlayItem6);
		
		point7 = new GeoPoint(15392416,73881173);
        OverlayItem overlayItem7 = new OverlayItem(point7, "LT-1", null);
		custom.insertPinpoint(overlayItem7);
		
		point8 = new GeoPoint(15391775,73880127);
        OverlayItem overlayItem8 = new OverlayItem(point8, "Outdoor Stage", null);
		custom.insertPinpoint(overlayItem8);
		
		point9 = new GeoPoint(15391739,73880953);
        OverlayItem overlayItem9 = new OverlayItem(point9, "CC", null);
		custom.insertPinpoint(overlayItem9);
       
        Log.d("anish", "map loaded");
 
		overlayList.add(custom);
       // map.invalidate();
		
		
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*public class MapOverlay extends Overlay{

	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
			long when) {
		// TODO Auto-generated method stub
		super.draw(canvas, mapView, shadow, when);
		Log.d("anish", "1");
		Point screenPts = new Point();
		map.getProjection().toPixels(point,screenPts);
		
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		Log.d("anish", "going to draw");
		canvas.drawBitmap(bmp, screenPts.x, screenPts.y, null);
		
//		OverlayItem overlayItem = new OverlayItem(point, "What's up", "2nd String");
//		CustomPinpoint custom = new CustomPinpoint(d, MainActivity.this);
//		custom.insertPinpoint(overlayItem);
//		overlayList.add(custom);
		
		return true;
	}
}*/
		
}
