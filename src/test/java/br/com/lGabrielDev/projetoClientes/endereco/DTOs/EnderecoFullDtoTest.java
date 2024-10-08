package br.com.lGabrielDev.projetoClientes.endereco.DTOs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;

public class EnderecoFullDtoTest {
    
    @Test
    public void constructorShouldCopyAttributesSuccessfully(){
        //arrange
        Endereco endereco = new Endereco();
        endereco.setId(1l);
        endereco.setBairro("bairro teste");
        //act
        EnderecoFullDto fullDto = new EnderecoFullDto(endereco);
        //assert
        Assertions.assertThat(fullDto.getId()).isEqualTo(1l);
        Assertions.assertThat(fullDto.getBairro()).isEqualTo("bairro teste");
    }
}