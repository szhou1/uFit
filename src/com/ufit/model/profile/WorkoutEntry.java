package com.ufit.model.profile;

import java.util.Date;
import java.util.List;

import com.ufit.model.exercise.Exercise;

public class WorkoutEntry {
	
	private Date workoutTS;
	private List<Exercise> excercise;
	private String description;
	
	public Date getWorkoutTS() {
		return workoutTS;
	}
	public void setWorkoutTS(Date workoutTS) {
		this.workoutTS = workoutTS;
	}
	public List<Exercise> getExcercise() {
		return excercise;
	}
	public void setExcercise(List<Exercise> excercise) {
		this.excercise = excercise;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
