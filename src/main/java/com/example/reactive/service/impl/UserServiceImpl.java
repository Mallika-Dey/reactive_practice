package com.example.reactive.service.impl;

import com.example.reactive.dto.UserDto;
import com.example.reactive.entity.User;
import com.example.reactive.repositories.UserRepository;
import com.example.reactive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Flux<User> getUserByAge(Integer age) {
        return userRepository.findByAge(age);
    }

    @Override
    public Mono<User> createUser(UserDto userDto) {
        return  userRepository.save(User
                .builder()
                .name(userDto.getName())
                .age(userDto.getAge())
                .build());
    }
}
