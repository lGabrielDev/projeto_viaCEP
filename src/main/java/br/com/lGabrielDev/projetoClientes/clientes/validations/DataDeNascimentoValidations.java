package br.com.lGabrielDev.projetoClientes.clientes.validations;

import java.time.LocalDate;
import java.time.Period;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.dataDeNascimento.ClienteEhMenorDeIdadeException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;

public abstract class DataDeNascimentoValidations {

    // nao pode ser NULL - CHECK
    // deve ser maior de 18 anos - CHECK

   // nao pode ser NULL
    public static Boolean dataDeNascimentoIsNotNull(LocalDate dataDeNascimento){
        if(dataDeNascimento == null ){
            throw new FieldCannotBeNullException("dataDeNascimento não pode ser NULL!");
        }
        return true;
    }

    // deve ser maior de 18 anos
    public static Boolean clienteEhMaiorDe18(LocalDate dataDeNascimento){
        Period periodo = Period.between(dataDeNascimento, LocalDate.now());
        Integer age = periodo.getYears();

        if(age < 18){
            throw new ClienteEhMenorDeIdadeException("Cliente deve ser maior de idade");
        }
        return true;
    }

    //Verificamos se o campo 'data de nascimento' passou por todas as validacoes
    public static Boolean allValidationsAreCorrect(LocalDate dataDeNascimento){
        dataDeNascimentoIsNotNull(dataDeNascimento);
        clienteEhMaiorDe18(dataDeNascimento);
        return true;
    }

    public static Boolean allValidationsAreCorrect(LocalDate dataDeNascimento, Boolean atualizarCliente){
        if(dataDeNascimento != null){
            dataDeNascimentoIsNotNull(dataDeNascimento);
            clienteEhMaiorDe18(dataDeNascimento);
        }
        return true;
    }
}