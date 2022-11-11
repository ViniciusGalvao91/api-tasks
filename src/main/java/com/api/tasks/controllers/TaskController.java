package com.api.tasks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.tasks.entitys.Task;
import com.api.tasks.services.TaskServices;

@Controller
@RequestMapping(path = "/tasks")
public class TaskController {

	@Autowired
	private TaskServices taskservices;
/*
	@PostMapping(path = "/add")
	public @ResponseBody String addNewTask(@RequestParam String title, @RequestParam String description,
			@RequestParam String date, @RequestParam String time, @RequestParam Integer state) {

		Task t = new Task();
		t.setTitle(title);
		t.setDescription(description);
		t.setDate(date);
		t.setTime(time);
		t.setState(state);
		// taskservices.add(t);
		return "Tarefa salva com sucesso!!";
	}
*/
	@GetMapping(path = "/")
	public ResponseEntity<List<Task>> findAll() {
		List<Task> list = taskservices.findAll();
		return ResponseEntity.ok().body(list);

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Task> findById(@PathVariable Integer id) {
		Task obj = taskservices.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
