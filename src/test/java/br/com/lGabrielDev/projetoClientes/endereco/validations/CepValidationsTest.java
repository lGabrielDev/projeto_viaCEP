package br.com.lGabrielDev.projetoClientes.endereco.validations;

import static org.mockito.ArgumentMatchers.anyString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldCannotBeNullException;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldLengthIsIncorrect;
import br.com.lGabrielDev.projetoClientes.clientes.exceptions.genericos.FieldMustHaveOnlyNumbers;
import br.com.lGabrielDev.projetoClientes.endereco.Endereco;
import br.com.lGabrielDev.projetoClientes.endereco.exceptions.InvalidCepException;
import br.com.lGabrielDev.projetoClientes.viaCep.ViaCepController;

@ExtendWith(MockitoExtension.class)
public class CepValidationsTest {

    @Mock
    private ViaCepController viacep;

    @InjectMocks
    private CepValidations cpfValidations = Mockito.mock(CepValidations.class);

 
    // 'CEP' nao pode ser NULL
    @Test
    public void itShouldReturnTrueBecauseCepIsNotNull(){
        //arrange
        String cep = "123";
        //act
        Boolean cepIsNotNull = CepValidations.cepIsNotNull(cep);
        //assert
        Assertions.assertThat(cepIsNotNull).isTrue();
    }

    @Test
    public void itShouldGetAnExceptionBecauseCepIsNull(){
        //arrange
        String cep = null;
        //act
        Assertions.assertThatThrownBy(() -> CepValidations.cepIsNotNull(cep))
        //assert
        .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    @Test
    public void itShouldGetAnExceptionBecauseCepIsBlank(){
        //arrange
        String cep = "  ";
        //act
        Assertions.assertThatThrownBy(() -> CepValidations.cepIsNotNull(cep))
        //assert
        .isExactlyInstanceOf(FieldCannotBeNullException.class);
    }

    // 'CEP' deve ter exatamente 8 characters
    @Test
    public void itShouldReturnTrueBecauseCepHasThePerfectLength(){
        //arrange
        String cep = "12345678";
        //act
        Boolean cepLengthIsEqual8 = CepValidations.cepLengthIsEqual8(cep);
        //assert
        Assertions.assertThat(cepLengthIsEqual8).isTrue();
    }

    @Test
    public void itShouldGetAnExceptionBecauseCepLengthIsToLong(){
        //arrange
        String cep = "123456789";
        //act
        Assertions.assertThatThrownBy(() -> CepValidations.cepLengthIsEqual8(cep))
        //assert
        .isExactlyInstanceOf(FieldLengthIsIncorrect.class);
    }

    // 'CEP' deve conter apenas numeros
    @Test
    public void itShouldReturnTrueBecauseCepHasOnlyNumbers(){
        //arrange
        String cep = "123";
        //act
        Boolean cepHasOnlyNumbers = CepValidations.cepHasOnlyNumbers(cep);
        //assert
        Assertions.assertThat(cepHasOnlyNumbers).isTrue();
    }

    @Test
    public void itShouldGetAnExceptionBecauseCepHasSymbols(){
        //arrange
        String cep = "123abc";
        //act
        Assertions.assertThatThrownBy(() -> CepValidations.cepHasOnlyNumbers(cep))
        //assert
        .isExactlyInstanceOf(FieldMustHaveOnlyNumbers.class);
    }

    // 'CEP' deve existir
    @Test
    public void itShouldReturnTrueBecauseCepExists(){
        //arrange
        Endereco endereco = new Endereco();
        endereco.setBairro("bairro aleatório");

        Mockito.when(this.viacep.getEnderecoByCep(anyString())).thenReturn(endereco);
        //act
        Boolean cepExists = CepValidations.cepExists(anyString(), this.viacep);
        //assert
        Assertions.assertThat(cepExists).isTrue();
    }

    @Test
    public void itShouldGetAnExceptionBecauseCepNotFound(){
        //arrange
        Endereco endereco = new Endereco();
        endereco.setBairro(null);

        Mockito.when(this.viacep.getEnderecoByCep(anyString())).thenReturn(endereco);
        //act
        Assertions.assertThatThrownBy(() -> CepValidations.cepExists(anyString(), this.viacep))
        //assert
            .isExactlyInstanceOf(InvalidCepException.class);
    }

    //Verificamos se o CEP passou por todas as validacoes, no caso de CADASTRO
    @Test
    public void itShouldReturnTrueBecauseCepPassThroughAllValidationsWhenCreate(){
        //arrange
        String cep = "12345678";
        Endereco endereco = new Endereco();
        endereco.setBairro("bairro aleatorio");

        Mockito.when(this.viacep.getEnderecoByCep(anyString())).thenReturn(endereco);
        //act
        Boolean allValidationsAreCorrect = CepValidations.allValidationsAreCorrect(cep, this.viacep);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    // ===== Verificamos se o CEP passou por todas as validacoes, no caso de UPDATE =====
    @Test
    public void itShouldReturnTrueBecauseCepPassThroughAllValidationsWhenUpdate(){
        //arrange
        String cep = "12345678";
        Endereco endereco = new Endereco();
        endereco.setBairro("bairro aleatorio");

        Mockito.when(this.viacep.getEnderecoByCep(anyString())).thenReturn(endereco);
        //act
        Boolean allValidationsAreCorrect = CepValidations.allValidationsAreCorrect(cep, this.viacep, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }

    //testamos uma atualizacao de cep, onde esse cep é NULL
    //Quando nao informado cep, o endereco permanece com os valores antigos e nao sofre nenhuma alteracao
    @Test
    public void whenUpdateItShouldReturnTrueBecauseCepIsNull(){
        //arrange
        String cep = null;
        //act
        Boolean allValidationsAreCorrect = CepValidations.allValidationsAreCorrect(cep, this.viacep, true);
        //assert
        Assertions.assertThat(allValidationsAreCorrect).isTrue();
    }
}