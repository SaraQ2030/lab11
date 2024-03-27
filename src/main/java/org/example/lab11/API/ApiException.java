package org.example.lab11.API;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ApiException extends RuntimeException{
    public ApiException (String message){
        super(message);
    }
}
