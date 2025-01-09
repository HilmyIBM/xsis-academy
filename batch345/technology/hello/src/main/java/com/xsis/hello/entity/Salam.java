package com.xsis.hello.entity;

public class Salam {
    private final long id;
    private final String content;
    public Salam(long id, String content){
        super();
        this.id = id;
        this.content = content;
    }
    public long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
}
