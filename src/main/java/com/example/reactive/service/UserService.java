package com.example.reactive.service;

import com.example.reactive.dto.UserDto;
import com.example.reactive.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Log logger = LogFactory.getLog(UserService.class);

    Flux<User> getUserByAge(Integer age);

    Mono<User> createUser(UserDto userDto);

    Mono<User> updateUser(UserDto userDto, Integer id);

    Mono<Void> deleteUser(Integer id);

    Flux<UserDto> getFilterUser(Integer age);

}
