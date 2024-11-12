package com.exam.exam.authentication.Responses;

import com.exam.exam.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginResponse {
    private String name;
    private String secretQuestion;
}
