package br.com.lGabrielDev.projetoClientes.clientes.validations;

import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;

public abstract class NumeroCasaValidations {
    
    // "numero da casa" nao pode ser NULL
    public static Boolean numeroCasaIsNotNull(Integer numeroCasa){
        if(numeroCasa == null){
            throw new FieldCannotBeNullException("numeroCasa não pode ser NULL!");
        }
        return true;
    }

    //Verificamos se o campo 'numeroCasa' passou por todas as validacoes
    public static Boolean allValidationsAreCorrect(Integer numeroCasa){
        numeroCasaIsNotNull(numeroCasa);
        return true;
    }

    public static Boolean allValidationsAreCorrect(Integer numeroCasa, Boolean atualizarNumero){
        if(numeroCasa != null){
            numeroCasaIsNotNull(numeroCasa);
        }
        return true;
    }
}