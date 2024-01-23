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
        return userRepository
                .findByAge(age)
                .switchIfEmpty(Mono.error(new NotFoundException("not exists")));
//                .onErrorResume(throwable -> {
//                    logger.error(throwable, throwable);
//                    return Mono.just(new User());
//                });
    }

    @Transactional
    @Override
    public Mono<User> createUser(UserDto userDto) {
        if (userDto == null || userDto.getAge() == null || userDto.getName() == null) {
            throw new NullPointerException("user input can't be null");
        }

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

    @Override
    public Flux<UserDto> getFilterUser(Integer age) {
        return getUserByAge(age)
                .flatMap(this::userToDto)
                .switchIfEmpty(Mono.error(new NotFoundException("not exists")));
    }

    private Mono<UserDto> userToDto(User user) {
        return Mono.just(UserDto
                .builder()
                .name(user.getName())
                .age(user.getAge())
                .build());
    }

}
