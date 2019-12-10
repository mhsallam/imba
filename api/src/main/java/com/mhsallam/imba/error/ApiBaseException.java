package com.mhsallam.imba.error;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public abstract class ApiBaseException extends RuntimeException {
    private HashMap<String, String> attributes;
    public ApiBaseException(String message) {

        super(message);
        this.attributes = new HashMap<String, String>();
    }

    public abstract HttpStatus getStatusCode();

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    public void putAttribute(String key, String value) {
        this.attributes.put(key, value);
    }

}
