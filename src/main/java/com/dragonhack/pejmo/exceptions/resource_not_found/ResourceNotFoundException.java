package com.dragonhack.pejmo.exceptions.resource_not_found;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
