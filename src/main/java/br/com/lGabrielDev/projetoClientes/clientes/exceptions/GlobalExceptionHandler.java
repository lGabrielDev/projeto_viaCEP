package br.com.lGabrielDev.projetoClientes.clientes.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.dataDeNascimento.ClienteEhMenorDeIdadeException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.email.EmailIsMissingException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldIsNotUniqueException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldLengthIsIncorrect;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldMustHaveOnlyNumbers;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.id.ClienteNotFoundException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.telefone.TelefonedoesNotContainNine;
import br.com.lGabrielDev.projetoClientes.endereco.exceptions.InvalidCepException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // ================== validacoes do CEP ==================
    @ExceptionHandler(FieldCannotBeNullException.class)
    public ResponseEntity<ErroPadrao> fieldCannotBeNullExceptionHandler(FieldCannotBeNullException e){
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(LocalDateTime.now());
        erroPadrao.setStatus(HttpStatus.BAD_REQUEST.value());
        erroPadrao.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

    @ExceptionHandler(FieldLengthIsIncorrect.class)
    public ResponseEntity<ErroPadrao> fieldLengthIsIncorrectHandler(FieldLengthIsIncorrect e){
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(LocalDateTime.now());
        erroPadrao.setStatus(HttpStatus.BAD_REQUEST.value());
        erroPadrao.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

    @ExceptionHandler(FieldMustHaveOnlyNumbers.class)
    public ResponseEntity<ErroPadrao> fieldMustHaveOnlyNumbersHandler(FieldMustHaveOnlyNumbers e){
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(LocalDateTime.now());
        erroPadrao.setStatus(HttpStatus.BAD_REQUEST.value());
        erroPadrao.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

    @ExceptionHandler(InvalidCepException.class)
    public ResponseEntity<ErroPadrao> invalidCepExceptionHandler(InvalidCepException e){
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(LocalDateTime.now());
        erroPadrao.setStatus(HttpStatus.BAD_REQUEST.value());
        erroPadrao.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

    // ================== validacoes do Cliente ==================
    //data de nascimento
    @ExceptionHandler(ClienteEhMenorDeIdadeException.class)
    public ResponseEntity<ErroPadrao> clienteEhMenorDeIdadeExceptionHandler(ClienteEhMenorDeIdadeException e){
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(LocalDateTime.now());
        erroPadrao.setStatus(HttpStatus.BAD_REQUEST.value());
        erroPadrao.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

    //cpf
    @ExceptionHandler(FieldIsNotUniqueException.class)
    public ResponseEntity<ErroPadrao> fieldIsNotUniqueExceptionHandler(FieldIsNotUniqueException e){
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(LocalDateTime.now());
        erroPadrao.setStatus(HttpStatus.BAD_REQUEST.value());
        erroPadrao.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

    //email
    @ExceptionHandler(EmailIsMissingException.class)
    public ResponseEntity<ErroPadrao> emailIsMissingExceptionHandler(EmailIsMissingException e){
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(LocalDateTime.now());
        erroPadrao.setStatus(HttpStatus.BAD_REQUEST.value());
        erroPadrao.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

    //telefone
    @ExceptionHandler(TelefonedoesNotContainNine.class)
    public ResponseEntity<ErroPadrao> telefonedoesNotContainNineHandler(TelefonedoesNotContainNine e){
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(LocalDateTime.now());
        erroPadrao.setStatus(HttpStatus.BAD_REQUEST.value());
        erroPadrao.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

    //Datatype diferente
    //No lugar de Integer, usuario inputou uma String
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroPadrao> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e){
    
        String errorMessage = "";
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(LocalDateTime.now());
        erroPadrao.setStatus(HttpStatus.BAD_REQUEST.value());
        erroPadrao.setErrorMessage(e.getCause().getMessage());

        if(e.getCause().getMessage().contains("dataDeNascimento")){
            errorMessage = String.format("dataDeNascimento deve serguir esse formato --> '1990-04-05' ");
            erroPadrao.setErrorMessage(errorMessage);
        }
        if(e.getCause().getMessage().contains("numeroCasa")){
            errorMessage = String.format("numeroCasa deve ser um Integer");
            erroPadrao.setErrorMessage(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

    //id - Cliente nao encontrado
    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErroPadrao> clienteNotFoundExceptionHandler(ClienteNotFoundException e){
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(LocalDateTime.now());
        erroPadrao.setStatus(HttpStatus.NOT_FOUND.value());
        erroPadrao.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroPadrao);
    }

    // ================== @Pathvariable ==================
    // Ao tentar atualizar ou deletar um cliente pelo #ID, informou uma String e nao um Long
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroPadrao> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e){
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(LocalDateTime.now());
        erroPadrao.setStatus(HttpStatus.BAD_REQUEST.value());
        erroPadrao.setErrorMessage(e.getMessage());

        if(e.getName().equals("id")){
            erroPadrao.setErrorMessage("O parametro {id} na url precisa ser um Long e não uma String. Se você quiser atualizar ou deletar um cliente pelo {id}, o padrao de url seria assim: 'http://localhost:8080/api/clientes/5'");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }
}