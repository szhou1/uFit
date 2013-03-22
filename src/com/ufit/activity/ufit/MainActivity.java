package com.ufit.activity.ufit;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.ufit.R;
import com.ufit.contants.Constants;
import com.ufit.dao.profile.ProfileDataSource;
import com.ufit.model.profile.Profile;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.i(Constants.TAG, "Starting MainActivity");
		ProfileDataSource datasource = new ProfileDataSource(this);
		datasource.open();
		
		List<Profile> profiles = datasource.getAllProfiles();
		
		for(Profile p : profiles){
			Log.d(Constants.TAG, "Login: " + p.getUserLogin());
			Log.d(Constants.TAG, "Password: " + p.getPassword());
		}
		
		Profile profile2 = datasource.createProfile("walterito", "ilovepupusas");
		Profile profile = datasource.createProfile("szhou", "mypassword1123");

		Log.d("ufit", "Login: " + profile.getUserLogin());
		Log.d("ufit", "Password: " + profile.getPassword());
		Log.d("ufit", "Login: " + profile2.getUserLogin());
		Log.d("ufit", "Password: " + profile2.getPassword());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
