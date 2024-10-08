package br.com.lGabrielDev.projetoClientes.clientes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import java.util.Optional;
import java.util.List;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteCreateDto;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteFullDto;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.id.ClienteNotFoundException;
import br.com.lGabrielDev.projetoClientes.clientes.validations.ClienteValidations;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;
import br.com.lGabrielDev.projetoClientes.endereco.EnderecoRepository;
import br.com.lGabrielDev.projetoClientes.endereco.validations.CepValidations;
import br.com.lGabrielDev.projetoClientes.relatorio.ManipuladorDoRelatorioValido;
import br.com.lGabrielDev.projetoClientes.viaCep.ViaCepController;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
 
    //attributes
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private ViaCepController viaCep;
    @Mock
    ManipuladorDoRelatorioValido manipuladorDoRelatorioValido;

    @InjectMocks
    private ClienteService clienteService;

    private ClienteCreateDto clienteCreateDto;
    private Endereco endereco;

    private MockedStatic<ClienteValidations> mockedClienteValidations;
    private MockedStatic<CepValidations> mockedCepValidations;

    private Cliente cliente1;
    private Cliente cliente2;

    //setup
    @BeforeEach
    public void setup(){
        //mockamos os methods staticos
        this.mockedClienteValidations = mockStatic(ClienteValidations.class);
        mockedClienteValidations.when(() -> ClienteValidations.allValidationsAreCorrect(any(), any())).thenReturn(true);

        mockedCepValidations = mockStatic(CepValidations.class);
        mockedCepValidations.when(() -> CepValidations.allValidationsAreCorrect(anyString(), any())).thenReturn(true);

        this.endereco = new Endereco();
        this.endereco.setId(1L);
        this.endereco.setCep("12345678");
        this.endereco.setLogradouro("Rua random");
        this.endereco.setNumeroCasa(123);
        this.endereco.setComplemento("Apto 101");
        this.endereco.setBairro("Bairro random");
        this.endereco.setLocalidade("Cidade random");
        this.endereco.setUf("SP");
        this.endereco.setEstado("São Paulo");
        this.endereco.setRegiao("Sudeste");
        this.endereco.setDdd("11");
        this.endereco.setNumeroCasa(123);

        this.clienteCreateDto = new ClienteCreateDto();
        this.clienteCreateDto.setNome("goku");
        this.clienteCreateDto.setDataDeNascimento(LocalDate.of(1992, 5, 4));
        this.clienteCreateDto.setCpf("12345678912");
        this.clienteCreateDto.setEmail("goku@gmail.com");
        this.clienteCreateDto.setTelefone("912345678");
        this.clienteCreateDto.setCep("44444444");
        this.clienteCreateDto.setNumeroCasa(456);

        cliente1 = new Cliente();
        cliente1.setNome("batman");
        cliente1.setEnderecoCompleto(this.endereco);

        cliente2 = new Cliente();
        cliente2.setNome("coringa");
        cliente2.setEnderecoCompleto(this.endereco);

    }

    @AfterEach
    public void closeSttufs(){
        mockedClienteValidations.close();
        mockedCepValidations.close();
    }

    //==================== CREATE ====================
    @Test
    public void itShouldCreateAClienteSuccessfully(){
        //arrange
        Mockito.when(this.viaCep.getEnderecoByCep(anyString())).thenReturn(this.endereco);
        Mockito.when(this.enderecoRepository.save(any())).thenReturn(this.endereco);
        //act
        ClienteFullDto methodResult = this.clienteService.createCliente(this.clienteCreateDto);
        //assert
        Assertions.assertThat(methodResult).isNotNull();
        Assertions.assertThat(methodResult.getNome()).isEqualTo("goku");
        Assertions.assertThat(methodResult.getEnderecoCompleto().getNumeroCasa()).isEqualTo(456);
    }

    //==================== UPDATE ====================
    @Test
    public void itShouldUpdateAClienteSuccessfully(){
        //arrange
        Cliente clienteAntigo = new Cliente();
        clienteAntigo.setId(1l);
        clienteAntigo.setNome("gokuAntigo");
        clienteAntigo.setEnderecoCompleto(this.endereco);

        Mockito.when(this.clienteRepository.findById(clienteAntigo.getId())).thenReturn(Optional.of(clienteAntigo));
        Mockito.when(this.enderecoRepository.findById(clienteAntigo.getEnderecoCompleto().getId())).thenReturn(Optional.of(this.endereco));
        Mockito.when(this.viaCep.getEnderecoByCep(anyString())).thenReturn(this.endereco);
        //act
        ClienteFullDto methodResult = this.clienteService.updateCliente(clienteAntigo.getId(), this.clienteCreateDto);
        //assert
        Assertions.assertThat(methodResult.getNome()).isEqualTo(this.clienteCreateDto.getNome());
        Assertions.assertThat(methodResult.getEnderecoCompleto().getNumeroCasa()).isEqualTo(this.clienteCreateDto.getNumeroCasa());
    }

    //tentar atualizar um cliente, onde os attributes informados no body sao null.
    //Ou seja, o cliente permanece com os mesmos valores.
    @Test
    public void clienteStillHasTheSameAttributes(){
        //arrange
        Cliente clienteAntigo = new Cliente();
        clienteAntigo.setId(1l);
        clienteAntigo.setNome("gokuAntigo");
        clienteAntigo.setEnderecoCompleto(this.endereco);
        Long clienteId = 1l;
        
        ClienteCreateDto clienteCreateDtoAttributesAreNull = new ClienteCreateDto();

        Mockito.when(this.clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteAntigo));
        Mockito.when(this.enderecoRepository.findById(clienteAntigo.getEnderecoCompleto().getId())).thenReturn(Optional.of(endereco));
        //act
        ClienteFullDto methodResult = this.clienteService.updateCliente(clienteAntigo.getId(), clienteCreateDtoAttributesAreNull);
        //assert
        Assertions.assertThat(methodResult.getNome()).isEqualTo("gokuAntigo");
        Assertions.assertThat(methodResult.getEnderecoCompleto().getNumeroCasa()).isEqualTo(123);
    }

    //==================== READ by #ID ====================
    @Test
    public void itShouldReturnAClienteByIdSuccessfully(){
        //arrange
        Cliente clienteAntigo = new Cliente();
        clienteAntigo.setId(1l);
        clienteAntigo.setNome("gokuAntigo");
        clienteAntigo.setEnderecoCompleto(this.endereco);
        Long clienteId = 1l;

        Mockito.when(this.clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteAntigo));
        //act
        ClienteFullDto methodResult = this.clienteService.getClienteById(clienteId);
        //assert
        Assertions.assertThat(methodResult.getNome()).isEqualTo("gokuAntigo");
        Assertions.assertThat(methodResult.getEnderecoCompleto().getNumeroCasa()).isEqualTo(123);
    }

    @Test
    public void itShoulThrowAnExceptionBecauseIdIsWrongAndClienteDoesNotExists(){
        //arrange
        Long clienteId = 1l;

        Mockito.when(this.clienteRepository.findById(clienteId)).thenReturn(Optional.empty());
        //act
        Assertions.assertThatThrownBy(() -> this.clienteService.getClienteById(clienteId))
            .isExactlyInstanceOf(ClienteNotFoundException.class);
    }

    //==================== READ ALL ====================
    //- verificamos se conseguimos entrar em cada condicao do if
    //- verificamos se a conversao da lista de "Cliente" para uma lista de "ClienteFullDto" acontece com sucesso
    @Test
    public void itShouldCallTheFindAllOrderByIdAscMethod(){
        //arrange
        List<Cliente> clientes = List.of(this.cliente1, this.cliente2);

        Mockito.when(this.clienteRepository.findAllOrderByIdAsc()).thenReturn(clientes);
        //act
        List<ClienteFullDto> methodResult = this.clienteService.getAllClientes(null, null);
        //assert
        Mockito.verify(this.clienteRepository).findAllOrderByIdAsc();
        Assertions.assertThat(methodResult.size()).isEqualTo(2);
        Assertions.assertThat(methodResult.get(0).getNome()).isEqualTo("batman");
        Assertions.assertThat(methodResult.get(1).getNome()).isEqualTo("coringa");
    }

    @Test
    public void itShouldCallTheFindAllFilterByDddMethod(){
        //arrange
        Integer ddd = 11;

        Mockito.when(this.clienteRepository.findAllFilterByDdd(ddd)).thenReturn(List.of(this.cliente1, cliente2));
        //act
        List<ClienteFullDto> methodResult = this.clienteService.getAllClientes(ddd, null);
        //assert
        Mockito.verify(this.clienteRepository).findAllFilterByDdd(ddd);
        Assertions.assertThat(methodResult.size()).isEqualTo(2);
        Assertions.assertThat(methodResult.get(0).getNome()).isEqualTo("batman");
        Assertions.assertThat(methodResult.get(1).getNome()).isEqualTo("coringa");
    }

    @Test
    public void itShouldCallTheFindAllFilterByUfMethod(){
        //arrange
        String uf = "SP";

        Mockito.when(this.clienteRepository.findAllFilterByUf(uf)).thenReturn(List.of(this.cliente1, cliente2));
        //act
        List<ClienteFullDto> methodResult = this.clienteService.getAllClientes(null, uf);
        //assert
        Mockito.verify(this.clienteRepository).findAllFilterByUf(uf);
        Assertions.assertThat(methodResult.size()).isEqualTo(2);
        Assertions.assertThat(methodResult.get(0).getNome()).isEqualTo("batman");
        Assertions.assertThat(methodResult.get(1).getNome()).isEqualTo("coringa");
    }

    @Test
    public void itShouldCallTheFindAllFilterByDddAndUfMethod(){
        //arrange
        Integer ddd = 11;
        String uf = "SP";

        Mockito.when(this.clienteRepository.findAllFilterByDddAndUf(ddd,uf)).thenReturn(List.of(this.cliente1, cliente2));
        //act
        List<ClienteFullDto> methodResult = this.clienteService.getAllClientes(ddd, uf);
        //assert
        Mockito.verify(this.clienteRepository).findAllFilterByDddAndUf(ddd, uf);
        Assertions.assertThat(methodResult.size()).isEqualTo(2);
        Assertions.assertThat(methodResult.get(0).getNome()).isEqualTo("batman");
        Assertions.assertThat(methodResult.get(1).getNome()).isEqualTo("coringa");
    }

    //==================== DELETE ====================
    @Test
    public void itShouldDeleteAClienteSuccessfully(){
        //arrange
        Long clienteId = this.cliente1.getId();
        Long enderecoId = this.cliente1.getEnderecoCompleto().getId();

        Mockito.when(this.clienteRepository.findById(clienteId)).thenReturn(Optional.of(this.cliente1));
        //act
        String methodResult = this.clienteService.deleteClienteById(clienteId);
        //assert
        Assertions.assertThat(methodResult).isEqualTo(String.format("Cliente #%d deletado com sucesso!", clienteId));
        Mockito.verify(this.enderecoRepository).deleteById(enderecoId);
        Mockito.verify(this.clienteRepository).deleteById(clienteId);
    }

    @Test
    public void whenDeleteAClienteItShouldGetAnExceptionBecauseClienteIdIsWrong(){
        //arrange
        Long clienteId = 1l;

        Mockito.when(this.clienteRepository.findById(clienteId)).thenReturn(Optional.empty());
        //act
        Assertions.assertThatThrownBy(() -> this.clienteService.deleteClienteById(clienteId))
        //assert
            .isExactlyInstanceOf(ClienteNotFoundException.class);
    }
}