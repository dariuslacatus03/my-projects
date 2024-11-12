package com.exam.exam.init;

import com.exam.exam.model.Property;
import com.exam.exam.model.User;
import com.exam.exam.service.PropertyService;
import com.exam.exam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ApplicationStartRunner implements CommandLineRunner {
    private final UserService userService;
    private final PropertyService propertyService;

    @Override
    public void run(String ...args) throws Exception {
        User newUser1 = new User("Dariuselu", "What is your real name?", "Darius");
        this.userService.addUser(newUser1);
        User newUser2 = new User("contiumario", "What is the name of your dog?", "Max");
        this.userService.addUser(newUser2);

        Property newProperty1 = new Property("address1", "description1");
        this.propertyService.addProperty(newProperty1);
        Property newProperty2 = new Property("address2", "description2");
        this.propertyService.addProperty(newProperty2);
        Property newProperty3 = new Property("address3", "description3");
        this.propertyService.addProperty(newProperty3);
        Property newProperty4 = new Property("address4", "description4");
        this.propertyService.addProperty(newProperty4);
        Property newProperty5 = new Property("address5", "description5");
        this.propertyService.addProperty(newProperty5);
    }
}
