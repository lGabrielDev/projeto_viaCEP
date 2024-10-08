package br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos;

public class FieldCannotBeNullException extends RuntimeException {
   
    //constructor
    public FieldCannotBeNullException(String errorMessage){
        super(errorMessage);
    }
}