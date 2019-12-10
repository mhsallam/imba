package com.mhsallam.imba.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ErrorDetails {
    private HttpStatus error;
    private String message;
    private String uri;
    private Map<String, String> attributes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    public ErrorDetails() {
        this.timestamp = new Date();
        this.attributes = new HashMap<String, String>();
    }

    public ErrorDetails(String message, String uri, HttpStatus error) {
        this();
        this.message = message;
        this.uri = uri;
        this.error = error;
        this.attributes = new HashMap<String, String>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getError() {
        return error;
    }

    public void setError(HttpStatus error) {
        this.error = error;
    }

    public int getStatus() {
        return error.value();
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
