package com.waves.rss;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

public class ContactInformation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		TextView t;
		t=(TextView)findViewById(R.id.textView1);
	    Linkify.addLinks(t, Linkify.PHONE_NUMBERS);
	}

}
