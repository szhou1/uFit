package com.ufit.dao.profile;

import java.util.ArrayList;
import java.util.List;

import com.ufit.contants.Constants;
import com.ufit.model.database.ProfileDatabase;
import com.ufit.model.profile.Profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ProfileDataSource {

	private SQLiteDatabase database;
	private ProfileDatabase dbHelper;
	private String[] allColumns = { ProfileDatabase.COLUMN_LOGIN,
			ProfileDatabase.COLUMN_PW };

	public ProfileDataSource(Context context) {
		dbHelper = new ProfileDatabase(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Profile createProfile(String loginId, String password) {
		ContentValues values = new ContentValues();
		values.put(ProfileDatabase.COLUMN_LOGIN, loginId);
		values.put(ProfileDatabase.COLUMN_PW, password);
		// long insertId = database.insert(CreateProfileDatabase.TABLE_PROFILE,
		// null, values);
		database.insert(ProfileDatabase.TABLE_PROFILE, null, values);
		Cursor cursor = database.query(ProfileDatabase.TABLE_PROFILE,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		Profile newProfile = cursorToProfile(cursor);
		cursor.close();
		return newProfile;
	}

	public void deleteProfile(Profile profile) {
		String id = profile.getUserLogin();
		Log.d(Constants.TAG, "Deleting profile with id: " + id);
		database.delete(ProfileDatabase.TABLE_PROFILE, ProfileDatabase.COLUMN_LOGIN
				+ " = '" + id + "'", null);
	}

	public List<Profile> getAllProfiles() {
		List<Profile> profiles = new ArrayList<Profile>();

		Cursor cursor = database.query(ProfileDatabase.TABLE_PROFILE,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Profile profile = cursorToProfile(cursor);
			profiles.add(profile);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return profiles;
	}

	private Profile cursorToProfile(Cursor cursor) {
		Profile profile = new Profile();
		profile.setUserLogin(cursor.getString(0));
		profile.setPassword(cursor.getString(1));
		return profile;
	}
}
