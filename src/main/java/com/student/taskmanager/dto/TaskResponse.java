package com.student.taskmanager.dto;

import java.time.LocalDateTime;

/**
 * DTO for sending task details back to the client securely.
 */
public record TaskResponse(
        Long id,
        String title,
        String description,
        LocalDateTime createdAt,
        String ownerUsername
) {}