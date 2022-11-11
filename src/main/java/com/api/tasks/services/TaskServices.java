package com.api.tasks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tasks.entitys.Task;
import com.api.tasks.repository.TaskRepository;

@Service
public class TaskServices {

	@Autowired
	private TaskRepository taskRepository;

	public List<Task> findAll() {

		return taskRepository.findAll();
	}

	public Task findById(Integer id) {
		Optional<Task> obj = taskRepository.findById(id);
		return obj.get();
	}

}
