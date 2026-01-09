package com.sisimpur.library.exceptions.types;

public class ResourceNotFoundException extends RuntimeException{
    private String message;
    public ResourceNotFoundException (Class<?> clazz, String message) {
        super(String.format(message, clazz.getSimpleName()));
    }
}
