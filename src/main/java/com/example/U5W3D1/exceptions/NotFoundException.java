package com.example.U5W3D1.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Device with  " + id + " was not found");
    }
}
