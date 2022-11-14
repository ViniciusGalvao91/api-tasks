package com.api.tasks.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TB_TASKS")
public class TaskModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(nullable = false, unique = false, length = 100)
	private String title;

	@Column(nullable = true, unique = false, length = 360)
	private String description;

	@FutureOrPresent
	@Column(nullable = false, unique = false, length = 20)
	private LocalDate date;

	@Column(nullable = true, unique = false, length = 10)
	private String time;

	@Column(nullable = false, unique = false)
	private boolean state;

	public TaskModel() {
	}

	public TaskModel(String id, String title, String description, LocalDate date, String time, boolean state) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.time = time;
		this.state = state;
	}

	public TaskModel(String title, String description, LocalDate date, String time) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.time = time;

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
