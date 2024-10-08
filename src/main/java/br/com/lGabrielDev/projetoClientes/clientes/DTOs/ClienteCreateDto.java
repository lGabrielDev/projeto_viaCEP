package br.com.lGabrielDev.projetoClientes.clientes.DTOs;

import java.time.LocalDate;

public class ClienteCreateDto {
    
    //attributes
    private String nome;
    private LocalDate dataDeNascimento;
    private String cpf;
    private String email;
    private String telefone;
    public String cep;
    public Integer numeroCasa;

    //constructors
    public ClienteCreateDto(){}

    public String getNome() {
        return nome;
    }

    //getters and setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(Integer numeroCasa) {
        this.numeroCasa = numeroCasa;
    }
}