package com.exam.exam.controller;

import com.exam.exam.authentication.Requests.LoginRequest;
import com.exam.exam.dtos.PropertyDTO;
import com.exam.exam.model.Property;
import com.exam.exam.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/property")
@AllArgsConstructor
@CrossOrigin
public class PropertyController {
    private PropertyService propertyService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProperties() {
        List<Property> allProperties = propertyService.getAllProperties();
        return ResponseEntity.ok(PropertyDTO.convertListToDTO(allProperties));
    }

    @DeleteMapping(path = "/{propertyId}/delete")
    public ResponseEntity<?> deleteProperty(@PathVariable Integer propertyId) {
        return ResponseEntity.ok(propertyService.deleteProperty(propertyId));
    }
}
