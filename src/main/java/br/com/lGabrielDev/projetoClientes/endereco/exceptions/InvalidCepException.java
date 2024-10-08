package br.com.lGabrielDev.projetoClientes.endereco.exceptions;

public class InvalidCepException extends RuntimeException {
    
    //constructor
    public InvalidCepException(String errorMessage){
        super(errorMessage);
    }
}