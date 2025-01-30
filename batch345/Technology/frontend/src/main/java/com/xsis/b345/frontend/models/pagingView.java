package com.xsis.b345.frontend.models;

import java.util.List;

public class pagingView {
    private List<?> content;
    private boolean empty;
    private boolean first;
    private boolean last;
    private int number;
    private int totalPages;
    private long totalElements;
    private int size;

    /**
     * @return List<?> return the content
     */
    public List<?> getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(List<?> content) {
        this.content = content;
    }

    /**
     * @return boolean return the empty
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * @param empty the empty to set
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    /**
     * @return boolean return the first
     */
    public boolean isFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(boolean first) {
        this.first = first;
    }

    /**
     * @return boolean return the last
     */
    public boolean isLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(boolean last) {
        this.last = last;
    }

    /**
     * @return int return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return int return the totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages the totalPages to set
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return long return the totalElements
     */
    public long getTotalElements() {
        return totalElements;
    }

    /**
     * @param totalElements the totalElements to set
     */
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    /**
     * @return int return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

}
