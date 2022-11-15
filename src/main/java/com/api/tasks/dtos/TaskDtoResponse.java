package com.api.tasks.dtos;

import com.api.tasks.Utils.TaskUtils;
import com.api.tasks.models.TaskModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TaskDtoResponse {

	private String id;
	private String title;
	private String description;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy")
	private String date;
	private String time;
	private boolean state;
	
	public TaskDtoResponse(TaskModel task) {
		id = task.getId();
		title = task.getTitle();
		description = task.getDescription();
		date = TaskUtils.LocalDateToString(task.getDate());
		time = TaskUtils.LocalTimeToString(task.getTime());
		state = task.getState();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
}
