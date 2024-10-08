package br.com.lGabrielDev.projetoClientes.clientes.validations;

import br.com.lGabrielDev.projetoClientes.clientes.ClienteRepository;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteCreateDto;

public abstract class ClienteValidations {

    public static Boolean allValidationsAreCorrect(ClienteCreateDto clienteCreateDto, ClienteRepository clienteRepository){
        NameValidations.allValidationsAreCorrect(clienteCreateDto.getNome());
        DataDeNascimentoValidations.allValidationsAreCorrect(clienteCreateDto.getDataDeNascimento());
        CpfValidations.allValidationsAreCorrect(clienteCreateDto.getCpf(), clienteRepository);
        EmailValidations.allValidationsAreCorrect(clienteCreateDto.getEmail(), clienteRepository);
        TelefoneValidations.allValidationsAreCorrect(clienteCreateDto.getTelefone(), clienteRepository);
        NumeroCasaValidations.allValidationsAreCorrect(clienteCreateDto.getNumeroCasa());
        return true;
    }

    public static Boolean allValidationsAreCorrect(ClienteCreateDto clienteCreateDto, ClienteRepository clienteRepository, Long clientId){
        IdValidations.clienteIdIsCorrect(clientId, clienteRepository);
        NameValidations.allValidationsAreCorrect(clienteCreateDto.getNome(), true);
        DataDeNascimentoValidations.allValidationsAreCorrect(clienteCreateDto.getDataDeNascimento(), true);
        CpfValidations.allValidationsAreCorrect(clienteCreateDto.getCpf(), clienteRepository, true);
        EmailValidations.allValidationsAreCorrect(clienteCreateDto.getEmail(), clienteRepository, true);
        TelefoneValidations.allValidationsAreCorrect(clienteCreateDto.getTelefone(), clienteRepository, true);
        NumeroCasaValidations.allValidationsAreCorrect(clienteCreateDto.getNumeroCasa(), true);
        return true;
    }
}