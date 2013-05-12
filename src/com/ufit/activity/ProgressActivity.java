package com.ufit.activity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ufit.R;
import com.ufit.contants.Constants;
import com.ufit.dao.profile.EntryDataSource;
import com.ufit.model.profile.WorkoutEntry;

public class ProgressActivity extends ListActivity {

	private EntryDataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
//		ListView listView = (ListView) findViewById(android.R.layout.);

		datasource = new EntryDataSource(this);
		datasource.open();


		List<WorkoutEntry> values = datasource.getAllEntries();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<WorkoutEntry> adapter = new ArrayAdapter<WorkoutEntry>(
				this, android.R.layout.simple_list_item_1, values);
//		listView.setAdapter(adapter);
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

			entry = datasource.createEntry(timestamp, "Benchpress",
					"135 lb heavy as a muthalove");
			Log.d(Constants.TAG, "added entry timestamp: id: " + entry.getId() + " : " + timestamp);
			
			adapter.add(entry);

			break;
		case R.id.delete:
			if (getListAdapter().getCount() > 0) {
				entry = (WorkoutEntry) getListAdapter().getItem(0);
				Log.d(Constants.TAG, "Deleting entry timestamp: id:" + entry.getId() + ": " + entry.getWorkoutTS());
				datasource.deleteEntry(entry);
				adapter.remove(entry);
			}
			break;
		case R.id.delete_all:
			if (getListAdapter().getCount() > 0) {
				datasource.deleteAllEntries();
				adapter.clear();
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

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	 String selection = l.getItemAtPosition(position).toString();
	 
     final Dialog dialog = new Dialog(this);

     dialog.setContentView(R.layout.entry_dialog);
     dialog.setTitle(R.string.workout_entry);

     dialog.show();
     
     LinearLayout delete = (LinearLayout)dialog.findViewById(R.id.delete_entry_dialog);
     delete.setOnClickListener(new OnClickListener() {

         public void onClick(View v) {
            dialog.dismiss();
 			Log.d(Constants.TAG, "clicked delete ");
 			
 			WorkoutEntry entry = null;
 			
 			
//			datasource.deleteAllEntries();
			ArrayAdapter<WorkoutEntry> adapter = (ArrayAdapter<WorkoutEntry>) getListAdapter();
//			adapter.clear();
//             
 			adapter.notifyDataSetChanged();
         }
     });
     
	}

}
