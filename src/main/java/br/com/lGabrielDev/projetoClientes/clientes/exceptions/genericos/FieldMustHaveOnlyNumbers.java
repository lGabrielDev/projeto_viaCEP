package br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos;

public class FieldMustHaveOnlyNumbers extends RuntimeException{
    
    //constructor
    public FieldMustHaveOnlyNumbers(String errorMessage){
        super(errorMessage);
    }
}