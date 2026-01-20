package com.example.maybankassignment.controller;

import com.example.maybankassignment.dto.ExternalPostDto;
import com.example.maybankassignment.service.ExternalProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external")
@RequiredArgsConstructor
public class ExternalProxyController {

    private final ExternalProxyService externalProxyService;

    @GetMapping("/posts/{id}")
    public ResponseEntity<ExternalPostDto> proxyPost(@PathVariable Long id) {
        return ResponseEntity.ok(externalProxyService.getPost(id));
    }
}
