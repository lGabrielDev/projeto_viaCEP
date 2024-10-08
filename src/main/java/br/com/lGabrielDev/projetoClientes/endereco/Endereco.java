package br.com.lGabrielDev.projetoClientes.endereco;

import br.com.lGabrielDev.projetoClientes.clientes.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_endereco")
public class Endereco {
    
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(mappedBy = "enderecoCompleto")
    private Cliente cliente;

    //constructors
    public Endereco(){}

    public Endereco(Endereco enderecoAntigo){
        this.id = enderecoAntigo.getId();
        this.cep = enderecoAntigo.getCep();
        this.logradouro = enderecoAntigo.getLogradouro();
        this.numeroCasa = enderecoAntigo.getNumeroCasa();
        this.complemento = enderecoAntigo.getComplemento();
        this.bairro = enderecoAntigo.getBairro();
        this.localidade = enderecoAntigo.getLocalidade();
        this.uf = enderecoAntigo.getUf();
        this.estado = enderecoAntigo.getEstado();
        this.regiao = enderecoAntigo.getRegiao();
        this.ddd = enderecoAntigo.getDdd();
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    } 

    //equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Endereco)) return false;
        Endereco endereco2 = (Endereco) obj;

        // Compara todos os campos que definem a igualdade
        return
            this.id.equals(endereco2.id) &&
            this.cep.equals(endereco2.cep) &&
            this.logradouro.equals(endereco2.logradouro) &&
            this.numeroCasa.equals(endereco2.numeroCasa) &&
            this.complemento.equals(endereco2.complemento) &&
            this.bairro.equals(endereco2.bairro) &&
            this.localidade.equals(endereco2.localidade) &&
            this.uf.equals(endereco2.uf) &&
            this.estado.equals(endereco2.estado) &&
            this.regiao.equals(endereco2.regiao) &&
            this.ddd.equals(endereco2.ddd); 
    }
}