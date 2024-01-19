package com.example.reactive.service.impl;

import com.example.reactive.dto.UserDto;
import com.example.reactive.entity.User;
import com.example.reactive.exception.NotFoundException;
import com.example.reactive.repositories.UserRepository;
import com.example.reactive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Flux<User> getUserByAge(Integer age) {
//        return userRepository
//                .findByAge(age)
//                .switchIfEmpty(Mono.error(new NotFoundException("not exists")));

        return userRepository.findByAge(age)
                .onErrorResume(ex -> {
                    logger.error(ex, ex);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new NotFoundException("not exists")));

    }

    @Transactional
    @Override
    public Mono<User> createUser(UserDto userDto) {
        return userRepository.save(User
                        .builder()
                        .name(userDto.getName())
                        .age(userDto.getAge())
                        .build())
                .doOnSuccess(
                        user ->
                                logger.info("Student with ID {} created successfully " + user))
                .onErrorMap(throwable -> {
                    logger.error("Error creating user", throwable);
                    return new RuntimeException("Failed to create user", throwable);
                });
    }

    @Override
    public Mono<User> updateUser(UserDto userDto, Integer id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("not found")))
                .flatMap(user ->
                        userRepository.save(User
                                .builder()
                                .id(id)
                                .name(userDto.getName())
                                .age(userDto.getAge())
                                .build()));
    }

    @Override
    public Mono<Void> deleteUser(Integer id) {
        return userRepository.deleteById(id)
                .doOnSuccess(
                        user -> logger.info("successfully deleted " + id)
                )
                .onErrorResume(ex -> {
                            logger.error("failed " + ex);
                            return Mono.empty();
                        }
                );
    }

}
