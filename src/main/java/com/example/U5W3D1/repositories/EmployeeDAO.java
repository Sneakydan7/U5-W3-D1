package com.example.U5W3D1.repositories;

import com.example.U5W3D1.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, UUID> {

    boolean existsByEmail(String email);

    Optional<Employee> findByEmail(String email);

    Employee findByEmailAndIdNot(String email, UUID id);
}
