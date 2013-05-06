package com.ufit.model.exercise;

public class Exercise {

	private String name;
	private String muscleGroup;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMuscleGroup() {
		return muscleGroup;
	}
	public void setMuscleGroup(String muscleGroup) {
		this.muscleGroup = muscleGroup;
	}
	
	public String toString(){
		String s = name + " : " + muscleGroup;
		
		return s;
		
	}
}
