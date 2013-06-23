package com.ufit.activity;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ufit.R;
import com.ufit.contants.Constants;

public class MainActivity extends Activity {

	Button button;
	ComponentName cn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(Constants.TAG, "Starting MainActivity");

		addListenerOnProgressButton();
		addListenerOnDietButton();
		addListenerOnCalendarButton();//aklsdjflkafjlkfj
	}

	public void addListenerOnProgressButton() {
		final Context context = this;
		button = (Button) findViewById(R.id.progress);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, ProgressActivity.class);
				startActivity(intent);
			}
		});
	}

	public void addListenerOnDietButton() {
		final Context context = this;
		button = (Button) findViewById(R.id.diet);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, DietActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void addListenerOnCalendarButton() {

		button = (Button) findViewById(R.id.calendar);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				// NOTE: may vary depending on the OS version
				cn = new ComponentName("com.android.calendar",
						"com.android.calendar.LaunchActivity");
				intent.setComponent(cn);
				startActivity(intent);
			}
		});
	}

}
