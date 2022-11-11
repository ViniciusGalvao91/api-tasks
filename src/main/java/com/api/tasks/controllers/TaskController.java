package com.api.tasks.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.tasks.entitys.Task;
import com.api.tasks.services.TaskServices;

@Controller
@RequestMapping(path = "/tasks")
public class TaskController {

	@Autowired
	private TaskServices taskservices;

	@GetMapping
	public ResponseEntity<List<Task>> findAllTasks() {
		List<Task> listAllTasks = taskservices.findAll();
		return ResponseEntity.ok().body(listAllTasks);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Task> findTaskById(@PathVariable Integer id) {
		Task objTask = taskservices.findById(id);
		return ResponseEntity.ok().body(objTask);
	}
	
	@PostMapping
	public ResponseEntity<Task> insertTask(@RequestBody Task objTask){
		objTask = taskservices.insert(objTask);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objTask.getId()).toUri();
		return ResponseEntity.created(uri).body(objTask);
	}
	

}
