package com.example.U5W3D1.controllers;

import com.example.U5W3D1.entities.Employee;
import com.example.U5W3D1.payloads.EmployeeDTO;
import com.example.U5W3D1.services.AuthSRV;
import com.example.U5W3D1.services.EmployeeSRV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeCTRL {
    @Autowired
    private EmployeeSRV employeeSRV;
    @Autowired
    private AuthSRV authSRV;

    @GetMapping
    public Page<Employee> getEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String orderBy) {
        return this.employeeSRV.getEmployees(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable UUID id) {
        return this.employeeSRV.getEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody EmployeeDTO newEmployee) {
        return this.authSRV.saveEmployee(newEmployee);

    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable UUID id, @RequestBody @Validated EmployeeDTO updatedEmployee, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new RuntimeException();
        } else {
            return this.employeeSRV.updateEmployeeById(updatedEmployee, id);
        }


    }

    @GetMapping("/me")
    public Employee getProfile(@AuthenticationPrincipal Employee currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public Employee getMeAndUpdate(@AuthenticationPrincipal Employee currentAuthenticatedUser, @RequestBody EmployeeDTO updatedUser) {
        return this.employeeSRV.updateEmployeeById(updatedUser, currentAuthenticatedUser.getId());
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getMeAndDelete(@AuthenticationPrincipal Employee currentAuthenticatedUser) {
        this.employeeSRV.getEmployeeById(currentAuthenticatedUser.getId());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable UUID id) {
        this.employeeSRV.deleteEmployee(id);
    }

    @PostMapping("/upload/{id}")
    public String uploadImageForEmployee(@RequestParam("image") MultipartFile image, @PathVariable UUID id) throws IOException {
        return this.employeeSRV.uploadImageForAuthor(image, id);
    }
}
