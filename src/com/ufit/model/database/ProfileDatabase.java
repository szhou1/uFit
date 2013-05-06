package com.ufit.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProfileDatabase extends SQLiteOpenHelper {

	public static final String TABLE_PROFILE = "profiles";
	public static final String COLUMN_LOGIN = "loginId";
	public static final String COLUMN_PW = "password";

	private static final String DATABASE_NAME = "profile.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_PROFILE + "(" + COLUMN_PW + " text not null," + COLUMN_LOGIN
			+ " text not null);";

	public ProfileDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(ProfileDatabase.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
		onCreate(db);
	}

}
