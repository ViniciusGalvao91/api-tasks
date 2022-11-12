package com.api.tasks.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TB_TASKS")
public class TaskModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, unique = false, length = 100)
	private String title;

	@Column(nullable = true, unique = false, length = 360)
	private String description;

	@Column(nullable = false, unique = false, length = 20)
	private String date;

	@Column(nullable = true, unique = false, length = 10)
	private String time;

	@Column(nullable = false, unique = false)
	private boolean state;

	public TaskModel() {

	}

	public TaskModel(UUID id, String title, String description, String date, String time, boolean state) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.time = time;
		this.state = state;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
