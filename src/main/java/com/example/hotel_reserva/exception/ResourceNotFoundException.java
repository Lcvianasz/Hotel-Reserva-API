package com.example.hotel_reserva.exception;

public class ResourceNotFoundException extends BusinessException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
