package br.com.lGabrielDev.projetoClientes.clientes.DTOs;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import br.com.lGabrielDev.projetoClientes.clientes.Cliente;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;
import java.time.LocalDateTime;

// O objetivo aqui é garantir que o contructor consiga transformar um Cliente em ClienteFullDto
public class ClienteFullDtoTest {
  
    // ============== constructors ==============
    @Test
    public void itShouldCreateAClienteFullDtoSuccessfully(){

        Endereco endereco = new Endereco();
        endereco.setId(1L);

        Cliente cliente = new Cliente();
        cliente.setId(1l);
        cliente.setNome("goku");
        cliente.setDataDeNascimento(LocalDate.of(1990, 1, 1));
        cliente.setCpf("12345678912");
        cliente.setEmail("goku@gmail.com");
        cliente.setTelefone("912349874");
        cliente.setEnderecoCompleto(endereco);
        //act
        ClienteFullDto clienteFullDto = new ClienteFullDto(cliente);
        //assert
        Assertions.assertThat(clienteFullDto.getId()).isEqualTo(cliente.getId());
        Assertions.assertThat(clienteFullDto.getNome()).isEqualTo(cliente.getNome());
        Assertions.assertThat(clienteFullDto.getCpf()).isEqualTo(cliente.getCpf());
        Assertions.assertThat(clienteFullDto.getEmail()).isEqualTo(cliente.getEmail());
        Assertions.assertThat(clienteFullDto.getTelefone()).isEqualTo(cliente.getTelefone());
        Assertions.assertThat(clienteFullDto.getEnderecoCompleto().getId()).isEqualTo(cliente.getEnderecoCompleto().getId());
    }

    // ============== getters and setter -> dataDeNascimento ==============
    @Test
    public void getDataDeNascimentoShouldReturnAString(){
        ClienteFullDto clienteFullDto = new ClienteFullDto();
        clienteFullDto.setDataDeNascimento(LocalDate.of(1990,4,2));

        //act
        String dataDeNascimentoFormatada = clienteFullDto.getDataDeNascimento();
        //assert
        Assertions.assertThat(dataDeNascimentoFormatada).isEqualTo("1990-04-02");
    }

    //Nao eh possivel converter null para String. Por isso, lancamos uma Exception
    @Test
    public void getDataDeNascimentoShouldThrowAnException(){
        ClienteFullDto clienteFullDto = new ClienteFullDto();
        //act
        Assertions.assertThatThrownBy(() -> clienteFullDto.getDataDeNascimento())
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    // ============== getters and setter -> createdAt ==============
    @Test
    public void getCreatedAtShouldReturnAString(){
        ClienteFullDto clienteFullDto = new ClienteFullDto();
        clienteFullDto.setCreatedAt(LocalDateTime.of(1990,4,2,20,0,5));
        //act
        String createdAt = clienteFullDto.getCreatedAt();
        //assert
        Assertions.assertThat(createdAt).isEqualTo("1990-04-02  08:00:05 PM");
    }

    @Test
    public void createdAtShouldThrowAnException(){
        ClienteFullDto clienteFullDto = new ClienteFullDto();
        //act
        Assertions.assertThatThrownBy(() -> clienteFullDto.getCreatedAt())
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    // ============== getters and setter -> lastUpdate ==============
    @Test
    public void getLastUpdateShouldReturnAString(){
        ClienteFullDto clienteFullDto = new ClienteFullDto();
        clienteFullDto.setLastUpdate(LocalDateTime.of(1990,4,2,20,0,5));

        //act
        String lastUpdate = clienteFullDto.getLastUpdate();
        //assert
        Assertions.assertThat(lastUpdate).isEqualTo("1990-04-02  08:00:05 PM");
    }

    @Test
    public void lastUpdateShouldThrowAnException(){
        ClienteFullDto clienteFullDto = new ClienteFullDto();
        //act
        Assertions.assertThatThrownBy(() -> clienteFullDto.getLastUpdate())
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }
}