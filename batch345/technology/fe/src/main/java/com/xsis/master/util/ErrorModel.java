package com.xsis.master.util;

public class ErrorModel {
    private String timestamp;
    private String status;
    private String error;
    private String message;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorModel{" +
                "timestamp=" + timestamp +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
