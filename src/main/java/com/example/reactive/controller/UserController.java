package com.example.reactive.controller;

import com.example.reactive.dto.UserDto;
import com.example.reactive.entity.User;
import com.example.reactive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get/age/{age}")
    public Flux<User> getAllUserByAge(@PathVariable Integer age) {
        return userService.getUserByAge(age);
    }

    @GetMapping("/get/user/age/{age}")
    public Flux<UserDto> getAllUserDtoByAge(@PathVariable Integer age) {
        return userService.getFilterUser(age);
    }

    @PostMapping("/create")
    public Mono<User> createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/update/{id}")
    public Mono<User> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
        return userService.updateUser(userDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
