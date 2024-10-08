package br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos;

public class FieldIsNotUniqueException extends RuntimeException{

    //constructor
    public FieldIsNotUniqueException(String errorMessage){
        super(errorMessage);
    }
}