package br.com.lGabrielDev.projetoClientes.clientes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteCreateDto;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteFullDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "clientes")
public class ClienteController {
    
    //attributes
    private ClienteService clienteService;

    //constructors
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }
    
    //==================== CREATE ====================
    @Operation(summary = "Cria um cliente", description = "Nenhum campo pode ser NULL")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Created - Cliente cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Algum campo não passou na validação", content = {@Content(examples = {})})
        }
    )
    @PostMapping("")
    public ResponseEntity<ClienteFullDto> createCliente(@RequestBody ClienteCreateDto clienteCreateDto){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(this.clienteService.createCliente(clienteCreateDto));
    }

    //==================== UPDATE ====================
    @Operation(summary = "Atualiza um cliente através do #ID", description = "Se algum campo for NULL, esse campo permanecerá com o valor anterior")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "OK - Cliente atualizado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Not Found - Informou um cliente #ID que não existe no banco", content = {@Content(examples = {})}),
            @ApiResponse(responseCode = "400", description = "Bad Request - Algum campo não passou na validação", content = {@Content(examples = {})})
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ClienteFullDto> updateCliente(@PathVariable("id") Long clienteId, @RequestBody ClienteCreateDto clienteCreateDto){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(this.clienteService.updateCliente(clienteId, clienteCreateDto));
    }

    //==================== READ by #ID ====================
    @Operation(summary = "Retorna um cliente através do #ID", description = "O #ID do cliente precisa existir no banco")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "OK - Cliente encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Not Found - Informou um cliente #ID que não existe no banco", content = {@Content(examples = {})}),
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClienteFullDto> getClienteById(@PathVariable("id") Long clienteId){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(this.clienteService.getClienteById(clienteId));
    }

    //==================== READ ALL ====================
    @Operation(summary = "Retorna todos os clientes cadastrados", description = "Aqui, você também pode filtrar por 'UF' e/ou 'DDD'")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "OK - Lista todos os clientes"),
        }
    )
    @GetMapping("")
    public ResponseEntity<List<ClienteFullDto>> getAllClientes(
        @RequestParam(required = false, name = "ddd") Integer ddd,
        @RequestParam(required = false, name = "uf") String uf
    ){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(this.clienteService.getAllClientes(ddd, uf));
    }

    //==================== DELETE ====================
    @Operation(summary = "Deleta um cliente através do #ID", description = "O #ID do cliente precisa existir no banco")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "OK - Cliente deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Not Found - Informou um cliente #ID que não existe no banco", content = {@Content(examples = {})}),
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClienteById(@PathVariable("id") Long clienteId){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(this.clienteService.deleteClienteById(clienteId));
    }
}