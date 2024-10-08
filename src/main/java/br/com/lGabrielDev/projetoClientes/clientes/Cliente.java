package br.com.lGabrielDev.projetoClientes.clientes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteCreateDto;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class Cliente {
    
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataDeNascimento;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String telefone;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdate;

    @OneToOne(targetEntity = Endereco.class)
    @JoinColumn(name = "enderecoId")
    private Endereco enderecoCompleto;

    //constructors
    public Cliente(){
        this.createdAt = LocalDateTime.now();
        this.lastUpdate = LocalDateTime.now();
    }

    public Cliente(ClienteCreateDto clienteCreateDto){
        this.nome = clienteCreateDto.getNome();
        this.dataDeNascimento = clienteCreateDto.getDataDeNascimento();
        this.cpf = clienteCreateDto.getCpf();
        this.email = clienteCreateDto.getEmail();
        this.telefone = clienteCreateDto.getTelefone();
        this.createdAt = LocalDateTime.now();
        this.lastUpdate = LocalDateTime.now();
    }

    public Cliente(Cliente clienteAntigo){
        this.id = clienteAntigo.getId();
        this.nome = clienteAntigo.getNome();
        this.dataDeNascimento = clienteAntigo.getDataDeNascimento();
        this.cpf = clienteAntigo.getCpf();
        this.email = clienteAntigo.getEmail();
        this.telefone = clienteAntigo.getTelefone();
        this.createdAt = clienteAntigo.getCreatedAt();
        this.lastUpdate = LocalDateTime.now();
        this.enderecoCompleto = clienteAntigo.getEnderecoCompleto();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Endereco getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(Endereco enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }
}