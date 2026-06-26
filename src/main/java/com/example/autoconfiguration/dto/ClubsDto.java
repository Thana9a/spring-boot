package com.example.autoconfiguration.dto;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class ClubsDto {
    private Long id;

    @NotNull(message = "title is required")
    private String title;
    @NotEmpty(message = "description is required")
    private String description;
    @NotEmpty(message = "email is required")
    private String email;

    @UpdateTimestamp
    private LocalDateTime UpdatedOn;
    @CreationTimestamp
    private LocalDateTime CreatedOn;
}
