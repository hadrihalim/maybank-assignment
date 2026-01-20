package com.example.maybankassignment.controller;

import com.example.maybankassignment.dto.CustomerRequest;
import com.example.maybankassignment.dto.CustomerResponse;
import com.example.maybankassignment.dto.PagedResponse;
import com.example.maybankassignment.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id, @Valid @RequestBody CustomerRequest req) {
        return ResponseEntity.ok(customerService.update(id, req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    // Requirement: pagination, 10 records per page
    @GetMapping
    public ResponseEntity<PagedResponse<CustomerResponse>> getPaged(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(customerService.getPaged(page));
    }
}
