package com.exam.exam.service.implementations;

import com.exam.exam.model.Property;
import com.exam.exam.repository.PropertyRepository;
import com.exam.exam.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;

    @Override
    public List<Property> getAllProperties() {
        return this.propertyRepository.findAll();
    }

    @Override
    public Property addProperty(Property property) {
        if (propertyRepository.findPropertyByAddress(property.getAddress()) == null)
        {
            this.propertyRepository.save(property);
            return property;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Boolean deleteProperty(Integer propertyId) {
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        if (propertyOptional.isEmpty())
            return Boolean.FALSE;
        propertyRepository.deleteById(propertyId);
        return Boolean.TRUE;
    }
}
