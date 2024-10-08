package br.com.lGabrielDev.projetoClientes.clientes.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErroPadrao {
    
    //attributes
    private LocalDateTime timestamp;
    private Integer status;
    private String errorMessage;

    //constructors
    public ErroPadrao(){}
    
    //getters and setters
    public String getTimestamp() {
        DateTimeFormatter formatoPadrao = DateTimeFormatter.ofPattern("yyyy-MM-dd  hh:mm:ss a"); //2024-09-26  05:40:20 PM
        return formatoPadrao.format(this.timestamp);
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}