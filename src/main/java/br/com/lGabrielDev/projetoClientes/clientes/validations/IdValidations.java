package br.com.lGabrielDev.projetoClientes.clientes.validations;

import br.com.lGabrielDev.projetoClientes.clientes.ClienteRepository;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.id.ClienteNotFoundException;

public abstract class IdValidations {
 
    // verificar se o #ID do cliente existe
    public static Boolean clienteIdIsCorrect(Long clienteId, ClienteRepository clienteRepository){
       clienteRepository.findById(clienteId)
            .orElseThrow(() -> new ClienteNotFoundException(String.format("Cliente #%d não existe!", clienteId)));
        return true;
    }
}