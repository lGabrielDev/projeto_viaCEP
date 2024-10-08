package br.com.lGabrielDev.projetoClientes.clientes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteCreateDto;

public class ClienteTest {

    //testando os contructors
    @Test
    public void itShouldInitializeTimestampsWhenDefaultConstructorIsCalled(){
        //arrange
        LocalDateTime today = LocalDateTime.now();
        //act
        Cliente cliente = new Cliente();
        //assert
        Assertions.assertThat(cliente.getCreatedAt().getYear()).isEqualTo(today.getYear());
        Assertions.assertThat(cliente.getCreatedAt().getMonthValue()).isEqualTo(today.getMonthValue());
        Assertions.assertThat(cliente.getCreatedAt().getDayOfMonth()).isEqualTo(today.getDayOfMonth());
    }

    @Test
    public void itShouldCopyFieldsFromClienteCreateDtoWhenConstructorIsCalled(){
        //arrange
        ClienteCreateDto clienteCreateDto = new ClienteCreateDto();
        clienteCreateDto.setNome("goku");
        clienteCreateDto.setDataDeNascimento(LocalDate.now());
        clienteCreateDto.setCpf("123");
        clienteCreateDto.setEmail("goku@gmail.com");
        clienteCreateDto.setTelefone("123");
        //act
        Cliente cliente = new Cliente(clienteCreateDto);
        //assert
        Assertions.assertThat(cliente.getNome()).isEqualTo("goku");
        Assertions.assertThat(cliente.getDataDeNascimento()).isEqualTo(LocalDate.now());
        Assertions.assertThat(cliente.getCpf()).isEqualTo("123");
        Assertions.assertThat(cliente.getEmail()).isEqualTo("goku@gmail.com");
        Assertions.assertThat(cliente.getTelefone()).isEqualTo("123");
    }

    @Test
    public void itShouldCopyFieldsFromClienteWhenConstructorIsCalled(){
        //arrange
        Cliente clienteAntigo = new Cliente();
        clienteAntigo.setNome("goku");
        clienteAntigo.setDataDeNascimento(LocalDate.now());
        clienteAntigo.setCpf("123");
        clienteAntigo.setEmail("goku@gmail.com");
        clienteAntigo.setTelefone("123");
        //act
        Cliente cliente = new Cliente(clienteAntigo);
        //assert
        Assertions.assertThat(cliente.getNome()).isEqualTo("goku");
        Assertions.assertThat(cliente.getDataDeNascimento()).isEqualTo(LocalDate.now());
        Assertions.assertThat(cliente.getCpf()).isEqualTo("123");
        Assertions.assertThat(cliente.getEmail()).isEqualTo("goku@gmail.com");
        Assertions.assertThat(cliente.getTelefone()).isEqualTo("123");
    }
}