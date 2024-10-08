package br.com.lGabrielDev.projetoClientes.clientes.validations;

import static org.mockito.ArgumentMatchers.anyString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.lGabrielDev.projetoClientes.clientes.Cliente;
import br.com.lGabrielDev.projetoClientes.clientes.ClienteRepository;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.email.EmailIsMissingException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldIsNotUniqueException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldLengthIsIncorrect;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmailValidationsTest {

    //attributes
    private String email;

    @Mock 
    private ClienteRepository clienteRepository;

    @InjectMocks
    private EmailValidations emailValidations = Mockito.mock(EmailValidations.class);

    // ===== 'email' nao pode ser NULL =====
    @Test
    public void itShouldReturnTrueBecauseEmailIsNotNull(){
        //arrange
        this.email = "teste@gmail.com";
        //act
        Boolean emailIsNotNull = EmailValidations.emailIsNotNull(this.email);
        //assert
        Assertions.assertThat(emailIsNotNull).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecauseEmailIsNull(){
        //arrange
        this.email = null;
        //act
        Assertions.assertThatThrownBy(() -> EmailValidations.emailIsNotNull(this.email))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    @Test
    public void itShouldThrowAnExceptionBecauseEmailIsBlank(){
        //arrange
        this.email = "   ";
        //act
        Assertions.assertThatThrownBy(() -> EmailValidations.emailIsNotNull(this.email))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    // ===== 'email' deve possuir '@' e '.com' =====
    @Test
    public void itShouldReturnTrueBecauseEmailHasDotCom(){
        //arrange
        this.email = "teste@gmail.com";
        //act
        Boolean emailHasDotCom = EmailValidations.emailHasDotCom(this.email);
        //assert
        Assertions.assertThat(emailHasDotCom).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecauseEmailDoesNotHaveDotCom(){
        //arrange
        this.email = "teste@teste"; // faltando '.com'
        //act
        Assertions.assertThatThrownBy(() -> EmailValidations.emailHasDotCom(this.email))
        //assert
            .isExactlyInstanceOf(EmailIsMissingException.class);
    }

    @Test
    public void itShouldThrowAnExceptionBecauseEmailDoesNotHaveAtSign(){
        //arrange
        this.email = "teste.com"; // faltando '@'
        //act
        Assertions.assertThatThrownBy(() -> EmailValidations.emailHasDotCom(this.email))
        //assert
            .isExactlyInstanceOf(EmailIsMissingException.class);
    }

    // ===== 'email' deve ter no maximo  100 characters =====
    @Test
    public void itShouldReturnTrueBecauseEmailHasThePerfectLength(){
        //arrange
        this.email = "teste@gmail.com";
        //act
        Boolean emailLengthIsCorrect = EmailValidations.emailLengthIsCorrect(this.email);
        //assert
        Assertions.assertThat(emailLengthIsCorrect).isTrue();     
    }

    @Test
    public void itShouldThrowAnExceptionBecauseEmailLengthIsMoreThen100Characters(){
        //arrange
        this.email = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" ; //101 characters
        //act
        Assertions.assertThatThrownBy(() -> EmailValidations.emailLengthIsCorrect(this.email))
        //assert
            .isExactlyInstanceOf(FieldLengthIsIncorrect.class);
    }

    // ===== 'email' deve ser unico =====
    @Test
    public void itShouldReturnTrueBecauseEmailIsUnique(){
        //arrange
        this.email = "teste@gmail.com";
        Mockito.when(this.clienteRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        //act
        Boolean emailIsUnique = EmailValidations.emailIsUnique(this.email, this.clienteRepository);
        //assert
        Assertions.assertThat(emailIsUnique).isTrue();     
    }

    @Test
    public void itShouldThrowAnExceptionBecauseEmailIsNotUnique(){
        //arrange
        this.email = "123";
        //act
        Mockito.when(this.clienteRepository.findByEmail(anyString())).thenReturn(Optional.of(new Cliente()));
        Assertions.assertThatThrownBy(() -> EmailValidations.emailIsUnique(this.email, this.clienteRepository))
        //assert
            .isExactlyInstanceOf(FieldIsNotUniqueException.class);
    }

    // ===== Verificamos se o campo 'email' passou por todas as validacoes, no caso de CADASTRO =====
    @Test
    public void itShouldReturnTrueBecauseCpfPassThroughAllValidationsWhenCreate(){
        //arrange
        this.email = "teste@gmail.com";
        Mockito.when(this.clienteRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        //act
        Boolean allValidationsAreCorrect = EmailValidations.allValidationsAreCorrect(this.email, this.clienteRepository);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    // ===== Verificamos se o campo 'email' passou por todas as validacoes, no caso de UPDATE =====
    @Test
    public void itShouldReturnTrueBecauseEmailPassThroughAllValidationsWhenUpdate(){
        //arrange
        this.email = "teste@gmail.com";
        Mockito.when(this.clienteRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        //act
        Boolean allValidationsAreCorrect = EmailValidations.allValidationsAreCorrect(this.email, this.clienteRepository, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    @Test
    public void itShouldReturnTrueBecauseEmailPassThroughAllValidationsEvenIfIsNull(){
        //arrange
        this.email = null;
        //act
        Boolean allValidationsAreCorrect = EmailValidations.allValidationsAreCorrect(this.email, this.clienteRepository, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }
}