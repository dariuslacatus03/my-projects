package com.exam.exam.controller;

import com.exam.exam.dtos.PropertyDTO;
import com.exam.exam.model.Property;
import com.exam.exam.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @PostMapping(path = "/{username}/add-property")
    public ResponseEntity<?> addPropertyToUser(@PathVariable String username, @RequestBody Property propertyToAdd)
    {
        return ResponseEntity.ok(PropertyDTO.convertToDTO(userService.addPropertyToUser(username, propertyToAdd)));
    }
}
