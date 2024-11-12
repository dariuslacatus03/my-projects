package com.exam.exam.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@RequiredArgsConstructor
@Table(name = "Users")
public class User {
    @Id @GeneratedValue
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String secretQuestion;

    @NonNull
    private String secretAnswer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UserToProperties",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private Set<Property> properties;
}
