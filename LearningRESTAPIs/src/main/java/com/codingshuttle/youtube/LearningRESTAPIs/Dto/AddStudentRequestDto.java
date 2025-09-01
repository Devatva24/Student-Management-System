package com.codingshuttle.youtube.LearningRESTAPIs.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStudentRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min=3, max=30, message="Name should be of length 3 to 30 characters")
    private String name;

    @Email
    @NotBlank(message = "Email is required")
    private String email;
}
