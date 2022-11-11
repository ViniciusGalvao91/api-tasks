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

	public List<Task> findAllTasks() {

		return taskRepository.findAll();
	}

	public Task findTaskById(Integer id) {
		Optional<Task> objTask = taskRepository.findById(id);
		return objTask.get();
	}

	public Task insertTask(Task objTask) {
		return taskRepository.save(objTask);
	}

	public Task changeTask(Task objTask) {

		Task temp = new Task();

		// taskRepository.findById(objTask.getId();
		return temp;
	}

	public void deleteTask(Integer id) {
		taskRepository.deleteById(id);
	}
}
