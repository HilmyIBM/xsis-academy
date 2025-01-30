package com.xsis.bc345.frontend.models;

public class Pageable {
  int pageNumber;
  int pageSize;
  int offset;
  boolean paged;
  boolean unpaged;
  public int getPageNumber() {
    return pageNumber;
  }
  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }
  public int getPageSize() {
    return pageSize;
  }
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  public int getOffset() {
    return offset;
  }
  public void setOffset(int offset) {
    this.offset = offset;
  }
  public boolean isPaged() {
    return paged;
  }
  public void setPaged(boolean paged) {
    this.paged = paged;
  }
  public boolean isUnpaged() {
    return unpaged;
  }
  public void setUnpaged(boolean unpaged) {
    this.unpaged = unpaged;
  }

  
}
