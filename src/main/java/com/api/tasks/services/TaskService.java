package com.api.tasks.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

	public Optional<TaskModel> findTaskById(String id) {		
		return taskRepository.findById(id);
	}

	@Transactional
	public TaskModel save(TaskModel objTask) {
		return taskRepository.save(objTask);
	}

	public TaskModel updateTask(String id, TaskModel objTask) {

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

	@Transactional
	public void deleteTask(TaskModel taskModel) {
		taskRepository.delete(taskModel);
	}
}
