package br.com.lGabrielDev.projetoClientes.relatorio;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class ManipuladorDoRelatorioInvalido {

    //attributes
    private final String CAMINHO_RELATORIO = "./relatorios/cepsInvalidos.txt";
    private final String CABECALHO = "CEPs invalidos";
    private final String ERROR_MESSAGE = "Erro ao executar o CEP";
    private File file = new File(CAMINHO_RELATORIO);

    //cria arquivo e cria cabecalho
    public void criarArquivoESetarCabecalho(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.file, false));
            file.createNewFile();
            bw.write(this.CABECALHO);
            bw.newLine();
            bw.close();
        }
        catch(IOException e){
            throw new RuntimeException("Arquivo nao encontrado. O erro aconteceu aqui 'criarArquivoESetarCabecalho()'");
        } 
    }

    //verifica se o cep ja existe no relatorio
    public Boolean cepJaExiste(String cep){
        try(BufferedReader br = new BufferedReader(new FileReader(this.file))){
            
            String currentLine;

            while((currentLine = br.readLine()) != null){
                if(currentLine.contains(cep)){
                    return true;
                }
            }
            br.close();
        }
        catch(IOException e){
            throw new RuntimeException("Arquivo nao encontrado. O erro aconteceu no method 'cepJaExiste()'");
        } 
        return false;
    }
    
    //adicionamos o cep no relatorio
    public void adicionarCepNoRelatorio(String cep){
        
        if(!(this.file.exists())){
            criarArquivoESetarCabecalho();
        }
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.file, true));
            if(!(cepJaExiste(cep))){
                bw.write(String.format("%s '%s'", this.ERROR_MESSAGE, cep)); //aspas simples para nao dar problema no excel
                bw.newLine();
            }
            bw.close();
        }
        catch(IOException e){
            throw new RuntimeException("Arquivo nao encontrado. O erro aconteceu aqui 'adicionarCepNoRelatorio()'");
        }
    }

    //getters
    public String getCAMINHO_RELATORIO() {
        return CAMINHO_RELATORIO;
    }

    public String getCABECALHO() {
        return CABECALHO;
    }

    public String getERROR_MESSAGE() {
        return ERROR_MESSAGE;
    }

    public File getFile() {
        return file;
    } 
}