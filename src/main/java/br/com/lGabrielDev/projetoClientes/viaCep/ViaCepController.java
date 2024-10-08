package br.com.lGabrielDev.projetoClientes.viaCep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;

@FeignClient(name = "viacep", url = "viacep.com.br/ws")
public interface ViaCepController {
    
    @GetMapping("/{cep}/json")
    public Endereco getEnderecoByCep(@PathVariable("cep") String cep);
}