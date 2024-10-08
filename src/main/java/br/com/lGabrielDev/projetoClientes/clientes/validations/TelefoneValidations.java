package br.com.lGabrielDev.projetoClientes.clientes.validations;

import br.com.lGabrielDev.projetoClientes.clientes.ClienteRepository;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldIsNotUniqueException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldLengthIsIncorrect;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldMustHaveOnlyNumbers;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.telefone.TelefonedoesNotContainNine;

public abstract class TelefoneValidations {

    // "telefone" nao pode ser NULL - CHECK
    // "telefone" deve possuir 9 characters - CHECK
    // "telefone" deve possuir apenas números - CHECK
    // "telefone" deve possuir o digito 9 na frente - CHECK
    // "telefone" deve ser único - CHECK

    // "telefone" nao pode ser NULL
    public static Boolean telefoneIsNotNull(String telefone){
        if(telefone == null || telefone.isBlank()){
            throw new FieldCannotBeNullException("telefone não pode ser NULL");
        }
        return true;
    }

    // 'telefone' deve possuir 9 characters
    public static Boolean telefoneLengthIsCorrect(String telefone){
        if(telefone.length() != 9){
            throw new FieldLengthIsIncorrect("telefone' deve conter 9 charaters");
        }
        return true;
    }

    // "telefone" deve possuir apenas números
    public static Boolean telefoneHasOnlyNumbers(String telefone){
        telefone.chars().forEach((currentCharacter) -> {
            if(!(Character.isDigit(currentCharacter))){
                throw new FieldMustHaveOnlyNumbers("telefone deve possuir apenas números!");
            }
        });
        return true;
    }

    // "telefone" deve possuir o digito 9 na frente - CHECK
    public static Boolean firstDigitIs9(String telefone){
        Character primeiroDigito = telefone.charAt(0);
        if(!(primeiroDigito.toString().equals("9"))){
            throw new TelefonedoesNotContainNine("Está faltando o dígito 9 na frente");
        }
        return true;
    }

    // "telefone" deve ser único
    public static Boolean telefoneIsUnique(String telefone, ClienteRepository clienteRepository){
        clienteRepository.findByTelefone(telefone).ifPresent((cliente) -> {
            throw new FieldIsNotUniqueException("telefone deve ser único. Já existe um cliente com esse telefone.");
        });
        return true;
    }

    //Verificamos se o campo 'telefone' passou por todas as validacoes
    public static Boolean allValidationsAreCorrect(String telefone, ClienteRepository clienteRepository){
        telefoneIsNotNull(telefone);
        telefoneLengthIsCorrect(telefone);
        telefoneHasOnlyNumbers(telefone);
        firstDigitIs9(telefone);
        telefoneIsUnique(telefone, clienteRepository);
        return true;
    }

    public static Boolean allValidationsAreCorrect(String telefone, ClienteRepository clienteRepository, Boolean atualizarCliente){
        if(telefone != null){
            telefoneIsNotNull(telefone);
            telefoneLengthIsCorrect(telefone);
            telefoneHasOnlyNumbers(telefone);
            firstDigitIs9(telefone);
            telefoneIsUnique(telefone, clienteRepository);
        }
        return true;
    }
}