package br.com.lGabrielDev.projetoClientes.clientes.validations;

import static org.mockito.ArgumentMatchers.anyString;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.lGabrielDev.projetoClientes.clientes.Cliente;
import br.com.lGabrielDev.projetoClientes.clientes.ClienteRepository;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldIsNotUniqueException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldLengthIsIncorrect;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldMustHaveOnlyNumbers;

@ExtendWith(MockitoExtension.class)
public class CpfValidationsTest {

    //attributes
    private String cpf;

    @Mock 
    private ClienteRepository clienteRepository;

    @InjectMocks
    private CpfValidations cpfValidations = Mockito.mock(CpfValidations.class);

    // ===== 'cpf' nao pode ser NULL =====
    @Test
    public void itShouldReturnTrueBecauseCpfIsNotNull(){
        //arrange
        this.cpf = "123";
        //act
        Boolean cpfIsNotNull = CpfValidations.cpfIsNotNull(this.cpf);
        //assert
        Assertions.assertThat(cpfIsNotNull).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecauseCpfNull(){
        //arrange
        this.cpf = null;
        //act
        Assertions.assertThatThrownBy(() -> CpfValidations.cpfIsNotNull(this.cpf))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    @Test
    public void itShouldThrowAnExceptionBecauseCpfIsBlank(){
        //arrange
        this.cpf = "   ";
        //act
        Assertions.assertThatThrownBy(() -> CpfValidations.cpfIsNotNull(this.cpf))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    // ===== 'cpf' deve conter 11 charaters =====
    @Test
    public void itShouldReturnTrueBecauseCpfHasThePerfectLength(){
        //arrange
        this.cpf = "12345678912";
        //act
        Boolean cpfLengthIsCorrect = CpfValidations.cpfLengthIsCorrect(this.cpf);
        //assert
        Assertions.assertThat(cpfLengthIsCorrect).isTrue();     
    }

    @Test
    public void itShouldThrowAnExceptionBecauseCpfLengthIsLessThen11Characters(){
        //arrange
        this.cpf = "1";
        //act
        Assertions.assertThatThrownBy(() -> CpfValidations.cpfLengthIsCorrect(this.cpf))
        //assert
            .isExactlyInstanceOf(FieldLengthIsIncorrect.class);
    }

    @Test
    public void itShouldThrowAnExceptionBecauseCpfLengthIsMoreThen11Characters(){
        //arrange
        this.cpf = "123456789123456789";
        //act
        Assertions.assertThatThrownBy(() -> CpfValidations.cpfLengthIsCorrect(this.cpf))
        //assert
            .isExactlyInstanceOf(FieldLengthIsIncorrect.class);
    }

    // ===== 'cpf' deve possuir apenas numeros =====
    @Test
    public void itShouldReturnTrueBecauseCpfHasOnlyNumbers(){
        //arrange
        this.cpf = "123";
        //act
        Boolean cpfHasOnlyNumbers = CpfValidations.cpfHasOnlyNumbers(this.cpf);
        //assert
        Assertions.assertThat(cpfHasOnlyNumbers).isTrue();     
    }

    @Test
    public void itShouldThrowAnExceptionBecauseCpfHasSymbols(){
        //arrange
        this.cpf = "123abc";
        //act
        Assertions.assertThatThrownBy(() -> CpfValidations.cpfHasOnlyNumbers(this.cpf))
        //assert
            .isExactlyInstanceOf(FieldMustHaveOnlyNumbers.class);
    }

    // ===== 'cpf' deve ser unico =====
    @Test
    public void itShouldReturnTrueBecauseCpfIsUniquel(){
        //arrange
        this.cpf = "123";
        Mockito.when(this.clienteRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        //act
        Boolean cpfIsUnique = CpfValidations.cpfIsUnique(this.cpf, this.clienteRepository);
        //assert
        Assertions.assertThat(cpfIsUnique).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecauseCpfIsNotUnique(){
        //arrange
        this.cpf = "123";
        //act
        Mockito.when(this.clienteRepository.findByCpf(anyString())).thenReturn(Optional.of(new Cliente()));
        Assertions.assertThatThrownBy(() -> CpfValidations.cpfIsUnique(this.cpf, this.clienteRepository))
        //assert
            .isExactlyInstanceOf(FieldIsNotUniqueException.class);
    }

    // ===== Verificamos se o campo 'cpf' passou por todas as validacoes, no caso de CADASTRO =====
    @Test
    public void itShouldReturnTrueBecauseCpfPassThroughAllValidationsWhenCreate(){
        //arrange
        this.cpf = "12345678912"; //11 characters
        Mockito.when(this.clienteRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        //act
        Boolean allValidationsAreCorrect = CpfValidations.allValidationsAreCorrect(this.cpf, this.clienteRepository);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    // ===== Verificamos se o campo 'cpf' passou por todas as validacoes, no caso de UPDATE =====
    @Test
    public void itShouldReturnTrueBecauseCpfPassThroughAllValidationsWhenUpdate(){
        //arrange
        this.cpf = "12345678912"; //11 characters
        Mockito.when(this.clienteRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        //act
        Boolean allValidationsAreCorrect = CpfValidations.allValidationsAreCorrect(this.cpf, this.clienteRepository, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    @Test
    public void itShouldReturnTrueBecauseCpfIsNullWhenUpdate(){
        //arrange
        this.cpf = null; //11 characters
        //act
        Boolean allValidationsAreCorrect = CpfValidations.allValidationsAreCorrect(this.cpf, this.clienteRepository, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }
}