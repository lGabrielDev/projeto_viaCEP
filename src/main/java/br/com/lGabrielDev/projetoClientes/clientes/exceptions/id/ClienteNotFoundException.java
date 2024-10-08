package br.com.lGabrielDev.projetoClientes.clientes.exceptions.id;

public class ClienteNotFoundException extends RuntimeException{
    
    //constructor
    public ClienteNotFoundException(String errorMessage){
        super(errorMessage);
    }
}