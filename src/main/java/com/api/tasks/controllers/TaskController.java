package com.api.tasks.controllers;

import java.util.Optional;

import javax.validation.Valid;

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
import com.api.tasks.dtos.TaskDtoResponse;
import com.api.tasks.models.TaskModel;
import com.api.tasks.services.TaskService;

@RestController
@CrossOrigin(origins = "*, maxAge = 3600")
@RequestMapping(path = "/tasks")
public class TaskController {

	@Autowired
	private TaskService taskservices;

	@PostMapping
	public ResponseEntity<TaskDtoResponse> saveTask(@RequestBody @Valid TaskDto taskDto) {
		if (taskDto.getState() == true) {
			taskDto.setState(false);
		}
		var taskModel = new TaskModel();
		taskModel = taskDto.changeToTask();

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(TaskDtoResponse.changeToDtoResponse(taskservices.save(taskModel)));
	}

	@GetMapping
	public ResponseEntity<Object> getAllTasks(
			@PageableDefault(page = 0, size = 10, sort = "date", direction = Sort.Direction.ASC) Pageable pageable) {

		Page<TaskModel> taskModelPage = taskservices.findAll(pageable);
		if (taskModelPage.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma tarefa encontrada!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(taskModelPage);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> getOneTask(@PathVariable String id) {
		Optional<TaskModel> taskModelOptional = taskservices.findById(id);
		if (!taskModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(TaskDtoResponse.changeOptionalTaskToDtoResponse(taskModelOptional));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Object> updateTask(@PathVariable String id, @RequestBody @Valid TaskDto taskDto) {
		Optional<TaskModel> taskModelOptional = taskservices.findById(id);
		if (!taskModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(TaskDtoResponse.changeOptionalTaskToDtoResponse(taskModelOptional));
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
