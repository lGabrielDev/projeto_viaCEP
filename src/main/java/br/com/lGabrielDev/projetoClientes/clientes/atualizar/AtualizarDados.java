package br.com.lGabrielDev.projetoClientes.clientes.atualizar;

import br.com.lGabrielDev.projetoClientes.clientes.Cliente;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteCreateDto;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;
import br.com.lGabrielDev.projetoClientes.viaCep.ViaCepController;

public abstract class AtualizarDados {
    // Atualiza os dados do cliente
    // Ao atualizar, o cliente pode escolher quais campos deseja altera.
    // Ao deixar algum campo null, esse campo vai continuar com seu valor antigo.
    public static Cliente atualizarCliente(Cliente clienteAntigo, ClienteCreateDto clienteCreateDto){
        Cliente clienteNovo = new Cliente(clienteAntigo);

        if(clienteCreateDto.getNome() != null){
            clienteNovo.setNome(clienteCreateDto.getNome());
        }
        if(clienteCreateDto.getDataDeNascimento() != null){
            clienteNovo.setDataDeNascimento(clienteCreateDto.getDataDeNascimento());
        }
        if(clienteCreateDto.getCpf() != null){
            clienteNovo.setCpf(clienteCreateDto.getCpf());
        }
        if(clienteCreateDto.getEmail() != null){
            clienteNovo.setEmail((clienteCreateDto.getEmail()));
        }
        if(clienteCreateDto.getTelefone() != null){
            clienteNovo.setTelefone((clienteCreateDto.getTelefone()));
        }
        return clienteNovo;
    }
    
    public static Endereco atualizarEndereco(Endereco enderecoAntigo, ClienteCreateDto clienteCreateDto, ViaCepController viacep){
        Endereco enderecoNovo = new Endereco(enderecoAntigo);
   
        if(clienteCreateDto.getCep() != null){
            enderecoNovo = viacep.getEnderecoByCep(clienteCreateDto.getCep());
            enderecoNovo.setId(enderecoAntigo.getId());
            enderecoNovo.setNumeroCasa(enderecoAntigo.getNumeroCasa());
            enderecoNovo.setCep(clienteCreateDto.getCep()); //o cep que enviamos no body já vem sem o '-'
        }
        if(clienteCreateDto.getNumeroCasa() != null){
            enderecoNovo.setNumeroCasa(clienteCreateDto.getNumeroCasa());
        }
        return enderecoNovo;
    }
}