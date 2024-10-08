package br.com.lGabrielDev.projetoClientes.relatorio;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;
import java.io.File;

public class ManipuladorDoRelatorioValido {

    //attributes
    final String CAMINHO_RELATORIO = "./relatorios/cepsValidos.txt";
    final String CABECALHO = "id;cep;logradouro;numeroCasa;complemento;bairro;localidade;uf;estado;regiao;ddd";
    final File file = new File(CAMINHO_RELATORIO);

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
            throw new RuntimeException("Arquivo nao encontrado. O erro aconteceu no method 'criarArquivoESetarCabecalho()'");
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
    public void adicionarCepNoRelatorio(Endereco enderecoCompleto){
        String cep = enderecoCompleto.getCep();
        String messageToBeWrite = String.format("%d; '%s'; %s; %d; %s; %s; %s; %s; %s; %s; %s",enderecoCompleto.getId(), enderecoCompleto.getCep(), enderecoCompleto.getLogradouro(), enderecoCompleto.getNumeroCasa(), enderecoCompleto.getComplemento(), enderecoCompleto.getBairro(), enderecoCompleto.getLocalidade(), enderecoCompleto.getUf(), enderecoCompleto.getEstado(), enderecoCompleto.getRegiao(), enderecoCompleto.getDdd());

        if(!(this.file.exists())){
            criarArquivoESetarCabecalho();
        }
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.file, true));
            if(!(cepJaExiste(cep))){
                bw.write(messageToBeWrite);
                bw.newLine();
            }
            bw.close();
        }
        catch(IOException e){
            throw new RuntimeException("Arquivo nao encontrado. O erro aconteceu no method 'adicionarCepNoRelatorio()'");
        }
    }

    //getters
    public String getCAMINHO_RELATORIO() {
        return CAMINHO_RELATORIO;
    }

    public String getCABECALHO() {
        return CABECALHO;
    }

    public File getFile() {
        return file;
    }
}