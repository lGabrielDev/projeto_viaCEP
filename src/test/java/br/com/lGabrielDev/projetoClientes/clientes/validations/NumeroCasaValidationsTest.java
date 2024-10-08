package br.com.lGabrielDev.projetoClientes.clientes.validations;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;

public class NumeroCasaValidationsTest {
    
    //attributes
    Integer numeroCasa = 123;

    // ===== 'numeroCasa' nao pode ser NULL =====
    @Test
    public void itShouldReturnTrueBecausenNumeroCasaIsNotNull(){
        //arrang
        //act
        Boolean numeroCasaIsNotNull = NumeroCasaValidations.numeroCasaIsNotNull(this.numeroCasa);
        //assert
        Assertions.assertThat(numeroCasaIsNotNull).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecauseNumeroCasaIsNull(){
        //arrange
        this.numeroCasa = null;
        //act
        Assertions.assertThatThrownBy(() -> NumeroCasaValidations.numeroCasaIsNotNull(this.numeroCasa))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    // ===== 'numeroCasa' passou por todas as validacoes, ao CADASTRAR =====
    @Test
    public void itShouldReturnTrueBecauseNumeroCasaPassThroughAllValidationsWhenCreate(){
        //arrange
        //act
        Boolean allValidationsAreCorrect = NumeroCasaValidations.allValidationsAreCorrect(this.numeroCasa);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    // ===== Verificamos se o campo 'numeroCasa' passou por todas as validacoes, no caso de ATUALIZAR =====
    @Test
    public void itShouldReturnTrueBecauseNumeroCasaPassThroughAllValidationsWhenUpdate(){
        //arrange
        //act
        Boolean allValidationsAreCorrect = NumeroCasaValidations.allValidationsAreCorrect(this.numeroCasa, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    @Test
    public void itShouldReturnTrueBecauseNumeroCasaPassThroughAllValidationsWhenUpdateEvenIfIsNull(){
        //arrange
        this.numeroCasa = null;
        //act
        Boolean allValidationsAreCorrect = NumeroCasaValidations.allValidationsAreCorrect(this.numeroCasa, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }
}