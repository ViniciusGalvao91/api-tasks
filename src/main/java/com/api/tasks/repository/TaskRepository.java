package com.api.tasks.repository;

import org.springframework.data.repository.CrudRepository;

import com.api.tasks.entitys.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}
