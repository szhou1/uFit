package com.model.profile;

import java.util.List;

public class Profile {

	private String userLogin;
	private String password;
	private List<Profile> friends;
	private List<WorkoutEntry> entries;
	
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Profile> getFriends() {
		return friends;
	}
	public void setFriends(List<Profile> friends) {
		this.friends = friends;
	}
	public List<WorkoutEntry> getEntries() {
		return entries;
	}
	public void setEntries(List<WorkoutEntry> entries) {
		this.entries = entries;
	}
	
}
