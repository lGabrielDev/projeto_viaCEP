package br.com.lGabrielDev.projetoClientes.clientes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteCreateDto;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteFullDto;
import br.com.lGabrielDev.projetoClientes.clientes.atualizar.AtualizarDados;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.id.ClienteNotFoundException;
import br.com.lGabrielDev.projetoClientes.clientes.validations.ClienteValidations;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;
import br.com.lGabrielDev.projetoClientes.endereco.EnderecoRepository;
import br.com.lGabrielDev.projetoClientes.endereco.validations.CepValidations;
import br.com.lGabrielDev.projetoClientes.relatorio.ManipuladorDoRelatorioValido;
import br.com.lGabrielDev.projetoClientes.viaCep.ViaCepController;

@Service
public class ClienteService {
    
    //attributes
    private ClienteRepository clienteRepository;
    private EnderecoRepository enderecoRepository;
    private ViaCepController viaCep;

    //constructors
    public ClienteService(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, ViaCepController viaCep){
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.viaCep = viaCep;
    }

    //==================== CREATE ====================
    public ClienteFullDto createCliente(ClienteCreateDto clienteCreateDto){
       
        //validations
        ClienteValidations.allValidationsAreCorrect(clienteCreateDto, this.clienteRepository);
        CepValidations.allValidationsAreCorrect(clienteCreateDto.getCep(), this.viaCep);
        
        Endereco enderecoCompleto = this.viaCep.getEnderecoByCep(clienteCreateDto.getCep());
        enderecoCompleto.setNumeroCasa(clienteCreateDto.getNumeroCasa());
        enderecoCompleto.setCep(clienteCreateDto.getCep()); //o cep que enviamos no body já vem sem o '-'

        this.enderecoRepository.save(enderecoCompleto);

        Cliente cliente = new Cliente(clienteCreateDto);
        //bilateralidade
        cliente.setEnderecoCompleto(enderecoCompleto);
        enderecoCompleto.setCliente(cliente);

        this.clienteRepository.save(cliente);
        
        //adicionamos esse cep no relatorio de 'ceps válidos'
        ManipuladorDoRelatorioValido manipuladorArquivos = new ManipuladorDoRelatorioValido();
        manipuladorArquivos.adicionarCepNoRelatorio(enderecoCompleto);
        return new ClienteFullDto(cliente);
    }
    
    //==================== UPDATE ====================
    public ClienteFullDto updateCliente(Long clienteId, ClienteCreateDto clienteCreateDto){
       
        //validations
        ClienteValidations.allValidationsAreCorrect(clienteCreateDto, this.clienteRepository, clienteId);
        CepValidations.allValidationsAreCorrect(clienteCreateDto.getCep(), this.viaCep, true);

        Cliente clienteAntigo = this.clienteRepository.findById(clienteId).get();
        Endereco enderecoAntigo = this.enderecoRepository.findById(clienteAntigo.getEnderecoCompleto().getId()).get();

        Cliente clienteNovo = AtualizarDados.atualizarCliente(clienteAntigo, clienteCreateDto);
        this.clienteRepository.save(clienteNovo);

        Endereco enderecoNovo = AtualizarDados.atualizarEndereco(enderecoAntigo, clienteCreateDto, viaCep);
        this.enderecoRepository.save(enderecoNovo);

        //adicionamos esse cep no relatorio de 'ceps válidos'
        ManipuladorDoRelatorioValido manipuladorArquivos = new ManipuladorDoRelatorioValido();
        manipuladorArquivos.adicionarCepNoRelatorio(enderecoNovo);
        return new ClienteFullDto(clienteNovo);
    }

    //==================== READ by #ID ====================
    public ClienteFullDto getClienteById(Long clienteId){
        Cliente cliente = this.clienteRepository.findById(clienteId)
            .orElseThrow(() -> new ClienteNotFoundException(String.format("Cliente #%d não existe!", clienteId)));
        return new ClienteFullDto(cliente);
    }

    //==================== READ ALL ====================
    public List<ClienteFullDto> getAllClientes(Integer ddd, String uf){
        List<Cliente> clientes = new ArrayList<>();

        if(ddd == null && uf == null){
            clientes = this.clienteRepository.findAllOrderByIdAsc();
        }
        else if(ddd != null && uf == null){
            clientes = this.clienteRepository.findAllFilterByDdd(ddd);
        }
        else if(ddd == null && uf != null){
            clientes = this.clienteRepository.findAllFilterByUf(uf);
        }
        else{
            clientes = this.clienteRepository.findAllFilterByDddAndUf(ddd, uf);
        }

        List<ClienteFullDto> clientesDtos = clientes.stream()
            .map((cliente) -> new ClienteFullDto(cliente))
            .collect(Collectors.toList());
            
        return clientesDtos;
    }

    //==================== DELETE ====================
    public String deleteClienteById(Long clienteId){
        Cliente cliente = this.clienteRepository.findById(clienteId)
            .orElseThrow(() -> new ClienteNotFoundException(String.format("Cliente #%d não existe!", clienteId)));
        
        Long enderecoId = cliente.getEnderecoCompleto().getId();

        this.clienteRepository.deleteById(clienteId);
        this.enderecoRepository.deleteById(enderecoId);
        return String.format("Cliente #%d deletado com sucesso!", clienteId);
    }
}