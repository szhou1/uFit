package com.model.exercise;

import java.sql.Time;

public class Cardio extends Exercise {
// my name is walter
	private String distance;
	private Time time;
	public int testInt = 5;
	
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
}
