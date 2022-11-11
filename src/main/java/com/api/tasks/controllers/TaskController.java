package com.api.tasks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.tasks.entitys.Task;
import com.api.tasks.repository.TaskRepository;

@Controller
@RequestMapping(path="/tasks")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewTask (@RequestParam String title, @RequestParam String description, @RequestParam String date, @RequestParam String time, @RequestParam Integer state) {
		
		Task t = new Task();
		t.setTitle(title);
		t.setDescription(description);
		t.setDate(date);
		t.setTime(time);
		t.setState(state);
		taskRepository.save(t);
		return "Tarefa salva com sucesso!!";
	}
	
	@GetMapping(path="/")
	public @ResponseBody Iterable<Task> getAllTasks(){
		return taskRepository.findAll();
	
	}
}
