package com.example.maybankassignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalPostDto {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
