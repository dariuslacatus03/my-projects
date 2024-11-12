package com.exam.exam.dtos;

import com.exam.exam.model.Property;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PropertyDTO {
    private Integer id;
    private String address;
    private String description;

    public static PropertyDTO convertToDTO(Property propertyToConvert){
        return new PropertyDTO(propertyToConvert.getId(), propertyToConvert.getAddress(), propertyToConvert.getDescription());
    }

    public static List<PropertyDTO> convertListToDTO(List<Property> propertiesToConvert){
        List<PropertyDTO> allPropertiesDTO = new ArrayList<>();
        for (Property property : propertiesToConvert)
        {
            allPropertiesDTO.add(PropertyDTO.convertToDTO(property));
        }
        return allPropertiesDTO;
    }
}
