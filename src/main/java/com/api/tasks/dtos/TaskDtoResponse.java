package com.api.tasks.dtos;

import java.util.Optional;

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

	public TaskDtoResponse(String id, String title, String description, String date, String time, boolean state) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.time = time;
		this.state = state;
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

	public static TaskDtoResponse changeToDtoResponse(TaskModel task) {

		return new TaskDtoResponse(task.getId(), task.getTitle(), task.getDescription(),TaskUtils.LocalDateToString(task.getDate()), task.getTime(), task.getState());

	}

	public static TaskDtoResponse changeOptionalTaskToDtoResponse(Optional<TaskModel> optionalTask) {

		return new TaskDtoResponse(optionalTask.get().getId(), optionalTask.get().getTitle(),
				optionalTask.get().getDescription(), TaskUtils.LocalDateToString(optionalTask.get().getDate()),
				optionalTask.get().getTime(), optionalTask.get().getState());

	}

}
