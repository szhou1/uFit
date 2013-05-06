package com.ufit.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EntryDatabase extends SQLiteOpenHelper  {

	public static final String TABLE_ENTRY = "entries";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_TS = "timestamp";
	public static final String COLUMN_EXERCISE = "exercise";
	public static final String COLUMN_DESCRIPTION = "description";

	private static final String DATABASE_NAME = "entry.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_ENTRY + "(" + COLUMN_ID + " text not null, " + COLUMN_TS + " text not null," + COLUMN_EXERCISE
			+ " text not null, " + COLUMN_DESCRIPTION + " );";

	
	public EntryDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(EntryDatabase.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRY);
		onCreate(db);
	}

}
