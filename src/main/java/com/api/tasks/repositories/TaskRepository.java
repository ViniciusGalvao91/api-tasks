package com.api.tasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.tasks.models.TaskModel;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
}