package br.com.lGabrielDev.projetoClientes.clientes.atualizar;

import static org.mockito.ArgumentMatchers.anyString;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.lGabrielDev.projetoClientes.clientes.Cliente;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteCreateDto;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;
import br.com.lGabrielDev.projetoClientes.viaCep.ViaCepController;

@ExtendWith(MockitoExtension.class)
public class AtualizarDadosTest {

    //attributes
    private Cliente clienteAntigo;
    private ClienteCreateDto clienteCreateDto;
    private Endereco enderecoAntigo;
    private Endereco enderecoViaCep;

    @Mock
    private ViaCepController viacep;

    @InjectMocks
    private AtualizarDados atualizarDados = Mockito.mock(AtualizarDados.class); // mockamos a class abstrata

    //setup
    @BeforeEach
    public void setup(){

        this.clienteAntigo = new Cliente();
        clienteAntigo.setId(1l);
        clienteAntigo.setNome("goku");
        clienteAntigo.setDataDeNascimento(LocalDate.of(1990, 1, 1));
        clienteAntigo.setCpf("12345678912");
        clienteAntigo.setEmail("goku@gmail.com");
        clienteAntigo.setTelefone("912349874");
        
        this.clienteCreateDto = new ClienteCreateDto();
        clienteCreateDto.setNome("naruto");
        clienteCreateDto.setDataDeNascimento(LocalDate.of(1990, 1, 2));
        clienteCreateDto.setCpf("00000000000");
        clienteCreateDto.setEmail("naruto@gmail.com");
        clienteCreateDto.setTelefone("911111111");

        this.enderecoAntigo = new Endereco();
        this.enderecoAntigo.setId(1L);
        this.enderecoAntigo.setCep("12345678");
        this.enderecoAntigo.setLogradouro("Rua random");
        this.enderecoAntigo.setNumeroCasa(123);
        this.enderecoAntigo.setComplemento("Apto 101");
        this.enderecoAntigo.setBairro("Bairro random");
        this.enderecoAntigo.setLocalidade("Cidade random");
        this.enderecoAntigo.setUf("SP");
        this.enderecoAntigo.setEstado("São Paulo");
        this.enderecoAntigo.setRegiao("Sudeste");
        this.enderecoAntigo.setDdd("11");

        this.enderecoViaCep = new Endereco();
        this.enderecoViaCep.setId(null);
        this.enderecoViaCep.setCep("22222222");
        this.enderecoViaCep.setLogradouro("Rua diferente");
        this.enderecoViaCep.setNumeroCasa(null);
        this.enderecoViaCep.setComplemento("Apto diferente");
        this.enderecoViaCep.setBairro("Bairro diferente");
        this.enderecoViaCep.setLocalidade("Cidade diferente");
        this.enderecoViaCep.setUf("MG");
        this.enderecoViaCep.setEstado("Minas Gerais");
        this.enderecoViaCep.setRegiao("Sudeste");
        this.enderecoViaCep.setDdd("31");

    }


    // ================ atualizarCliente() ================
    @Test
    public void shouldUpdateAClienteWithAllFieldsFilled(){       
        //act
        Cliente clienteNovo = AtualizarDados.atualizarCliente(clienteAntigo, clienteCreateDto);
        //assert
        Assertions.assertThat(clienteNovo).isNotNull();
        Assertions.assertThat(clienteNovo.getNome()).isEqualTo("naruto");
        Assertions.assertThat(clienteNovo.getDataDeNascimento()).isEqualTo(LocalDate.of(1990, 1, 2));
        Assertions.assertThat(clienteNovo.getCpf()).isEqualTo("00000000000");
        Assertions.assertThat(clienteNovo.getEmail()).isEqualTo("naruto@gmail.com");
        Assertions.assertThat(clienteNovo.getTelefone()).isEqualTo("911111111");
    }

    @Test
    public void shouldUpdateClienteButKeepOldNome(){       
        //arrange
        this.clienteCreateDto.setNome(null);
        //act
        Cliente clienteNovo = AtualizarDados.atualizarCliente(clienteAntigo, clienteCreateDto);
        //assert
        Assertions.assertThat(clienteNovo.getNome()).isEqualTo("goku");
    }

    @Test
    public void shouldUpdateClienteButKeepOldDataDeNascimento(){       
        //arrange
        this.clienteCreateDto.setDataDeNascimento(null);
        //act
        Cliente clienteNovo = AtualizarDados.atualizarCliente(clienteAntigo, clienteCreateDto);
        //assert
        Assertions.assertThat(clienteNovo.getDataDeNascimento()).isEqualTo(LocalDate.of(1990, 1, 1));
    }

    @Test
    public void shouldUpdateClienteButKeepOldCpf(){       
        //arrange
        this.clienteCreateDto.setCpf(null);
        //act
        Cliente clienteNovo = AtualizarDados.atualizarCliente(clienteAntigo, clienteCreateDto);
        //assert
        Assertions.assertThat(clienteNovo.getCpf()).isEqualTo("12345678912");
    }

    @Test
    public void shouldUpdateClienteButKeepOldEmail(){       
        //arrange
        this.clienteCreateDto.setEmail(null);
        //act
        Cliente clienteNovo = AtualizarDados.atualizarCliente(clienteAntigo, clienteCreateDto);
        //assert
        Assertions.assertThat(clienteNovo.getEmail()).isEqualTo("goku@gmail.com");
    }

    @Test
    public void shouldUpdateClienteButKeepOldTelefone(){       
        //arrange
        this.clienteCreateDto.setTelefone(null);
        //act
        Cliente clienteNovo = AtualizarDados.atualizarCliente(clienteAntigo, clienteCreateDto);
        //assert
        Assertions.assertThat(clienteNovo.getTelefone()).isEqualTo("912349874");
    }

    @Test
    public void shouldNotUpdateAClienteBecauseFieldsAreNull(){     
        //arrange
        this.clienteCreateDto.setNome(null);
        this.clienteCreateDto.setDataDeNascimento(null);
        this.clienteCreateDto.setCpf(null);
        this.clienteCreateDto.setEmail(null);
        this.clienteCreateDto.setTelefone(null);  
        //act
        Cliente clienteNovo = AtualizarDados.atualizarCliente(clienteAntigo, clienteCreateDto);
        //assert
        Assertions.assertThat(clienteNovo).isNotNull();
        Assertions.assertThat(clienteNovo.getNome()).isEqualTo("goku");
        Assertions.assertThat(clienteNovo.getDataDeNascimento()).isEqualTo(LocalDate.of(1990, 1, 1));
        Assertions.assertThat(clienteNovo.getCpf()).isEqualTo("12345678912");
        Assertions.assertThat(clienteNovo.getEmail()).isEqualTo("goku@gmail.com");
        Assertions.assertThat(clienteNovo.getTelefone()).isEqualTo("912349874");
    }

    // ================ atualizarEndereco() ================
    @Test
    public void shouldUpdateEnderecoWithAllFieldsFilled(){       
        //arrange
        this.clienteCreateDto.setCep("22222222");
        this.clienteCreateDto.setNumeroCasa(222);
        Mockito.when(this.viacep.getEnderecoByCep(anyString())).thenReturn(this.enderecoViaCep);
        //act
        Endereco enderecoNovo = AtualizarDados.atualizarEndereco(this.enderecoAntigo, clienteCreateDto, this.viacep);
        //assert
        Assertions.assertThat(enderecoNovo.getId()).isEqualTo(1l);
        Assertions.assertThat(enderecoNovo.getCep()).isEqualTo("22222222");
        Assertions.assertThat(enderecoNovo.getLogradouro()).isEqualTo("Rua diferente");
        Assertions.assertThat(enderecoNovo.getNumeroCasa()).isEqualTo(222);
        Assertions.assertThat(enderecoNovo.getComplemento()).isEqualTo("Apto diferente");
        Assertions.assertThat(enderecoNovo.getBairro()).isEqualTo("Bairro diferente");
        Assertions.assertThat(enderecoNovo.getLocalidade()).isEqualTo("Cidade diferente");
        Assertions.assertThat(enderecoNovo.getUf()).isEqualTo("MG");
        Assertions.assertThat(enderecoNovo.getEstado()).isEqualTo("Minas Gerais");
        Assertions.assertThat(enderecoNovo.getRegiao()).isEqualTo("Sudeste");
        Assertions.assertThat(enderecoNovo.getDdd()).isEqualTo("31");
    }

    @Test
    public void shouldUpdateEnderecoButKeepOldNumeroCasa(){       
        //arrange
        this.clienteCreateDto.setCep("22222222");
        Mockito.when(this.viacep.getEnderecoByCep(anyString())).thenReturn(this.enderecoViaCep);
        //act
        Endereco enderecoNovo = AtualizarDados.atualizarEndereco(this.enderecoAntigo, clienteCreateDto, this.viacep);
        //assert
        Assertions.assertThat(enderecoNovo.getNumeroCasa()).isEqualTo(123);
        Assertions.assertThat(enderecoNovo.getId()).isEqualTo(1l);
        Assertions.assertThat(enderecoNovo.getCep()).isEqualTo("22222222");
        Assertions.assertThat(enderecoNovo.getLogradouro()).isEqualTo("Rua diferente");
        Assertions.assertThat(enderecoNovo.getComplemento()).isEqualTo("Apto diferente");
        Assertions.assertThat(enderecoNovo.getBairro()).isEqualTo("Bairro diferente");
        Assertions.assertThat(enderecoNovo.getLocalidade()).isEqualTo("Cidade diferente");
        Assertions.assertThat(enderecoNovo.getUf()).isEqualTo("MG");
        Assertions.assertThat(enderecoNovo.getEstado()).isEqualTo("Minas Gerais");
        Assertions.assertThat(enderecoNovo.getRegiao()).isEqualTo("Sudeste");
        Assertions.assertThat(enderecoNovo.getDdd()).isEqualTo("31");
    }

    @Test
    public void shouldUpdateEnderecoButKeepOldCep(){       
        //arrange
        this.clienteCreateDto.setNumeroCasa(222);
        //act
        Endereco enderecoNovo = AtualizarDados.atualizarEndereco(this.enderecoAntigo, clienteCreateDto, this.viacep);
        //assert
        Assertions.assertThat(enderecoNovo.getNumeroCasa()).isEqualTo(222);
        Assertions.assertThat(enderecoNovo.getId()).isEqualTo(this.enderecoAntigo.getId());
        Assertions.assertThat(enderecoNovo.getCep()).isEqualTo(this.enderecoAntigo.getCep());
        Assertions.assertThat(enderecoNovo.getLogradouro()).isEqualTo(this.enderecoAntigo.getLogradouro());
        Assertions.assertThat(enderecoNovo.getComplemento()).isEqualTo(this.enderecoAntigo.getComplemento());
        Assertions.assertThat(enderecoNovo.getBairro()).isEqualTo(this.enderecoAntigo.getBairro());
        Assertions.assertThat(enderecoNovo.getLocalidade()).isEqualTo(this.enderecoAntigo.getLocalidade());
        Assertions.assertThat(enderecoNovo.getUf()).isEqualTo(this.enderecoAntigo.getUf());
        Assertions.assertThat(enderecoNovo.getEstado()).isEqualTo(this.enderecoAntigo.getEstado());
        Assertions.assertThat(enderecoNovo.getRegiao()).isEqualTo(this.enderecoAntigo.getRegiao());
        Assertions.assertThat(enderecoNovo.getDdd()).isEqualTo(this.enderecoAntigo.getDdd());
    }

    @Test
    public void shouldKeepWithOldEnderecoBecauseFieldsAreNull(){       
        //act
        Endereco enderecoNovo = AtualizarDados.atualizarEndereco(this.enderecoAntigo, clienteCreateDto, this.viacep);
        //assert
        Assertions.assertThat(enderecoNovo).isEqualTo(this.enderecoAntigo);
        // Assertions.assertThat(enderecoNovo.getNumeroCasa()).isEqualTo(this.enderecoAntigo.getNumeroCasa());
        // Assertions.assertThat(enderecoNovo.getId()).isEqualTo(this.enderecoAntigo.getId());
        // Assertions.assertThat(enderecoNovo.getCep()).isEqualTo(this.enderecoAntigo.getCep());
        // Assertions.assertThat(enderecoNovo.getLogradouro()).isEqualTo(this.enderecoAntigo.getLogradouro());
        // Assertions.assertThat(enderecoNovo.getComplemento()).isEqualTo(this.enderecoAntigo.getComplemento());
        // Assertions.assertThat(enderecoNovo.getBairro()).isEqualTo(this.enderecoAntigo.getBairro());
        // Assertions.assertThat(enderecoNovo.getLocalidade()).isEqualTo(this.enderecoAntigo.getLocalidade());
        // Assertions.assertThat(enderecoNovo.getUf()).isEqualTo(this.enderecoAntigo.getUf());
        // Assertions.assertThat(enderecoNovo.getEstado()).isEqualTo(this.enderecoAntigo.getEstado());
        // Assertions.assertThat(enderecoNovo.getRegiao()).isEqualTo(this.enderecoAntigo.getRegiao());
        // Assertions.assertThat(enderecoNovo.getDdd()).isEqualTo(this.enderecoAntigo.getDdd());
    }
}