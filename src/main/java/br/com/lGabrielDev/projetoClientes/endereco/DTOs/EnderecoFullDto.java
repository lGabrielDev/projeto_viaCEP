package br.com.lGabrielDev.projetoClientes.endereco.DTOs;

import br.com.lGabrielDev.projetoClientes.endereco.Endereco;

public class EnderecoFullDto {
    
    //attributes
    private Long id;
    public String cep;
    public String logradouro;
    public Integer numeroCasa;
    public String complemento;
    public String bairro;
    public String localidade;
    public String uf;
    public String estado;
    public String regiao;
    public String ddd;

    //comstructors
    public EnderecoFullDto(){}

    public EnderecoFullDto(Endereco endereco){
        this.id = endereco.getId();
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.numeroCasa = endereco.getNumeroCasa();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
        this.estado = endereco.getEstado();
        this.regiao = endereco.getRegiao();
        this.ddd = endereco.getDdd();
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(Integer numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
}