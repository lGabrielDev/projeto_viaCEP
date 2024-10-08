package br.com.lGabrielDev.projetoClientes.endereco;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnderecoTest {
    
    private Endereco endereco1;
    private Endereco endereco2;

    @BeforeEach
    public void setup(){
        this.endereco1 = new Endereco();
        this.endereco1.setId(1L);
        this.endereco1.setCep("123");
        this.endereco1.setLogradouro("Rua A");
        this.endereco1.setNumeroCasa(100);
        this.endereco1.setComplemento("Apto 1");
        this.endereco1.setBairro("Bairro A");
        this.endereco1.setLocalidade("Cidade A");
        this.endereco1.setUf("UF");
        this.endereco1.setEstado("Estado A");
        this.endereco1.setRegiao("Região A");
        this.endereco1.setDdd("11");

        this.endereco2 = new Endereco();
        this.endereco2.setId(1L);
        this.endereco2.setCep("123");
        this.endereco2.setLogradouro("Rua A");
        this.endereco2.setNumeroCasa(100);
        this.endereco2.setComplemento("Apto 1");
        this.endereco2.setBairro("Bairro A");
        this.endereco2.setLocalidade("Cidade A");
        this.endereco2.setUf("UF");
        this.endereco2.setEstado("Estado A");
        this.endereco2.setRegiao("Região A");
        this.endereco2.setDdd("11");
    }

    // Testando o method equals()
    @Test
    public void itShouldReturnTrueBecauseEnderecosAreTheSame(){
        //arrange
        //act
        Boolean theyAreEquals = this.endereco1.equals(this.endereco2);
        //assert
        Assertions.assertThat(theyAreEquals).isTrue();
    }

    @Test
    public void itShouldReturnFalseBecauseSomeAttributeIsWrong(){
        //arrange
        this.endereco2.setEstado("estado b");
        //act
        Boolean theyAreEquals = this.endereco1.equals(this.endereco2);
        //assert
        Assertions.assertThat(theyAreEquals).isFalse();
    }

    @Test
    public void itShouldReturnFalseWhenComparingWithNull(){
        //arrange
        this.endereco2 = null;
        //act
        Boolean theyAreEquals = this.endereco1.equals(this.endereco2);
        //assert
        Assertions.assertThat(theyAreEquals).isFalse();
    }
}