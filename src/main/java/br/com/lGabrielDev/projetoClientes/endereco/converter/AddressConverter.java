package br.com.lGabrielDev.projetoClientes.endereco.converter;

import org.springframework.stereotype.Component;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;
import br.com.lGabrielDev.projetoClientes.endereco.DTOs.EnderecoFullDto;

@Component
public abstract class AddressConverter {
    
    //converte um "Endereço" em um "EnderecoFullDto"
    public static EnderecoFullDto converter(Endereco Endereco){
        return new EnderecoFullDto(Endereco);
    }
}