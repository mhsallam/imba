package com.mhsallam.imba.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends  ApiBaseException {
    public ConflictException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
