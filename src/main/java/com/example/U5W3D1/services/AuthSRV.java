package com.example.U5W3D1.services;

import com.example.U5W3D1.entities.Employee;
import com.example.U5W3D1.payloads.LoginDTO;
import com.example.U5W3D1.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthSRV {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private EmployeeSRV employeeSRV;


    public String authUserAndGenerateToken(LoginDTO payload) {
        Employee employee = employeeSRV.findEmployeeByEmail(payload.email());
        if (employee.getPassword().equals(payload.password())) {
            return jwtTools.createToken(employee);
        } else {
            throw new RuntimeException("Error logging the user in: incorrect password");
        }

    }
}
