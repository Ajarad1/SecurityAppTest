package com.student.taskmanager.dto;

/**
 * DTO for creating a new task.
 */
public record TaskRequest(String title, String description) {}