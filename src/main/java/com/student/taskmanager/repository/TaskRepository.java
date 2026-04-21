package com.student.taskmanager.repository;

import com.student.taskmanager.model.Task;
import com.student.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOwner(User owner);
}