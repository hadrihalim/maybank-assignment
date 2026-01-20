package com.example.maybankassignment.service;

import com.example.maybankassignment.dto.ExternalPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ExternalProxyService {

    private final WebClient webClient;

    public ExternalPostDto getPost(Long postId) {
        return webClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts/{id}", postId)
                .retrieve()
                .bodyToMono(ExternalPostDto.class)
                .block();
    }
}
