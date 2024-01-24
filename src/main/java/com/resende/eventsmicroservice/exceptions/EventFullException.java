package com.resende.eventsmicroservice.exceptions;

public class EventFullException extends RuntimeException{

    public EventFullException() {
        super("The event is already full");
    }

    public EventFullException(String message) {
        super(message);
    }

}
