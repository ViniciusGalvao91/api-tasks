package com.api.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tasks.entitys.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
