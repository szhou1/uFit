package com.ufit.activity;

import java.util.List;

import android.app.Activity;
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
import com.ufit.dao.profile.ProfileDataSource;
import com.ufit.model.profile.Profile;

public class MainActivity extends Activity {

	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnProgressButton();
		addListenerOnDietButton();
		
		Log.i(Constants.TAG, "Starting MainActivity");
		ProfileDataSource datasource = new ProfileDataSource(this);
		datasource.open();
		
		List<Profile> profiles = datasource.getAllProfiles();
		
		
//		Profile profile2 = datasource.createProfile("walterito", "ilovepupusas");
//		Profile profile = datasource.createProfile("szhou", "mypassword1123");
		for(Profile p : profiles){
			Log.d(Constants.TAG, "Login: " + p.getUserLogin());
			Log.d(Constants.TAG, "Password: " + p.getPassword());
		}

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

}
