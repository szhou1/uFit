package com.ufit.activity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.ufit.R;
import com.ufit.contants.Constants;
import com.ufit.dao.profile.EntryDataSource;
import com.ufit.dao.profile.ProfileDataSource;
import com.ufit.model.profile.Profile;
import com.ufit.model.profile.WorkoutEntry;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

public class ProgressActivity extends ListActivity {

	private EntryDataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);

		datasource = new EntryDataSource(this);
		datasource.open();

		Log.d(Constants.TAG, "****************************** EntryDataSource opened");

		
		List<WorkoutEntry> values = datasource.getAllEntries();
		Log.d(Constants.TAG, "****************************** getAllEntries done");

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<WorkoutEntry> adapter = new ArrayAdapter<WorkoutEntry>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<WorkoutEntry> adapter = (ArrayAdapter<WorkoutEntry>) getListAdapter();
		WorkoutEntry entry = null;
		switch (view.getId()) {
		case R.id.add:
			// Save the new entry to the database
			Format formatter;
			Date date = new Date();
			formatter = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.US);
			String timestamp = formatter.format(date);
			
			entry = datasource.createEntry("1", timestamp, "Benchpress", "135 lb heavy as a muthalove");
			adapter.add(entry);
			break;
		case R.id.delete:
			if (getListAdapter().getCount() > 0) {
				entry = (WorkoutEntry) getListAdapter().getItem(0);
				datasource.deleteEntry(entry);
				adapter.remove(entry);
			}
			break;
		}
		adapter.notifyDataSetChanged();
	}
	
	  @Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.progress, menu);
		return true;
	}

}
