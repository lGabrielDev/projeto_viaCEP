package br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos;

public class FieldLengthIsIncorrect extends RuntimeException{
    
    //constructor
    public FieldLengthIsIncorrect(String errorMessage){
        super(errorMessage);
    }
}