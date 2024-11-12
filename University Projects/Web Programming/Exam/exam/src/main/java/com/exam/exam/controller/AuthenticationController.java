package com.exam.exam.controller;

import com.exam.exam.authentication.Requests.AnswerRequest;
import com.exam.exam.authentication.Requests.LoginRequest;
import com.exam.exam.authentication.Responses.ErrorResponse;
import com.exam.exam.authentication.Responses.LoginResponse;
import com.exam.exam.authentication.Responses.VerifyResponse;
import com.exam.exam.model.User;
import com.exam.exam.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try
        {
            String username = loginRequest.getUsername();
            User user = userService.checkNameExistence(username);
            if (user == null)
            {
                ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid username");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
            LoginResponse loginResponse = new LoginResponse(user.getName(), user.getSecretQuestion());
            return ResponseEntity.ok(loginResponse);
        }
        catch (Exception ex)
        {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @RequestMapping(value = "/verifySecretAnswer", method = RequestMethod.POST)
    public ResponseEntity<?> verifySecretAnswer(@RequestBody AnswerRequest answerRequest) {
        try {
            String name = answerRequest.getUsername();
            String answer = answerRequest.getAnswer();
            boolean isCorrect = userService.verifySecretAnswer(name, answer);
            if (isCorrect) {
                String token = "good_answer";
                VerifyResponse verifyResponse = new VerifyResponse("Success", token);
                return ResponseEntity.ok(verifyResponse);
            } else {
                ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Incorrect secret answer");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
