package com.xsis.bc345.fe.models;

import java.util.List;

public class PagingView {
    private List<?> content;
    private boolean empty;
    private boolean first;
    private boolean last;
    private int number;
    private int totalPages;
    private long totalElements;
    private int size;
    
    public List<?> getContent() {
        return content;
    }
    public void setContent(List<?> content) {
        this.content = content;
    }
    public boolean isEmpty() {
        return empty;
    }
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
    public boolean isFirst() {
        return first;
    }
    public void setFirst(boolean first) {
        this.first = first;
    }
    public boolean isLast() {
        return last;
    }
    public void setLast(boolean last) {
        this.last = last;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public long getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    
}
