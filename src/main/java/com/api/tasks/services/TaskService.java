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
	
	public TaskModel save(TaskModel task) {
		return taskRepository.save(task);
	}

	@Transactional
	public void delete(TaskModel taskModel) {
		taskRepository.delete(taskModel);
	}
}










