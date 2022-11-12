package com.api.tasks.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tasks.models.TaskModel;
import com.api.tasks.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public List<TaskModel> findAllTasks() {

		return taskRepository.findAll();
	}

	public TaskModel findTaskById(UUID id) {
		Optional<TaskModel> objTask = taskRepository.findById(id);
		return objTask.get();
	}

	public TaskModel insertTask(TaskModel objTask) {
		return taskRepository.save(objTask);
	}

	public TaskModel updateTask(UUID id, TaskModel objTask) {

		TaskModel taskEntity = taskRepository.getReferenceById(id);
		updateData(taskEntity, objTask);
		return taskRepository.save(taskEntity);
	}

	private void updateData(TaskModel entity, TaskModel objTask) {

		entity.setTitle(objTask.getTitle());
		entity.setDescription(objTask.getDescription());
		entity.setDate(objTask.getDate());
		entity.setTime(objTask.getTime());
		entity.setState(objTask.getState());
	}

	public void deleteTask(UUID id) {
		taskRepository.deleteById(id);
	}
}
