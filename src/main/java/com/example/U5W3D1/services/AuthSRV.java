package com.example.U5W3D1.services;

import com.example.U5W3D1.entities.Employee;
import com.example.U5W3D1.exceptions.BadRequestException;
import com.example.U5W3D1.payloads.EmployeeDTO;
import com.example.U5W3D1.payloads.LoginDTO;
import com.example.U5W3D1.repositories.EmployeeDAO;
import com.example.U5W3D1.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthSRV {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private EmployeeSRV employeeSRV;
    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private EmployeeDAO employeeDAO;

    public String authUserAndGenerateToken(LoginDTO payload) {
        Employee employee = employeeSRV.findEmployeeByEmail(payload.email());
        if (bcrypt.matches(payload.password(), employee.getPassword())) {
            return jwtTools.createToken(employee);
        } else {
            throw new RuntimeException("Error logging the user in: incorrect password");
        }

    }


    public Employee saveEmployee(EmployeeDTO payload) {
        employeeDAO.findByEmail(payload.getEmail()).ifPresent(user -> {
            throw new BadRequestException("Email " + user.getEmail() + " already in use!");
        });

        Employee newEmployee = new Employee(payload.getUsername(), payload.getName(),
                payload.getSurname(), payload.getEmail(), bcrypt.encode(payload.getPassword()));

        return employeeDAO.save(newEmployee);
    }
}
