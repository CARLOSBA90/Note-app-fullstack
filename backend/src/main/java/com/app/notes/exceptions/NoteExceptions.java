package com.app.notes.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class NoteExceptions extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;

    public NoteExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}