package br.com.lGabrielDev.projetoClientes.clientes.validations;

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
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.id.ClienteNotFoundException;

@ExtendWith(MockitoExtension.class)
public class IdValidationsTest {
    
    //attributes
    private Long clienteId;

    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private IdValidations idValidations = Mockito.mock(IdValidations.class);

    @Test
    public void itShouldReturnTrueBecauseClienteIdIsUnique(){
        //arrange
        this.clienteId = 1l;
        Mockito.when(this.clienteRepository.findById(clienteId)).thenReturn(Optional.of(new Cliente()));
        Boolean clienteExiste = IdValidations.clienteIdIsCorrect(this.clienteId, this.clienteRepository);
        //assert
        Assertions.assertThat(clienteExiste).isTrue();
    }

    @Test
    public void itShouldThrowAnExceptionBecauseClienteDoesNotExists(){
        //arrange
        this.clienteId = 1l;
        Mockito.when(this.clienteRepository.findById(clienteId)).thenReturn(Optional.empty());
        //act
        Assertions.assertThatThrownBy(() -> IdValidations.clienteIdIsCorrect(this.clienteId, this.clienteRepository))
        //assert
            .isExactlyInstanceOf(ClienteNotFoundException.class);
    }
}