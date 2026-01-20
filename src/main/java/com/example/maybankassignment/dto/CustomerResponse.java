package com.example.maybankassignment.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
}
