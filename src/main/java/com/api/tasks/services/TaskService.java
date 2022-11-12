package com.api.tasks.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.tasks.models.TaskModel;
import com.api.tasks.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Page<TaskModel> findAll(Pageable pageable) {
		return taskRepository.findAll(pageable);
	}

	public Optional<TaskModel> findById(String id) {		
		return taskRepository.findById(id);
	}

	@Transactional
	public TaskModel save(TaskModel objTask) {
		return taskRepository.save(objTask);
	}

	public TaskModel update(String id, TaskModel objTask) {

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
	public void delete(TaskModel taskModel) {
		taskRepository.delete(taskModel);
	}
}
