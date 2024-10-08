package br.com.lGabrielDev.projetoClientes.clientes.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import br.com.lGabrielDev.projetoClientes.clientes.Cliente;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.endereco.DTOs.EnderecoFullDto;
import br.com.lGabrielDev.projetoClientes.endereco.converter.AddressConverter;

public class ClienteFullDto {
    
    //attributes
    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;
    private String cpf;
    private String email;
    private String telefone;
    private EnderecoFullDto enderecoCompleto;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

    //constructors
    public ClienteFullDto(){}

    public ClienteFullDto(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.dataDeNascimento = cliente.getDataDeNascimento();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
        this.enderecoCompleto = AddressConverter.converter(cliente.getEnderecoCompleto());
        this.createdAt = cliente.getCreatedAt();
        this.lastUpdate = cliente.getLastUpdate();
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDeNascimento() {
        if(this.dataDeNascimento == null){
            throw new FieldCannotBeNullException("dataDeNascimento nao pode ser null. getDataDeNascimento() tenta retornar uma String, mas não é possivel porque dataDeNascimento é null. Nao é possivel transformar uma data em uma String se a data é null.");
        }
        DateTimeFormatter formatoPadrao = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //2024-09-26
        return formatoPadrao.format(this.dataDeNascimento);
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCreatedAt() {
        if(this.createdAt == null){
            throw new FieldCannotBeNullException("createdAt nao pode ser null. getCreatedAt() tenta retornar uma String, mas não é possivel porque createdAt é null. Nao é possivel transformar uma data em uma String se a data é null.");
        }
        DateTimeFormatter formatoPadrao = DateTimeFormatter.ofPattern("yyyy-MM-dd  hh:mm:ss a"); //2024-09-26  05:40:20 PM
        return formatoPadrao.format(this.createdAt);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastUpdate() {
        if(this.lastUpdate == null){
            throw new FieldCannotBeNullException("lastUpdate nao pode ser null. getLastUpdate() tenta retornar uma String, mas não é possivel porque lastUpdate é null. Nao é possivel transformar uma data em uma String se a data é null.");
        }
        DateTimeFormatter formatoPadrao = DateTimeFormatter.ofPattern("yyyy-MM-dd  hh:mm:ss a"); //2024-09-26  05:40:20 PM
        return formatoPadrao.format(this.lastUpdate);
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public EnderecoFullDto getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(EnderecoFullDto enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }
}