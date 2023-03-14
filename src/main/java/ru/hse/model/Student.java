package ru.hse.model;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
}
