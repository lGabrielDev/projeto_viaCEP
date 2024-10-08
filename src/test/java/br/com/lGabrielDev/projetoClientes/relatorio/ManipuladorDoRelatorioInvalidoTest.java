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

public class ManipuladorDoRelatorioInvalidoTest {
    
    //attributes
    private ManipuladorDoRelatorioInvalido manipulador;
    private File relatorio;

    @BeforeEach
    public void setup() throws Exception{
        this.manipulador = new ManipuladorDoRelatorioInvalido();
        this.relatorio = this.manipulador.getFile();
        relatorio.createNewFile();
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
        String cep = "123";
        //act
        this.manipulador.adicionarCepNoRelatorio(cep);
        //assert
        Assertions.assertThat(this.relatorio.exists()).isTrue();   
    }

    //testamos uma situacao onde o cep ainda não foi registrado no relatorio
    //logo, o method deve cadastrar esse cep no relatorio
    @Test
    public void itShouldAddACep(){
        //arrange
        String cep = "123";
        String fileContent = "";
        //act
        this.manipulador.adicionarCepNoRelatorio(cep);

        try{
            BufferedReader br = new BufferedReader(new FileReader(this.manipulador.getFile()));
            fileContent = br.readLine();
            br.close();
        }
        catch(Exception e){
            throw new RuntimeException("Arquivo nao encontrado");
        }

        //assert
        Assertions.assertThat(fileContent).contains(cep);
    }

    //testamos a situacao onde o cep já foi registrado no relatorio
    @Test
    public void itShoulNotAddACep(){
        //arrange
        String cep = "123";
        String fileContent = "";
 
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.manipulador.getFile()));
            bw.write(cep);
            bw.close();
        }
        catch(Exception e){
            throw new RuntimeException("Arquivo nao encontrado");
        }
        //act
        this.manipulador.adicionarCepNoRelatorio(cep);

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
        Assertions.assertThat(fileContent).containsOnlyOnce(cep); //afirmamos que o cep foi registrado no arquivo somente uma vez
    }
}