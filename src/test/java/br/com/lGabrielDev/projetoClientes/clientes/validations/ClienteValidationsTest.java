package br.com.lGabrielDev.projetoClientes.clientes.validations;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.lGabrielDev.projetoClientes.clientes.Cliente;
import br.com.lGabrielDev.projetoClientes.clientes.ClienteRepository;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteCreateDto;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.dataDeNascimento.ClienteEhMenorDeIdadeException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.email.EmailIsMissingException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldLengthIsIncorrect;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.id.ClienteNotFoundException;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class ClienteValidationsTest {
    
    //attributes
    @Mock 
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteValidations clienteValidations = Mockito.mock(ClienteValidations.class);

    private ClienteCreateDto clienteCreateDto;

    @BeforeEach
    public void setup(){
        this.clienteCreateDto = new ClienteCreateDto();
        clienteCreateDto.setNome("goku");
        clienteCreateDto.setDataDeNascimento(LocalDate.of(1990, 1, 1));
        clienteCreateDto.setCpf("12345678981");
        clienteCreateDto.setEmail("goku@gmail.com");
        clienteCreateDto.setTelefone("912345698");
        clienteCreateDto.setNumeroCasa(123);
    }

    // ============= validations to Create a Cliente =============
    //successes
    @Test
    public void whenCreateAClienteItShouldPassThroughAllValidations(){
        //arrange
        Mockito.when(this.clienteRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        Mockito.when(this.clienteRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        Mockito.when(this.clienteRepository.findByTelefone(anyString())).thenReturn(Optional.empty());
        //act
        Boolean methodResult = ClienteValidations.allValidationsAreCorrect(this.clienteCreateDto, this.clienteRepository);
        //assert
        Assertions.assertThat(methodResult).isTrue();
    }

    //fails
    @Test
    public void whenCreateAClienteItShouldThrowAnExceptionBecauseNameIsNull(){
        //arrange
        this.clienteCreateDto.setNome(null);
        //act
        Assertions.assertThatThrownBy(() -> ClienteValidations.allValidationsAreCorrect(clienteCreateDto, clienteRepository))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    @Test
    public void whenCreateAClienteItShouldThrowAnExceptionBecauseDataDeNascimentoIsNull(){
        //arrange
        this.clienteCreateDto.setDataDeNascimento(null);
        //act
        Assertions.assertThatThrownBy(() -> ClienteValidations.allValidationsAreCorrect(clienteCreateDto, clienteRepository))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    @Test
    public void whenCreateAClienteItShouldThrowAnExceptionBecauseCpfIsNull(){
        //arrange
        this.clienteCreateDto.setCpf(null);
        //act
        Assertions.assertThatThrownBy(() -> ClienteValidations.allValidationsAreCorrect(clienteCreateDto, clienteRepository))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    @Test
    public void whenCreateAClienteItShouldThrowAnExceptionBecauseEmailIsNull(){
        //arrange
        this.clienteCreateDto.setEmail(null);
        //act
        Assertions.assertThatThrownBy(() -> ClienteValidations.allValidationsAreCorrect(clienteCreateDto, clienteRepository))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    @Test
    public void whenCreateAClienteItShouldThrowAnExceptionBecauseTelefoneIsNull(){
        //arrange
        this.clienteCreateDto.setTelefone(null);
        //act
        Assertions.assertThatThrownBy(() -> ClienteValidations.allValidationsAreCorrect(clienteCreateDto, clienteRepository))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    @Test
    public void whenCreateAClienteItShouldThrowAnExceptionBecauseNumeroCasaIsNull(){
        //arrange
        this.clienteCreateDto.setNumeroCasa(null);
        //act
        Assertions.assertThatThrownBy(() -> ClienteValidations.allValidationsAreCorrect(clienteCreateDto, clienteRepository))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    // ============= validations to Update a Cliente =============
    //successes
    @Test
    public void whenUpdateAClienteItShouldPassThroughAllValidations(){
        //arrange
        Mockito.when(this.clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Cliente()));
        Mockito.when(this.clienteRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        Mockito.when(this.clienteRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        Mockito.when(this.clienteRepository.findByTelefone(anyString())).thenReturn(Optional.empty());
        //act
        Boolean methodResult = ClienteValidations.allValidationsAreCorrect(this.clienteCreateDto, this.clienteRepository, anyLong());
        //assert
        Assertions.assertThat(methodResult).isTrue();
    }

    @Test
    public void whenUpdateAClienteItShouldPassThroughAllValidationsEvenIfTheAttributesAreNull(){
        //arrange
        clienteCreateDto.setNome(null);
        clienteCreateDto.setDataDeNascimento(null);
        clienteCreateDto.setCpf(null);
        clienteCreateDto.setEmail(null);
        clienteCreateDto.setTelefone(null);
        clienteCreateDto.setNumeroCasa(null);

        Mockito.when(this.clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Cliente()));
        //act
        Boolean methodResult = ClienteValidations.allValidationsAreCorrect(this.clienteCreateDto, this.clienteRepository, anyLong());
        //assert
        Assertions.assertThat(methodResult).isTrue();
    }

    //fails
    @Test
    public void whenUpdateAClienteItShouldThrowAnExceptionBecauseClienteDoesNotExists(){
        //arrange
        Mockito.when(this.clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
        //act
        Assertions.assertThatThrownBy(() -> ClienteValidations.allValidationsAreCorrect(clienteCreateDto, clienteRepository, anyLong()))
        //assert
            .isExactlyInstanceOf(ClienteNotFoundException.class);
    }

    @Test
    public void whenUpdateAClienteItShouldThrowAnExceptionBecauseNameIsEmpty(){
        //arrange
        this.clienteCreateDto.setNome("           ");
        Mockito.when(this.clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Cliente()));
        //act
        Assertions.assertThatThrownBy(() -> ClienteValidations.allValidationsAreCorrect(clienteCreateDto, clienteRepository, anyLong()))
        //assert
            .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    @Test
    public void whenUpdateAClienteItShouldThrowAnExceptionBecauseClientIsUnder18(){
        //arrange
        this.clienteCreateDto.setDataDeNascimento(LocalDate.of(2024,9,30));
        Mockito.when(this.clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Cliente()));
        //act
        Assertions.assertThatThrownBy(() -> ClienteValidations.allValidationsAreCorrect(clienteCreateDto, clienteRepository, anyLong()))
        //assert
            .isExactlyInstanceOf(ClienteEhMenorDeIdadeException.class);
    }

    @Test
    public void whenUpdateAClienteItShouldThrowAnExceptionBecauseCpfHasLessThen11Characters(){
        //arrange
        this.clienteCreateDto.setCpf("123");
        Mockito.when(this.clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Cliente()));
        //act
        Assertions.assertThatThrownBy(() -> ClienteValidations.allValidationsAreCorrect(clienteCreateDto, clienteRepository, anyLong()))
        //assert
            .isExactlyInstanceOf(FieldLengthIsIncorrect.class);
    }
    
    @Test
    public void whenUpdateAClienteItShouldThrowAnExceptionBecauseEmailHasNoDotCom(){
        //arrange
        this.clienteCreateDto.setEmail("teste@teste");
        Mockito.when(this.clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Cliente()));
        Mockito.when(this.clienteRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        //act
        Assertions.assertThatThrownBy(() -> ClienteValidations.allValidationsAreCorrect(clienteCreateDto, clienteRepository, anyLong()))
        //assert
            .isExactlyInstanceOf(EmailIsMissingException.class);
    }

    @Test
    public void whenUpdateAClienteItShouldThrowAnExceptionBecauseTelefoneLengthIsWrong(){
        //arrange
        this.clienteCreateDto.setTelefone("123");
        Mockito.when(this.clienteRepository.findById(anyLong())).thenReturn(Optional.of(new Cliente()));
        Mockito.when(this.clienteRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        Mockito.when(this.clienteRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        //act
        Assertions.assertThatThrownBy(() -> ClienteValidations.allValidationsAreCorrect(clienteCreateDto, clienteRepository, anyLong()))
        //assert
            .isExactlyInstanceOf(FieldLengthIsIncorrect.class);
    }
}