package com.example.U5W3D1.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignDTO {
    //@NotEmpty(message = "Employee email is required")
    String email;
}
