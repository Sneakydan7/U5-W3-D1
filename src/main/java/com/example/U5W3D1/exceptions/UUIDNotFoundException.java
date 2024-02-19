package com.example.U5W3D1.exceptions;

import java.util.UUID;

public class UUIDNotFoundException extends RuntimeException {
    public UUIDNotFoundException(UUID id) {
        super("Employee with  " + id + " was not found");
    }
}