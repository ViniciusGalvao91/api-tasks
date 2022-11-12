package com.api.tasks.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.tasks.dtos.TaskDto;
import com.api.tasks.models.TaskModel;
import com.api.tasks.services.TaskService;

@RestController
@CrossOrigin(origins = "*, maxAge = 3600")
@RequestMapping(path = "/tasks")
public class TaskController {

	@Autowired
	private TaskService taskservices;

	@GetMapping
	public ResponseEntity<List<TaskModel>> findAllTasks() {
		List<TaskModel> listAllTasks = taskservices.findAllTasks();
		return ResponseEntity.ok().body(listAllTasks);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<TaskModel> findTaskById(@PathVariable UUID id) {
		TaskModel objTask = taskservices.findTaskById(id);
		return ResponseEntity.ok().body(objTask);
	}

	@PostMapping
	public ResponseEntity<TaskModel> saveTask(@RequestBody @Valid TaskDto taskDto) {
		var taskModel = new TaskModel();
		BeanUtils.copyProperties(taskDto, taskModel);
		taskModel.setState(false);
		return ResponseEntity.status(HttpStatus.CREATED).body(taskservices.insertTask(taskModel));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<TaskModel> updateTask(@PathVariable UUID id, @RequestBody TaskModel objTask) {
		objTask = taskservices.updateTask(id, objTask);
		return ResponseEntity.ok().body(objTask);
	}

	@PutMapping(path = "/state/{id}")
	public ResponseEntity<TaskModel> updateState(@PathVariable UUID id, @RequestBody TaskModel objTask) {
		objTask = taskservices.updateTask(id, objTask);
		return ResponseEntity.ok().body(objTask);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
		taskservices.deleteTask(id);
		return ResponseEntity.noContent().build();
	}

}
