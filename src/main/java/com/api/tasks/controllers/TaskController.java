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

import com.api.tasks.Utils.TaskUtils;
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

	@GetMapping
	public ResponseEntity<Object> getAllTasks(
			@PageableDefault(page = 0, size = 10, sort = "date", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<TaskModel> taskModelPage = taskservices.findAll(pageable);
		if (taskModelPage.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma tarefa encontrada!");
		}
		Page<TaskDtoResponse> dtoResp = taskModelPage.map(taskModel -> TaskDtoResponse.changeToDtoResponse(taskModel));
		return ResponseEntity.status(HttpStatus.OK).body(dtoResp);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> getOneTask(@PathVariable String id) {
		Optional<TaskModel> taskModelOptional = taskservices.findById(id);
		if (taskModelOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
		}

		Optional<TaskDtoResponse> dtoResp = taskModelOptional
				.map(taskModel -> TaskDtoResponse.changeToDtoResponse(taskModel));

		return ResponseEntity.status(HttpStatus.OK).body(dtoResp);
	}

	@PostMapping
	public ResponseEntity<TaskDtoResponse> saveTask(@RequestBody @Valid TaskDto taskDto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(TaskDtoResponse.changeToDtoResponse(taskservices.save(taskDto.changeToTask())));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Object> updateTask(@PathVariable String id, @RequestBody @Valid TaskDto taskDto) {
		Optional<TaskModel> taskModelOptional = taskservices.findById(id);
		if (taskModelOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
		}

		BeanUtils.copyProperties(taskDto, taskModelOptional.get());
		taskModelOptional.get().setDate(TaskUtils.stringToLocalDate(taskDto.getDate()));
		return ResponseEntity.status(HttpStatus.OK)
				.body(TaskDtoResponse.changeToDtoResponse(taskservices.save(taskModelOptional.get())));
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
