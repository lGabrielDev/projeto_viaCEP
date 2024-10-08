package br.com.lGabrielDev.projetoClientes.clientes.validations;

import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldLengthIsIncorrect;

public abstract class NameValidations {
    
    // 'nome' nao pode ser NULL - CHECK
    // 'nome' deve ter até 100 characters - CHECK

    // 'nome' nao pode ser NULL
    public static Boolean nameIsNotNull(String name){
        if(name == null || name.isBlank()){
            throw new FieldCannotBeNullException("nome não pode ser NULL");
        }
        return true;
    }

    // 'nome' deve ter até 100 characters
    public static Boolean nameLengthIsCorrect(String name){
        if(name.length() > 100){
            throw new FieldLengthIsIncorrect("nome deve ter até 100 characters");
        }
        return true;
    }
    
    //Verificamos se o 'name' passou por todas as validacoes
    public static Boolean allValidationsAreCorrect(String name){
        nameIsNotNull(name);
        nameLengthIsCorrect(name);
        return true;
    }

    public static Boolean allValidationsAreCorrect(String name, Boolean atualizarCliente){
        if(name != null){
            nameIsNotNull(name);
            nameLengthIsCorrect(name);
        }
        return true;
    }
}