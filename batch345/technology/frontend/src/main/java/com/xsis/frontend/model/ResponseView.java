package com.xsis.frontend.model;

import java.util.List;

public class ResponseView {
    private List<?> content;
    private boolean empty;
    private boolean first;
    private boolean last;
    private int number;
    private int totalPages;
    private Long totalElements;
    private int size;


    public List<?> getContent() {
        return this.content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean getEmpty() {
        return this.empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isFirst() {
        return this.first;
    }

    public boolean getFirst() {
        return this.first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return this.last;
    }

    public boolean getLast() {
        return this.last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return this.totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }


}
