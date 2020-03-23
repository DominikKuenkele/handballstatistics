package com.kuenkele.handballstatistics;

public class DefaultHttpResponse {
    private String name;
    private String message;

    public DefaultHttpResponse(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
