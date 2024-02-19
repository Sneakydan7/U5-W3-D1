package com.example.U5W3D1.controllers;

import com.example.U5W3D1.entities.Employee;
import com.example.U5W3D1.payloads.EmployeeDTO;
import com.example.U5W3D1.payloads.LoginDTO;
import com.example.U5W3D1.payloads.LoginResponseDTO;
import com.example.U5W3D1.services.AuthSRV;
import com.example.U5W3D1.services.EmployeeSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCTRL {
    @Autowired
    private EmployeeSRV employeeSRV;
    @Autowired
    private AuthSRV authSRV;

    @PostMapping("/login")
    private LoginResponseDTO login(@RequestBody LoginDTO payload) {
        return new LoginResponseDTO(authSRV.authUserAndGenerateToken(payload));

    }

    @PostMapping("/register")
    private Employee register(@RequestBody EmployeeDTO payload) {
        return this.employeeSRV.saveEmployee(payload);
    }

}
