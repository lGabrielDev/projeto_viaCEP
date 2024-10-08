package br.com.lGabrielDev.projetoClientes.clientes.validations;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.dataDeNascimento.ClienteEhMenorDeIdadeException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;

@ExtendWith(MockitoExtension.class)
public class DataDeNascimentoValidationsTest {
    
    //attributes
    private LocalDate dataDeNascimento;

    // nao pode ser NULL - CHECK
    @Test
    public void itShouldReturnTrueBecauseDataDeNascimentoIsCorrect(){
        //arrange
        this.dataDeNascimento = LocalDate.of(1970,04,01);
        //act
        Boolean dataDeNascimentoIsNotNull = DataDeNascimentoValidations.dataDeNascimentoIsNotNull(dataDeNascimento);
        //assert
        Assertions.assertThat(dataDeNascimentoIsNotNull).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecauseDataDeNascimentoIsNull(){
        //arrange
        this.dataDeNascimento = null;
        //act
        Assertions.assertThatThrownBy(() -> DataDeNascimentoValidations.dataDeNascimentoIsNotNull(this.dataDeNascimento))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }


    // deve ser maior de 18 anos - CHECK
    @Test
    public void itShouldReturnTrueBecauseClienteIs18orMore(){
        //arrange
        this.dataDeNascimento = LocalDate.of(1970,04,01);
        //act
        Boolean dataDeNascimentoIsCorrect = DataDeNascimentoValidations.clienteEhMaiorDe18(this.dataDeNascimento);
        //assert
        Assertions.assertThat(dataDeNascimentoIsCorrect).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecauseClienteIsUnder18(){
        //arrange
        this.dataDeNascimento = LocalDate.of(2007,04,01);
        //act
        Assertions.assertThatThrownBy(() -> DataDeNascimentoValidations.clienteEhMaiorDe18(this.dataDeNascimento))
        //assert
            .isExactlyInstanceOf(ClienteEhMenorDeIdadeException.class);
    }

    //Verificamos se o campo 'data de nascimento' passou por todas as validacoes, ao CADASTRAR
    @Test
    public void itShouldReturnTrueBecauseDataDeNascimentoPassThroughAllValidationsWhenCreate(){
        //arrange
        this.dataDeNascimento = LocalDate.of(1970,04,01);
        //act
        Boolean allValidationsAreCorrect = DataDeNascimentoValidations.allValidationsAreCorrect(this.dataDeNascimento);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    //Verificamos se o campo 'data de nascimento' passou por todas as validacoes, ao ATUALIZAR
    @Test
    public void itShouldReturnTrueBecauseDataDeNascimentoPassThroughAllValidationsWhenUpdate(){
        //arrange
        this.dataDeNascimento = LocalDate.of(1970,04,01);
        //act
        Boolean allValidationsAreCorrect = DataDeNascimentoValidations.allValidationsAreCorrect(this.dataDeNascimento, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    @Test
    public void itShouldReturnTrueBecauseDataDeNascimentoPassThroughAllValidationsEvenIfIsNull(){
        //arrange
        this.dataDeNascimento = null;
        //act
        Boolean allValidationsAreCorrect = DataDeNascimentoValidations.allValidationsAreCorrect(this.dataDeNascimento, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }
}
