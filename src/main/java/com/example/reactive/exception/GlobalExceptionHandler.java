//package com.example.reactive.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import reactor.core.publisher.Mono;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(NotFoundException.class)
//    public Mono<ResponseEntity<?>> handleCustomException(NotFoundException ex, WebRequest webRequest) {
//        return Mono.just(new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND));
//    }
//}
