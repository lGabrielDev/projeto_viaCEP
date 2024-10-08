package br.com.lGabrielDev.projetoClientes.clientes.exceptions.telefone;

public class TelefonedoesNotContainNine extends RuntimeException{
    
    //constructor
    public TelefonedoesNotContainNine(String errorMessage){
        super(errorMessage);
    }
}