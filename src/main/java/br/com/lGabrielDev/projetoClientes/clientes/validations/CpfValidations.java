package br.com.lGabrielDev.projetoClientes.clientes.validations;

import br.com.lGabrielDev.projetoClientes.clientes.ClienteRepository;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldIsNotUniqueException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldLengthIsIncorrect;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldMustHaveOnlyNumbers;

public abstract class CpfValidations {
    
    // 'cpf' nao pode ser NULL
    // 'cpf' deve conter 11 charaters
    // 'cpf' deve possuir apenas numeros
    // 'cpf' deve ser unico
    
    // 'cpf' nao pode ser NULL
    public static Boolean cpfIsNotNull(String cpf){
        if(cpf == null || cpf.isBlank()){
            throw new FieldCannotBeNullException("cpf não pode ser NULL");
        }
        return true;
    }

    // 'cpf' deve conter 11 charaters
    public static Boolean cpfLengthIsCorrect(String cpf){
        if(cpf.length() != 11){
            throw new FieldLengthIsIncorrect("cpf deve conter 11 charaters");
        }
        return true;
    }

    // 'cpf' deve possuir apenas numeros
    public static Boolean cpfHasOnlyNumbers(String cpf){

        cpf.chars().forEach((currentCharacter) -> {
            if(!(Character.isDigit(currentCharacter))){
                throw new FieldMustHaveOnlyNumbers("cpf deve possuir apenas números!");
            }
        });
        return true;
    }

    // 'cpf' deve ser único
    public static Boolean cpfIsUnique(String cpf, ClienteRepository clienteRepository){
        clienteRepository.findByCpf(cpf).ifPresent((cliente) -> {
            throw new FieldIsNotUniqueException("cpf deve ser único. Já existe um cliente com esse CPF.");
        });
        return true;
    }
    
    //Verificamos se o campo 'cpf' passou por todas as validacoes
    public static Boolean allValidationsAreCorrect(String cpf, ClienteRepository clienteRepository){
        cpfIsNotNull(cpf);
        cpfLengthIsCorrect(cpf);
        cpfHasOnlyNumbers(cpf);
        cpfIsUnique(cpf, clienteRepository);
        return true;
    }

    public static Boolean allValidationsAreCorrect(String cpf, ClienteRepository clienteRepository, Boolean atualizarCliente){
        if(cpf != null){
            cpfIsNotNull(cpf);
            cpfLengthIsCorrect(cpf);
            cpfHasOnlyNumbers(cpf);
            cpfIsUnique(cpf, clienteRepository);
        }
        return true;
    }
}