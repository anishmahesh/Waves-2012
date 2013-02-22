package com.waves.rss;

import java.util.ArrayList;
import java.util.List;

import com.tutorial.expandListView.MainActivity;
import com.tutorial.expandListView.Adapter.ExpandListAdapter;

import android.app.Activity;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

public class ExamplePagerAdapter extends PagerAdapter {
	
	private String TAG = "ExamplePageAdapter";
	
	protected transient Activity mContext;
	List<RSSDescription> updates, categories;
	int layoutid;
	int textviewid;
	int flag;
	
	private MyScheduleAdapter ExpAdapter;
	private ArrayList<ExpandListGroup> ExpListItems;
	private ExpandableListView ExpandList;
	
	private int mLength = 0;
	//private int mBackgroundColor = 0xFFFFFFFF;
	//private int mTextColor = 0xFF000000;
	
	//private String[] mData = {
	  //  "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
	//};
	
	public ExamplePagerAdapter(Activity context, int length,List<RSSDescription> updates,List<RSSDescription> categories, int flag,ArrayList<ExpandListGroup> ExpListItems) {
		mContext = context;
		mLength = length;
		
		this.updates=updates;
		this.categories=categories;
		this.flag=flag;
		this.ExpListItems=ExpListItems;
	}
	
	@Override
	public int getCount() {
		return mLength;
	}
	
	@Override
	public Object instantiateItem(View container, int position) {
		
		/*LinearLayout v = new LinearLayout(mContext);
		if(position==0){
		
		TextView t = new TextView(mContext);
		t.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		t.setText(mData[position]);
		t.setTextSize(120);
		t.setGravity(Gravity.CENTER);
		t.setTextColor(mTextColor);
		t.setBackgroundColor(mBackgroundColor);
		
		v.addView(t);
		}
		else{
		
		
		ListView l = new ListView(mContext);

        l.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT));
        
        ArrayAdapter<RSSDescription> ap;
        ap=  new RSSArrayAdapter(mContext, updates);
        l.setAdapter(ap);
        v.addView(l);}*/
		View v=null;
		
		if (position==0){
		
		LayoutInflater inflator = mContext.getLayoutInflater();
		v = inflator.inflate(R.layout.main, null);
		
		ImageView myImage = (ImageView) v.findViewById(R.id.ivWavesBackground);
		myImage.setAlpha(75);
		
		ListView l = (ListView) v.findViewById(R.id.list1);
		ArrayAdapter<RSSDescription> ap;
		 layoutid = R.layout.rowitem;
		 textviewid= R.id.tvDescription;
        ap=  new RSSArrayAdapter(mContext, updates, layoutid, textviewid);
        l.setAdapter(ap);
		}
		else if(position ==1)
		{
			LayoutInflater inflator = mContext.getLayoutInflater();
			v = inflator.inflate(R.layout.eventcategories, null);
			
			ImageView myImage = (ImageView) v.findViewById(R.id.ivWavesBackground1);
			myImage.setAlpha(75);
			
			GridView gv = (GridView) v.findViewById(R.id.grid_view);
			
			Log.d(TAG, "Before call to grid adapter");
			EventsGridAdapter evg = new EventsGridAdapter(mContext, categories, flag);
			gv.setAdapter(evg);
			Log.d(TAG, "After call to grid adapter");
			
			//ListView l = (ListView) v.findViewById(R.id.list2);
			//ArrayAdapter<RSSDescription> ap;
			 //layoutid = R.layout.categories;
			 //textviewid= R.id.tvCategory;
	        //ap=  new RSSArrayAdapter(mContext, categories, layoutid, textviewid);
	        //l.setAdapter(ap);
			
		}
		else{
			
			LayoutInflater inflator = mContext.getLayoutInflater();
			v = inflator.inflate(R.layout.myschedule, null);
			ExpandList = (ExpandableListView) v.findViewById(R.id.explist_My_Schedule);
			ExpAdapter = new MyScheduleAdapter(mContext, ExpListItems);
	        ExpandList.setAdapter(ExpAdapter);
			
		}
                
		
		((ViewPager) container).addView(v, 0);
		
		return v;
	}
	
	
	@Override
	public void destroyItem(View container, int position, Object view) {
		((ViewPager) container).removeView((View) view);
	}
	
	
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((View) object);
	}
	
	
	@Override
	public void finishUpdate(View container) {}
	
	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {}
	
	@Override
	public Parcelable saveState() {
		return null;
	}
	
	@Override
	public void startUpdate(View container) {}
	
}

