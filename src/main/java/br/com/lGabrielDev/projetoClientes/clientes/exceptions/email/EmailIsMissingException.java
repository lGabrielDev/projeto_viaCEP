package br.com.lGabrielDev.projetoClientes.clientes.exceptions.email;

public class EmailIsMissingException extends RuntimeException{
    
    //constructor
    public EmailIsMissingException(String errorMessage){
        super(errorMessage);
    }
}