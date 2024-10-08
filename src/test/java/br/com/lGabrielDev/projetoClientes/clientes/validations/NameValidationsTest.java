package br.com.lGabrielDev.projetoClientes.clientes.validations;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldLengthIsIncorrect;

public class NameValidationsTest {
 
    //attributes
    private String name = "goku";

    // ===== 'name' nao pode ser NULL =====
    @Test
    public void itShouldReturnTrueBecauseNameIsNotNull(){
        //arrang
        //act
        Boolean nameIsNotNull = NameValidations.nameIsNotNull(this.name);
        //assert
        Assertions.assertThat(nameIsNotNull).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecauseNameIsNull(){
        //arrange
        this.name = null;
        //act
        Assertions.assertThatThrownBy(() -> NameValidations.nameIsNotNull(this.name))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    @Test
    public void itShouldThrowAnExceptionBecauseNameIsBlank(){
        //arrange
        this.name = "   ";
        //act
        Assertions.assertThatThrownBy(() -> NameValidations.nameIsNotNull(this.name))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    // ===== 'nome' deve ter até 100 characters =====
    @Test
    public void itShouldReturnTrueBecauseNameHasThePerfectLength(){
        //arrange
        //act
        Boolean nameLengthIsCorrect = NameValidations.nameLengthIsCorrect(this.name);
        //assert
        Assertions.assertThat(nameLengthIsCorrect).isTrue();     
    }

    @Test
    public void itShouldThrowAnExceptionBecauseNameLengthIsToLong(){
        //arrange
        this.name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" ; //101 characters
        //act
        Assertions.assertThatThrownBy(() -> NameValidations.nameLengthIsCorrect(this.name))
        //assert
            .isExactlyInstanceOf(FieldLengthIsIncorrect.class);
    }

    // ===== Verificamos se o campo 'name' passou por todas as validacoes, no caso de CADASTRO =====
    @Test
    public void itShouldReturnTrueBecauseNamePassThroughAllValidationsWhenCreate(){
        //arrange
        //act
        Boolean allValidationsAreCorrect = NameValidations.allValidationsAreCorrect(this.name);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    // ===== Verificamos se o campo 'name' passou por todas as validacoes, no caso de UPDATE =====
    @Test
    public void itShouldReturnTrueBecauseNamePassThroughAllValidationsWhenUpdate(){
        //arrange
        //act
        Boolean allValidationsAreCorrect = NameValidations.allValidationsAreCorrect(this.name, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    @Test
    public void itShouldReturnTrueBecauseNamePassThroughAllValidationsEvenIfIsNull(){
        //arrange
        this.name = null;
        //act
        Boolean allValidationsAreCorrect = NameValidations.allValidationsAreCorrect(this.name, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }
}