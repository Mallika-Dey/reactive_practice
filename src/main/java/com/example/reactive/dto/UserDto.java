package com.example.reactive.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name;
    private Integer age;
}
