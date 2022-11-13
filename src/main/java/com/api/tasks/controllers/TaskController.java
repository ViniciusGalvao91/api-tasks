package com.api.tasks.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
	public ResponseEntity<Page<TaskModel>> getAllTasks(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(taskservices.findAll(pageable));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> getOneTask(@PathVariable String id) {
		Optional<TaskModel> taskModelOptional = taskservices.findById(id);
		if (!taskModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
		}
	
		taskModelOptional.ifPresent(date -> taskservices.dateToString(taskModelOptional.get().getDate()));
		return ResponseEntity.status(HttpStatus.OK).body(taskModelOptional.get().getDate());
	}

	@PostMapping
	public ResponseEntity<TaskModel> saveTask(@RequestBody @Valid TaskDto taskDto) {
		var taskModel = new TaskModel();
		BeanUtils.copyProperties(taskDto, taskModel);
		taskModel.setDate(taskservices.stringToLocalDate(taskDto.getDate()));
		taskModel.setState(false);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(taskservices.save(taskModel));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Object> updateTask(@PathVariable String id, @RequestBody @Valid TaskDto taskDto) {
		Optional<TaskModel> taskModelOptional = taskservices.findById(id);
		if (!taskModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
		}
		var taskModel = taskModelOptional.get();
		BeanUtils.copyProperties(taskDto, taskModel);
		return ResponseEntity.status(HttpStatus.OK).body(taskservices.save(taskModel));
	}

	@PutMapping(path = "/state/{id}")
	public ResponseEntity<TaskModel> updateState(@PathVariable String id, @RequestBody TaskModel objTask) {
		return ResponseEntity.status(HttpStatus.OK).body(taskservices.update(id, objTask));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteTask(@PathVariable String id) {
		Optional<TaskModel> taskModelOptional = taskservices.findById(id);
		if (!taskModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
		}
		taskservices.delete(taskModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada com sucesso!");
	}

}
