package br.com.lGabrielDev.projetoClientes.endereco.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;
import br.com.lGabrielDev.projetoClientes.endereco.DTOs.EnderecoFullDto;

public class AddressConverterTest {

    @Test
    public void itShouldConverterAEnderecoSuccessfully(){
        //arrange
        Endereco endereco = new Endereco();
        endereco.setId(1l);
        endereco.setBairro("bairro teste");
        //act
        EnderecoFullDto methodResult = AddressConverter.converter(endereco);
        //assert
        Assertions.assertThat(methodResult.getId()).isEqualTo(1l);
        Assertions.assertThat(methodResult.getBairro()).isEqualTo("bairro teste");
    }
}