package com.example.autoconfiguration.actuator;

public class RestFull {
    private final Long id;
    private final String content;

    public RestFull(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
