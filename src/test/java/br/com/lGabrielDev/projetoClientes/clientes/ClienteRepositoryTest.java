package br.com.lGabrielDev.projetoClientes.clientes;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;
import br.com.lGabrielDev.projetoClientes.endereco.EnderecoRepository;

@DataJpaTest
public class ClienteRepositoryTest {
    
    //attributes
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Cliente cliente;
    private Endereco endereco;

    //setup
    @BeforeEach
    public void setup(){
        this.endereco = new Endereco();
        this.endereco.setDdd("11");
        this.endereco.setUf("SP");
        
        this.cliente = new Cliente();
        this.cliente.setNome("goku");
        this.cliente.setEmail("goku@gmail.com");
        this.cliente.setTelefone("912345678");
        this.cliente.setCpf("123");
        this.cliente.setEnderecoCompleto(this.endereco);

        this.enderecoRepository.save(this.endereco);
        this.endereco.setCliente(cliente);
        this.clienteRepository.save(this.cliente);
    }

    // ========= findByCpf =========
    @Test
    public void itShouldFindByCpfSuccessfully(){
        //arrange
        String cpf = "123";
        //act
        Optional<Cliente> methodResult = this.clienteRepository.findByCpf(cpf);
        //assert
        Assertions.assertThat(methodResult).isPresent();
        Assertions.assertThat(methodResult.get().getCpf()).isEqualTo(this.cliente.getCpf());
    }

    @Test
    public void itShouldReturnEmptyBecauseCpfIsWrong(){
        //arrange
        String cpf = "111";
        //act
        Optional<Cliente> methodResult = this.clienteRepository.findByCpf(cpf);
        //assert
        Assertions.assertThat(methodResult).isEmpty(); 
    }

    // ========= findByEmail =========
    @Test
    public void itShouldFindByEmailSuccessfully(){
        //arrange
        String email = "goku@gmail.com";
        //act
        Optional<Cliente> methodResult = this.clienteRepository.findByEmail(email);
        //assert
        Assertions.assertThat(methodResult).isPresent();
        Assertions.assertThat(methodResult.get().getEmail()).isEqualTo(this.cliente.getEmail());
    }

    @Test
    public void itShouldReturnEmptyBecauseEmailIsWrong(){
        //arrange
        String email = "some@email.com";
        //act
        Optional<Cliente> methodResult = this.clienteRepository.findByEmail(email);
        //assert
        Assertions.assertThat(methodResult).isEmpty(); 
    }

    // ========= findByTelefone =========
    @Test
    public void itShouldFindByTelefoneSuccessfully(){
        //arrange
        String telefone = "912345678";
        //act
        Optional<Cliente> methodResult = this.clienteRepository.findByTelefone(telefone);
        //assert
        Assertions.assertThat(methodResult).isPresent();
        Assertions.assertThat(methodResult.get().getTelefone()).isEqualTo(this.cliente.getTelefone());
    }

    @Test
    public void itShouldReturnEmptyBecauseTelefoneIsWrong(){
        //arrange
        String telefone = "000";
        //act
        Optional<Cliente> methodResult = this.clienteRepository.findByEmail(telefone);
        //assert
        Assertions.assertThat(methodResult).isEmpty(); 
    }

    // ========= all clientes ordered by cliente #ID =========
    @Test
    public void itShouldReturnAListOf2ClientesOrderedByIdAsc(){
        //arrange
        Cliente cliente2 = new Cliente();
        cliente2.setNome("naruto");
        this.clienteRepository.save(cliente2);

        //act
        List<Cliente> methodResult = this.clienteRepository.findAllOrderByIdAsc();
        //assert
        Assertions.assertThat(methodResult.size()).isEqualTo(2);
        Assertions.assertThat(methodResult.get(0).getNome()).isEqualTo("goku");
        Assertions.assertThat(methodResult.get(1).getNome()).isEqualTo("naruto");
    }

    // ========= findAllFilterByDdd =========
    @Test
    public void itShouldReturnAListOf1ClienteFrom11Ddd(){
        //arrange
        Integer ddd = 11;
        //act
        List<Cliente> methodResult = this.clienteRepository.findAllFilterByDdd(ddd);
        //assert
        Assertions.assertThat(methodResult.size()).isEqualTo(1);
        Assertions.assertThat(methodResult.get(0).getNome()).isEqualTo("goku");
    }

    @Test
    public void itShouldReturnAnEmptyListBecauseThereIsNoClienteFrom31Ddd(){
        //arrange
        Integer ddd = 31;
        //act
        List<Cliente> methodResult = this.clienteRepository.findAllFilterByDdd(ddd);
        //assert
        Assertions.assertThat(methodResult.size()).isEqualTo(0);
    }

    // ========= findAllFilterByUf =========
    @Test
    public void itShouldReturnAListOf1ClienteFromSPUf(){
        //arrange
        String uf = "SP";
        //act
        List<Cliente> methodResult = this.clienteRepository.findAllFilterByUf(uf);
        //assert
        Assertions.assertThat(methodResult.size()).isEqualTo(1);
        Assertions.assertThat(methodResult.get(0).getNome()).isEqualTo("goku");
    }

    @Test
    public void itShouldReturnAnEmptyListBecauseThereIsNoClienteFromMGUf(){
        //arrange
        String uf = "MG";
        //act
        List<Cliente> methodResult = this.clienteRepository.findAllFilterByUf(uf);
        //assert
        Assertions.assertThat(methodResult.size()).isEqualTo(0);
    }

    // ========= findAllFilterByDddAndUf =========
    @Test
    public void itShouldReturnAListOf1ClienteFromSPUfAndDdd11(){
        //arrange
        Integer ddd = 11;
        String uf = "SP";
        //act
        List<Cliente> methodResult = this.clienteRepository.findAllFilterByDddAndUf(ddd, uf);
        //assert
        Assertions.assertThat(methodResult.size()).isEqualTo(1);
        Assertions.assertThat(methodResult.get(0).getNome()).isEqualTo("goku");
    }

    @Test
    public void itShouldReturnAnEmptyListBecauseThereIsNoClienteFromMGUfAnd31Ddd(){
        //arrange
        Integer ddd = 31;
        String uf = "MG";
        //act
        List<Cliente> methodResult = this.clienteRepository.findAllFilterByDddAndUf(ddd, uf);
        //assert
        Assertions.assertThat(methodResult.size()).isEqualTo(0);
    }
}