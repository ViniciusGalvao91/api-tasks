package com.api.tasks.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.api.tasks.models.TaskModel;

public class TaskDtoResponse {

	private String id;
	private String title;
	private String description;
	private String date;
	private String time;
	private boolean state;

	public TaskDtoResponse(String id, String title, String description, String date, String time, boolean state) {
		super();
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

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public static TaskDtoResponse changeToDtoResponse(TaskModel task) {

		return new TaskDtoResponse(task.getId(), task.getTitle(), task.getDescription(),
				LocalDateToString(task.getDate()), LocalTimeToString(task.getTime()), task.getState());

	}

	public static TaskDtoResponse changeOptionalTaskToDtoResponse(Optional<TaskModel> optionalTask) {
				
		return new TaskDtoResponse(optionalTask.get().getId(), optionalTask.get().getTitle(), optionalTask.get().getDescription(),
				LocalDateToString(optionalTask.get().getDate()), LocalTimeToString(optionalTask.get().getTime()), optionalTask.get().getState());

	}
	
	public static String LocalDateToString(LocalDate date) {

		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(formatDate);
	}
	
	public static String LocalTimeToString(LocalTime time) {

		DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");								
		return time.format(formatTime);
	}
}
