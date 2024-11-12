package com.exam.exam.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
@Table(name = "Property")
public class Property {
    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    private String address;

    @NonNull
    private String description;

    @ManyToMany(mappedBy = "properties")
    private Set<User> users = new HashSet<>();
}
