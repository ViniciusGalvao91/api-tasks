package com.api.tasks.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.api.tasks.Utils.TaskUtils;
import com.api.tasks.models.TaskModel;

public class TaskDto {

	@NotBlank
	@Size(max = 100)
	private String title;

	@NotBlank
	@Size(max = 360)
	private String description;

	@NotBlank
	@Size(max = 10)
	private String date;

	private String time;
	
	private boolean state = false;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public TaskModel changeToTask() {

		return new TaskModel(title, description, TaskUtils.stringToLocalDate(date), time);
	}
}
