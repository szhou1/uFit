package com.ufit.dao.profile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ufit.contants.Constants;
import com.ufit.model.database.EntryDatabase;
import com.ufit.model.exercise.Exercise;
import com.ufit.model.profile.WorkoutEntry;

public class EntryDataSource {

	private SQLiteDatabase database;
	private EntryDatabase dbHelper;
	private String[] allColumns = {  
			EntryDatabase.COLUMN_ID,
			EntryDatabase.COLUMN_TS,
			EntryDatabase.COLUMN_EXERCISE,
			EntryDatabase.COLUMN_DESCRIPTION,};

	public EntryDataSource(Context context) {
		dbHelper = new EntryDatabase(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public WorkoutEntry createEntry(String ts, String exercise, String desc) {
		ContentValues values = new ContentValues();
//		values.put(EntryDatabase.COLUMN_ID, id);
		values.put(EntryDatabase.COLUMN_TS, ts);
		values.put(EntryDatabase.COLUMN_EXERCISE, exercise);
		values.put(EntryDatabase.COLUMN_DESCRIPTION, desc);

		database.insert(EntryDatabase.TABLE_ENTRY, null, values);
		Cursor cursor = database.query(EntryDatabase.TABLE_ENTRY,
				allColumns, null, null, null, null, null);
		cursor.moveToLast();
		WorkoutEntry newEntry = cursorToEntry(cursor);
		cursor.close();
		Log.d(Constants.TAG, "new entry id: " + newEntry.getId());

		return newEntry;
	}

	public void deleteEntry(WorkoutEntry entry) {
		Log.d(Constants.TAG, "deleteEntry: " + entry.getId());

		String id = entry.getId();
		database.delete(EntryDatabase.TABLE_ENTRY, EntryDatabase.COLUMN_ID
				+ " = '" + id + "'", null);
	}

	public List<WorkoutEntry> getAllEntries() {
		List<WorkoutEntry> entries = new ArrayList<WorkoutEntry>();

		Cursor cursor = database.query(EntryDatabase.TABLE_ENTRY,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			WorkoutEntry entry = cursorToEntry(cursor);
			Log.d(Constants.TAG, "	getallentries: timestamp: " + entry.getWorkoutTS());

			entries.add(entry);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return entries;
	}
	
	public String toString(WorkoutEntry entry){
		
		return entry.getId() + " : " + entry.getWorkoutTS();
	}

	
	private WorkoutEntry cursorToEntry(Cursor cursor) {
		WorkoutEntry entry = new WorkoutEntry();
		
		// get exercise
		String [] exStr = cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_EXERCISE)).split(";");
		Exercise ex = new Exercise();
		ex.setName(exStr[0]);
		ex.setMuscleGroup("chest");

		// get date
		String dateStr = cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_TS));
		Date date = null;
		try {
			date = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.US).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		entry.setId(cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_ID)));
		entry.setExercise(ex);
		entry.setWorkoutTS(date);
		entry.setDescription(cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_DESCRIPTION)));
		return entry;
	}

	public void deleteAllEntries() {
		Log.d(Constants.TAG, "Deleting all workout entries");
		database.delete(EntryDatabase.TABLE_ENTRY, null, null);		
	}
}
