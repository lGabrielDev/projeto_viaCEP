package br.com.lGabrielDev.projetoClientes.clientes.exceptions.dataDeNascimento;

public class ClienteEhMenorDeIdadeException extends RuntimeException{
    
    //constructor
    public ClienteEhMenorDeIdadeException(String errorMessage){
        super(errorMessage);
    }
}