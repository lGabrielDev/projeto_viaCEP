package br.com.lGabrielDev.projetoClientes.clientes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteCreateDto;
import br.com.lGabrielDev.projetoClientes.clientes.DTOs.ClienteFullDto;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.id.ClienteNotFoundException;
import br.com.lGabrielDev.projetoClientes.endereco.exceptions.InvalidCepException;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    
    //attributes
    @MockBean
    private ClienteService clienteService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objMapper;

    private ClienteCreateDto createDto;
    private ClienteFullDto fullDto;
    private ClienteFullDto fullDto2;

    @BeforeEach
    public void setup(){
        createDto = new ClienteCreateDto();
        createDto.setNome("goku");

        fullDto = new ClienteFullDto();
        fullDto.setNome("goku");
        fullDto.setDataDeNascimento(LocalDate.now());
        fullDto.setCreatedAt(LocalDateTime.now());
        fullDto.setLastUpdate(LocalDateTime.now());

        fullDto2 = new ClienteFullDto();
        fullDto2.setNome("naruto");
        fullDto2.setDataDeNascimento(LocalDate.now());
        fullDto2.setCreatedAt(LocalDateTime.now());
        fullDto2.setLastUpdate(LocalDateTime.now());
    }

    //==================== CREATE ====================
    @Test
    public void whenCreateItShouldGet201Successfully() throws Exception{
        //arrange
        String url = "/api/clientes";
        //act
        Mockito.when(this.clienteService.createCliente(Mockito.any(ClienteCreateDto.class))).thenReturn(fullDto);
        //assert
        this.mockMvc.perform(MockMvcRequestBuilders.post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.objMapper.writeValueAsString(createDto))
        )
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("goku"))
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void whenCreateItShouldGet400BecauseNameIsNull() throws Exception{
        //arrange
        String url = "/api/clientes";

        Mockito.when(this.clienteService.createCliente(Mockito.any(ClienteCreateDto.class))).thenThrow(new FieldCannotBeNullException("nome nao pode ser NULL"));
        //act
        this.mockMvc.perform(MockMvcRequestBuilders.post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.objMapper.writeValueAsString(createDto))
        )
        //assert
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("nome nao pode ser NULL"))
        .andDo(MockMvcResultHandlers.print());
    }

    //==================== UPDATE ====================
    @Test
    public void whenUpdateItShouldGet200Successfully() throws Exception{
        //arrange
        String url = "/api/clientes/{id}";
        Long clinteId = 1l;
    
        Mockito.when(this.clienteService.updateCliente(anyLong(), any(ClienteCreateDto.class))).thenReturn(this.fullDto);  //sempre que usar o mockito.any(), todos os parametros devem ser any()
        //act
        this.mockMvc.perform(MockMvcRequestBuilders
            .put(url, clinteId)
            .contentType(MediaType.APPLICATION_JSON) //tipo do objeto informado no @RequestBody
            .content(this.objMapper.writeValueAsString(this.createDto)) //informamos o DTO no @RequestBody
        )
        //assert
        .andExpect(MockMvcResultMatchers.status().isOk()) 
        .andExpect(MockMvcResultMatchers.content().json(this.objMapper.writeValueAsString(fullDto)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("goku"))
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void whenUpdateItShouldGet400BecauseCepIsWrong() throws Exception{
        //arrange
        String url = "/api/clientes/{id}";
        Long clienteId = 1l;

        Mockito.when(this.clienteService.updateCliente(anyLong(), any(ClienteCreateDto.class))).thenThrow(new InvalidCepException("cep nao existe"));
        //act
        this.mockMvc.perform(MockMvcRequestBuilders.put(url, clienteId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.objMapper.writeValueAsString(createDto))
        )
        //assert
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("cep nao existe"))
        .andDo(MockMvcResultHandlers.print());
    }

    //==================== READ by #ID ====================
    @Test
    public void itShouldGetACLienteByIdAndGet200() throws Exception{
        //arrange
        String url = "/api/clientes/{id}";
        Long clienteId = 1l;
        //act
        Mockito.when(this.clienteService.getClienteById(anyLong())).thenReturn(this.fullDto);
        //assert
        this.mockMvc.perform(MockMvcRequestBuilders.get(url, clienteId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("goku"))
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void whenFindByIdItShouldGet400BecauseClienteIdIsWrong() throws Exception{
        //arrange
        String url = "/api/clientes/{id}";
        Long clienteId = 1l;

        Mockito.when(this.clienteService.getClienteById(anyLong())).thenThrow(new ClienteNotFoundException("Cliente #ID nao existe"));
        //act
        this.mockMvc.perform(MockMvcRequestBuilders.get(url, clienteId))
        //assert
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("Cliente #ID nao existe"))
        .andDo(MockMvcResultHandlers.print());
    }

    //==================== READ all ====================
    //Testando o retorno da lista de Clientes, sem passar @RequestParams
    @Test
    public void itShouldGetAListOf2ClientesAndGet200WithNoParams() throws Exception{
        //arrange
        String url = "/api/clientes";
        Integer ddd = null;
        String uf = null;

        List<ClienteFullDto> clientes = List.of(fullDto, fullDto2);

        Mockito.when(this.clienteService.getAllClientes(ddd, uf)).thenReturn(clientes);
        //act
        this.mockMvc.perform( MockMvcRequestBuilders
            .get(url)
        )
        //assert
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(this.objMapper.writeValueAsString(clientes)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("goku"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("naruto"))
        .andDo(MockMvcResultHandlers.print());
    }

    //Testando o retorno da lista de Clientes, passando os @RequestParams
    @Test
    public void itShouldGetAListOf2ClientesAndGet200WithParams() throws Exception{
        //arrange
        String url = "/api/clientes";
        Integer ddd = 11;
        String uf = "SP";

        List<ClienteFullDto> clientes = List.of(fullDto, fullDto2);

        Mockito.when(this.clienteService.getAllClientes(ddd, uf)).thenReturn(clientes);
        //act
        this.mockMvc.perform( MockMvcRequestBuilders
            .get(url)
            .queryParam("ddd", ddd.toString())
            .queryParam("uf", uf)
        )
        //assert
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(this.objMapper.writeValueAsString(clientes)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("goku"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("naruto"))
        .andDo(MockMvcResultHandlers.print());
    }

    //==================== DELETE ====================
    @Test
    public void whenDeleteByIdItShouldGet200() throws Exception{
        //arrange
        String url = "/api/clientes/{clienteId}";
        Long clienteId = 1l;

        Mockito.when(this.clienteService.deleteClienteById(clienteId)).thenReturn("deletado com sucesso!");
        //act
        this.mockMvc.perform( MockMvcRequestBuilders
            .delete(url, clienteId)
        )
        //assert
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("deletado com sucesso!"))
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void whenDeleteByIdItShouldGet400BecauseClienteIdDoesNotExists() throws Exception{
        //arrange
        String url = "/api/clientes/{clienteId}";
        Long clienteId = 1l;

        Mockito.when(this.clienteService.deleteClienteById(clienteId)).thenThrow(new ClienteNotFoundException("Cliente #ID nao existe"));

        //act
        this.mockMvc.perform( MockMvcRequestBuilders
            .delete(url, clienteId)
        )
        //assert
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("Cliente #ID nao existe"))
        .andDo(MockMvcResultHandlers.print());
    }
}