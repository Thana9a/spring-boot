package com.example.autoconfiguration.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class ClubsDto {
    private Long id;
    private String title;
    private String description;
    private String email;

    @UpdateTimestamp
    private LocalDateTime UpdatedOn;
    @CreationTimestamp
    private LocalDateTime CreatedOn;
}
