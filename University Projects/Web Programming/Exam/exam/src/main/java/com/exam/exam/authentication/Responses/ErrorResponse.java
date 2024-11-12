package com.exam.exam.authentication.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter @Setter
public class ErrorResponse {
    private HttpStatus httpStatus;
    private String message;
}
