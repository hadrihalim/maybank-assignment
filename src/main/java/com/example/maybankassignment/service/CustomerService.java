package com.example.maybankassignment.service;

import com.example.maybankassignment.dto.CustomerRequest;
import com.example.maybankassignment.dto.CustomerResponse;
import com.example.maybankassignment.dto.PagedResponse;
import com.example.maybankassignment.entity.Customer;
import com.example.maybankassignment.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerResponse create(CustomerRequest req) {
        if (customerRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Customer saved = customerRepository.save(Customer.builder()
                .name(req.getName())
                .email(req.getEmail())
                .build());

        return toResponse(saved);
    }

    @Transactional
    public CustomerResponse update(Long id, CustomerRequest req) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found: " + id));

        customer.setName(req.getName());
        customer.setEmail(req.getEmail());

        Customer saved = customerRepository.save(customer);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public CustomerResponse getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found: " + id));
        return toResponse(customer);
    }

    @Transactional(readOnly = true)
    public PagedResponse<CustomerResponse> getPaged(int page) {
        int size = 10; // requirement: each page 10 records
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        Page<Customer> result = customerRepository.findAll(pageable);

        return PagedResponse.<CustomerResponse>builder()
                .items(result.getContent().stream().map(this::toResponse).collect(Collectors.toList()))
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();
    }

    private CustomerResponse toResponse(Customer c) {
        return CustomerResponse.builder()
                .id(c.getId())
                .name(c.getName())
                .email(c.getEmail())
                .createdAt(c.getCreatedAt())
                .build();
    }
}
