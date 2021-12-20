package com.taniltekdemir.springboot.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date errorDate;
    private String message;
    private String datail;

    public ExceptionResponse() {
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatail() {
        return datail;
    }

    public void setDatail(String datail) {
        this.datail = datail;
    }

    public ExceptionResponse(Date errorDate, String message, String datail) {
        this.errorDate = errorDate;
        this.message = message;
        this.datail = datail;
    }
}
