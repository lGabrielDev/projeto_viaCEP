package br.com.lGabrielDev.projetoClientes.endereco.validations;

import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldLengthIsIncorrect;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldMustHaveOnlyNumbers;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;
import br.com.lGabrielDev.projetoClientes.endereco.exceptions.InvalidCepException;
import br.com.lGabrielDev.projetoClientes.relatorio.ManipuladorDoRelatorioInvalido;
import br.com.lGabrielDev.projetoClientes.viaCep.ViaCepController;

public abstract class CepValidations {
    
    // 'CEP' nao pode ser NULL - CHECK
    // 'CEP' deve ter exatamente 8 characters - CHECK
    // 'CEP' deve conter apenas numeros - CHECk
    // 'CEP' deve existir - CHECK

    //cep nao pode ser NULL
    public static Boolean cepIsNotNull(String cep){
        if(cep == null || cep.isBlank() ){
            throw new FieldCannotBeNullException("cep nao pode ser NULL!");
        }
        return true;
    }

    //Deve ter exatamente 8 characters
    public static Boolean cepLengthIsEqual8(String cep){
        if(cep.length() != 8){
            throw new FieldLengthIsIncorrect("cep precisa ter exatamente 8 characters. Nem menos, nem mais.");
        }
        return true;
    }

    //Deve conter apenas numeros
    public static Boolean cepHasOnlyNumbers(String cep){
        cep.chars().forEach((currentCharacter) -> {
            if(!(Character.isDigit(currentCharacter))){
                throw new FieldMustHaveOnlyNumbers("cep deve possuir apenas números!");
            }
        });
        return true;
    }

    //CEP deve existir
    public static Boolean cepExists(String cep, ViaCepController viaCepController){
        Endereco endereco = viaCepController.getEnderecoByCep(cep);
        String bairro = endereco.getBairro();

        if(bairro == null){
            // adicionamos esse cep no relatorio dos ceps inválidos
            ManipuladorDoRelatorioInvalido manipuladorArquivo = new ManipuladorDoRelatorioInvalido();
            manipuladorArquivo.adicionarCepNoRelatorio(cep);
          
            throw new InvalidCepException("Cep inválido");
        }
        return true;
    }

    //Verificamos se o CEP passou por todas as validacoes
    public static Boolean allValidationsAreCorrect(String cep, ViaCepController viaCepController){
        CepValidations.cepIsNotNull(cep);
        CepValidations.cepLengthIsEqual8(cep);
        CepValidations.cepHasOnlyNumbers(cep);
        CepValidations.cepExists(cep, viaCepController);
        return true;
    }

    public static Boolean allValidationsAreCorrect(String cep, ViaCepController viaCepController, Boolean atualizarCliente){
        if(cep != null){
            CepValidations.cepIsNotNull(cep);
            CepValidations.cepLengthIsEqual8(cep);
            CepValidations.cepHasOnlyNumbers(cep);
            CepValidations.cepExists(cep, viaCepController);
        }
        return true;
    }
}