package com.ufit.activity;

import java.util.List;

import com.ufit.R;
import com.ufit.contants.Constants;
import com.ufit.dao.profile.ProfileDataSource;
import com.ufit.model.profile.Profile;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ProfileActivity extends Activity {

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(Constants.TAG, "Starting ProfileActivity");
		
		
		ProfileDataSource datasource = new ProfileDataSource(this);
		datasource.open();
		
		List<Profile> profiles = datasource.getAllProfiles();
		
		
//		Profile profile2 = datasource.createProfile("walterito", "ilovepupusas");
		Profile profile = datasource.createProfile("szhou", "mypassword1123");
		for(Profile p : profiles){
			Log.d(Constants.TAG, "Login: " + p.getUserLogin());
			Log.d(Constants.TAG, "Password: " + p.getPassword());
		}
	}
	
}
