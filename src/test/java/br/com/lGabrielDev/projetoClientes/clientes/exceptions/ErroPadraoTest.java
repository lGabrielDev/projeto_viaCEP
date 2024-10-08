package br.com.lGabrielDev.projetoClientes.clientes.exceptions;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class ErroPadraoTest {
    
    //getters and setters
    @Test
    public void itShouldReturnATimestampAsString(){
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(LocalDateTime.of(1990,4,2,20,0,5));

        String timestampFormatado = erroPadrao.getTimestamp();

        Assertions.assertThat(timestampFormatado).isEqualTo("1990-04-02  08:00:05 PM");
    }
}