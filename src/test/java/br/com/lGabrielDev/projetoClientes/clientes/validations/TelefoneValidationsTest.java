package br.com.lGabrielDev.projetoClientes.clientes.validations;

import static org.mockito.ArgumentMatchers.anyString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import br.com.lGabrielDev.projetoClientes.clientes.Cliente;
import br.com.lGabrielDev.projetoClientes.clientes.ClienteRepository;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldIsNotUniqueException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldLengthIsIncorrect;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldMustHaveOnlyNumbers;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.telefone.TelefonedoesNotContainNine;

@ExtendWith(MockitoExtension.class)
public class TelefoneValidationsTest {
    
    //attributes
    private String telefone = "912345678";

    @Mock 
    private ClienteRepository clienteRepository;

    @InjectMocks
    private TelefoneValidations telefoneValidations = Mockito.mock(TelefoneValidations.class);

    // ===== 'telefone' nao pode ser NULL =====
    @Test
    public void itShouldReturnTrueBecauseTelefoneIsNotNull(){
        //arrange
        //act
        Boolean telefoneIsNotNull = TelefoneValidations.telefoneIsNotNull(this.telefone);
        //assert
        Assertions.assertThat(telefoneIsNotNull).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecauseTelefoneIsNull(){
        //arrange
        this.telefone = null;
        //act
        Assertions.assertThatThrownBy(() -> TelefoneValidations.telefoneIsNotNull(this.telefone))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    @Test
    public void itShouldThrowAnExceptionBecauseTelefoneIsBlank(){
        //arrange
        this.telefone = "   ";
        //act
        Assertions.assertThatThrownBy(() -> TelefoneValidations.telefoneIsNotNull(this.telefone))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    // ===== 'telefone' deve possuir 9 characters =====
    @Test
    public void itShouldReturnTrueBecauseTelefoneHasThePerfectLength(){
        //arrange
        //act
        Boolean telefoneLengthIsCorrect = TelefoneValidations.telefoneLengthIsCorrect(this.telefone);
        //assert
        Assertions.assertThat(telefoneLengthIsCorrect).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecauseTelefoneLengthIsToLong(){
        //arrange
        this.telefone = "0000000000" ; //10 characters
        //act
        Assertions.assertThatThrownBy(() -> TelefoneValidations.telefoneLengthIsCorrect(this.telefone))
        //assert
            .isExactlyInstanceOf(FieldLengthIsIncorrect.class);
    }

    @Test
    public void itShouldThrowAnExceptionBecauseTelefoneLengthIsToShort(){
        //arrange
        this.telefone = "0" ; //1 character
        //act
        Assertions.assertThatThrownBy(() -> TelefoneValidations.telefoneLengthIsCorrect(this.telefone))
        //assert
            .isExactlyInstanceOf(FieldLengthIsIncorrect.class);
    }

    // ===== "telefone" deve possuir apenas números =====
    @Test
    public void itShouldReturnTrueBecauseTelefonefHasOnlyNumbers(){
        //arrange
        //act
        Boolean telefoneHasOnlyNumbers = TelefoneValidations.telefoneHasOnlyNumbers(this.telefone);
        //assert
        Assertions.assertThat(telefoneHasOnlyNumbers).isTrue();     
    }

    @Test
    public void itShouldThrowAnExceptionBecauseTelefoneHasSymbols(){
        //arrange
        this.telefone = "123@#$abc";
        //act
        Assertions.assertThatThrownBy(() -> TelefoneValidations.telefoneHasOnlyNumbers(this.telefone))
        //assert
            .isExactlyInstanceOf(FieldMustHaveOnlyNumbers.class);
    }

    // ===== "telefone" deve possuir o digito 9 na frente =====
    @Test
    public void itShouldReturnTrueBecauseTelefoneFirstDigit9IsCorrect(){
        //arrange
        //act
        Boolean telefoneLengthIsCorrect = TelefoneValidations.firstDigitIs9(this.telefone);
        //assert
        Assertions.assertThat(telefoneLengthIsCorrect).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecauseTelefoneFirstDigitIsWrong(){
        //arrange
        this.telefone = "123" ;
        //act
        Assertions.assertThatThrownBy(() -> TelefoneValidations.firstDigitIs9(this.telefone))
        //assert
            .isExactlyInstanceOf(TelefonedoesNotContainNine.class);
    }

    // ===== "telefone" deve ser único =====
    @Test
    public void itShouldReturnTrueBecaueTelefoneIsUniquel(){
        //arrange
        this.telefone = "123";
        Mockito.when(this.clienteRepository.findByTelefone(anyString())).thenReturn(Optional.empty());
        //act
        Boolean telefoneIsUnique = TelefoneValidations.telefoneIsUnique(this.telefone, this.clienteRepository);
        //assert
        Assertions.assertThat(telefoneIsUnique).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecaueTelefoneIsNotUnique(){
        //arrange
        this.telefone = "123";
        //act
        Mockito.when(this.clienteRepository.findByTelefone(anyString())).thenReturn(Optional.of(new Cliente()));
        Assertions.assertThatThrownBy(() -> TelefoneValidations.telefoneIsUnique(this.telefone, this.clienteRepository))
        //assert
            .isExactlyInstanceOf(FieldIsNotUniqueException.class);
    }

    // ===== Verificamos se o campo 'telefone' passou por todas as validacoes, no caso de CADASTRO =====
    @Test
    public void itShouldReturnTrueBecauseTelefonePassThroughAllValidationsWhenCreate(){
        //arrange
        Mockito.when(this.clienteRepository.findByTelefone(this.telefone)).thenReturn(Optional.empty());
        //act
        Boolean allValidationsAreCorrect = TelefoneValidations.allValidationsAreCorrect(this.telefone, this.clienteRepository);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    // ===== Verificamos se o campo 'telefone' passou por todas as validacoes, no caso de UPDATE =====
    @Test
    public void itShouldReturnTrueBecauseCpfPassThroughAllValidationsWhenUpdate(){
        //arrange
        Mockito.when(this.clienteRepository.findByTelefone(this.telefone)).thenReturn(Optional.empty());
        //act
        Boolean allValidationsAreCorrect = TelefoneValidations.allValidationsAreCorrect(this.telefone, this.clienteRepository, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    @Test
    public void itShouldReturnTrueBecauseCpfIsNullWhenUpdate(){
        //arrange
        this.telefone = null;
        //act
        Boolean allValidationsAreCorrect = CpfValidations.allValidationsAreCorrect(this.telefone, this.clienteRepository, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }
}