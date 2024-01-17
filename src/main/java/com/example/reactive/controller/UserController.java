package com.example.reactive.controller;

import com.example.reactive.dto.UserDto;
import com.example.reactive.entity.User;
import com.example.reactive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get/age/{age}")
    public Flux<User> getAllUserByAge(Integer age) {
        return userService.getUserByAge(age);
    }

    @PostMapping("/create")
    public Mono<User> createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
}