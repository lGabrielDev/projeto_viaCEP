package br.com.lGabrielDev.projetoClientes.relatorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;

public class ManipuladorDoRelatorioValidoTest {
    
    //attributes
    private ManipuladorDoRelatorioValido manipulador;
    private File relatorio;
    private Endereco endereco;

    @BeforeEach
    public void setup() throws Exception{
        this.manipulador = new ManipuladorDoRelatorioValido();
        this.relatorio = this.manipulador.getFile();
        relatorio.createNewFile();

        this.endereco = new Endereco();
        this.endereco.setId(1L);
        this.endereco.setCep("123");
        this.endereco.setLogradouro("Rua A");
        this.endereco.setNumeroCasa(100);
        this.endereco.setComplemento("Apto 1");
        this.endereco.setBairro("Bairro A");
        this.endereco.setLocalidade("Cidade A");
        this.endereco.setUf("UF");
        this.endereco.setEstado("Estado A");
        this.endereco.setRegiao("Região A");
        this.endereco.setDdd("11");
    }

    @AfterEach
    public void deleteFile(){

        if(this.relatorio.exists()){
            this.relatorio.delete();
        }
    }

    //cria arquivo e cria cabecalho
    @Test
    public void itShoulCreateAFileAndAHeaderSuccessfully(){
        //arrange
        String cabecalho = "";
        //act
        this.manipulador.criarArquivoESetarCabecalho();

        try{
            BufferedReader br = new BufferedReader(new FileReader(this.manipulador.getFile()));
            cabecalho = br.readLine();
            br.close();
        }
        catch(Exception e){
            throw new RuntimeException("Arquivo nao encontrado");
        }
        //assert
        Assertions.assertThat(this.manipulador.getFile().exists()).isTrue();
        Assertions.assertThat(cabecalho).isEqualTo(this.manipulador.getCABECALHO());
    }

    //verifica se o cep ja existe no relatorio
    @Test
    public void itShoulReturnTrueBecauseCepAlreadyExistsInTheFile(){
        //arrange
        String cep = "123";

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.relatorio));
            bw.write(cep);
            bw.close();
        }
        catch(Exception e){
            throw new RuntimeException("Arquivo nao encontrado");
        }
        //act
        Boolean cepJaExiste = this.manipulador.cepJaExiste(cep);
        //assert
        Assertions.assertThat(cepJaExiste).isTrue();   
    }

    @Test
    public void itShoulReturnFalseBecauseCepDoesNotExistsInTheFile(){
        //arrange
        String cep = "123";
        //act
        Boolean cepJaExiste = this.manipulador.cepJaExiste(cep);
        //assert
        Assertions.assertThat(cepJaExiste).isFalse();
    }

    //adicionamos o cep no relatorio
    //vamos verificar se ele entra na condicao de "criarArquivoESetarCabecalho()";
    @Test
    public void itShouldCreateAHeaderAndAddACep(){
        //arrange
        this.relatorio.delete();
        //act
        this.manipulador.adicionarCepNoRelatorio(this.endereco);
        //assert
        Assertions.assertThat(this.relatorio.exists()).isTrue();   
    }

    //testamos uma situacao onde o cep ainda não foi registrado no relatorio
    //logo, o method deve cadastrar esse cep no relatorio
    @Test
    public void itShouldAddACep(){
        //arrange
        String fileContent = "";
        //act
        this.manipulador.adicionarCepNoRelatorio(this.endereco);

        try{
            BufferedReader br = new BufferedReader(new FileReader(this.manipulador.getFile()));
            fileContent = br.readLine();
            br.close();
        }
        catch(Exception e){
            throw new RuntimeException("Arquivo nao encontrado");
        }

        //assert
        Assertions.assertThat(fileContent).contains(endereco.getCep());
    }

    //testamos a situacao onde o cep já foi registrado no relatorio
    @Test
    public void itShoulNotAddACep(){
        //arrange
        Endereco endereco = new Endereco();
        endereco.setCep("123");
        String fileContent = "";
 
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.manipulador.getFile()));
            bw.write(endereco.getCep());
            bw.close();
        }
        catch(Exception e){
            throw new RuntimeException("Arquivo nao encontrado");
        }
        //act
        this.manipulador.adicionarCepNoRelatorio(endereco);

        try{
            BufferedReader br = new BufferedReader(new FileReader(this.manipulador.getFile()));
            String nextLine;

            while((nextLine = br.readLine()) != null){
                fileContent += nextLine + "\n";
            }
            br.close();
        }
        catch(Exception e){
            throw new RuntimeException("Arquivo nao encontrado");
        }
        //assert
        Assertions.assertThat(fileContent).containsOnlyOnce(endereco.getCep()); //afirmamos que o cep foi registrado no arquivo somente uma vez
    }
}