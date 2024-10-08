package br.com.lGabrielDev.projetoClientes.clientes.validations;

import br.com.lGabrielDev.projetoClientes.clientes.ClienteRepository;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.email.EmailIsMissingException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldIsNotUniqueException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldLengthIsIncorrect;

public abstract class EmailValidations {
    
    // 'email' nao pode ser NULL - CHECK
    // 'email' deve possuir '@' e '.com' - CHECK
    // 'email' deve ter no maximo  100 characters - CHECK
    // 'email' deve ser unico - CHECK

    // 'email' nao pode ser NULL
    public static Boolean emailIsNotNull(String email){
        if(email == null || email.isBlank()){
            throw new FieldCannotBeNullException("email não pode ser NULL!");
        }
        return true;
    }

    // 'email' deve possuir '@' e '.com'
    public static Boolean emailHasDotCom(String email){
        String stringFaltando = "";

        if(!(email.contains("@")) || !(email.contains(".com"))){
            if(!(email.contains("@")) ){
                stringFaltando = "@";
            }
            else{
                stringFaltando = ".com";
            }
            throw new EmailIsMissingException(String.format("email deve possuir '%s'", stringFaltando));
        }
        return true;
    }

    // 'email' deve ter no maximo 100 characters
     public static Boolean emailLengthIsCorrect(String name){
        if(name.length() > 100){
            throw new FieldLengthIsIncorrect("nome deve ter até 100 characters");
        }
        return true;
    }

    // 'email' deve ser único
    public static Boolean emailIsUnique(String email, ClienteRepository clienteRepository){
        clienteRepository.findByEmail(email).ifPresent((cliente) -> {
            throw new FieldIsNotUniqueException("email deve ser único. Já existe um cliente com esse email.");
        });
        return true;
    }

    //Verificamos se o campo 'email' passou por todas as validacoes
    public static Boolean allValidationsAreCorrect(String email, ClienteRepository clienteRepository){
        emailIsNotNull(email);
        emailLengthIsCorrect(email);
        emailHasDotCom(email);
        emailIsUnique(email, clienteRepository);
        return true;
    }
    
    public static Boolean allValidationsAreCorrect(String email, ClienteRepository clienteRepository, Boolean atualizarCliente){
        if(email != null){
            emailIsNotNull(email);
            emailLengthIsCorrect(email);
            emailHasDotCom(email);
            emailIsUnique(email, clienteRepository);
        }
        return true;
    }
}