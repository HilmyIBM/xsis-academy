package com.xsis.bc345.frontend.models;

import java.util.List;

public class Pagination {
  private List<?> content;  
  private Pageable pageable;
  private boolean empty;
  private boolean first;
  private boolean last;
  private int number;
  private int numberOfElements;
  private int size;
  private Sort sort;
  private int totalElements;
  private int totalPages;
  public List<?> getContent() {
    return content;
  }
  public void setContent(List<?> content) {
    this.content = content;
  }
  public Pageable getPageable() {
    return pageable;
  }
  public void setPageable(Pageable pageable) {
    this.pageable = pageable;
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
  public int getNumberOfElements() {
    return numberOfElements;
  }
  public void setNumberOfElements(int numberOfElements) {
    this.numberOfElements = numberOfElements;
  }
  public int getSize() {
    return size;
  }
  public void setSize(int size) {
    this.size = size;
  }
  public Sort getSort() {
    return sort;
  }
  public void setSort(Sort sort) {
    this.sort = sort;
  }
  public int getTotalElements() {
    return totalElements;
  }
  public void setTotalElements(int totalElements) {
    this.totalElements = totalElements;
  }
  public int getTotalPages() {
    return totalPages;
  }
  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  
}