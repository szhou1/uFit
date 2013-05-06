package com.ufit.model.profile;

import java.util.Date;
import java.util.List;

import com.ufit.model.exercise.Exercise;

public class WorkoutEntry {
	
	private String id;
	private java.util.Date workoutTS;
	private Exercise exercise;
	private String description;
	
	public Date getWorkoutTS() {
		return workoutTS;
	}
	public void setWorkoutTS(Date workoutTS) {
		this.workoutTS = workoutTS;
	}
	public Exercise getExercise() {
		return exercise;
	}
	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String toString(){
		String str = this.workoutTS + " : " + this.exercise + " : " + this.description;
		return str;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
