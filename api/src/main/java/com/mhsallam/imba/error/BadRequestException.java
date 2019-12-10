package com.mhsallam.imba.error;

import org.springframework.http.HttpStatus;

public class BadRequestException extends  ApiBaseException {
    public BadRequestException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
