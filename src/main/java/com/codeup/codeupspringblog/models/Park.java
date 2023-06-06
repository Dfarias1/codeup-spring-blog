package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Create a constructor that accepts all fields
@AllArgsConstructor
// Create an empty constructor
@NoArgsConstructor
// Create all getter methods for each field
@Getter
// Create all setter methods for each field
@Setter
@Entity
@Table (name="parks")
public class Park {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 75)
    private String name;

    @Column(nullable = false, length = 20)
    private String location;

    public Park(String banffNationalPark, String banff) {
    }
}