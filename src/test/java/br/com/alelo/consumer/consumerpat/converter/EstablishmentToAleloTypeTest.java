package br.com.alelo.consumer.consumerpat.converter;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.ExtractCreationException;
import br.com.alelo.consumer.consumerpat.exception.IllegalEstablishmentTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 21:13
 */

class EstablishmentToAleloTypeTest {

    @Test
    void convertSuccess() {
        for(int x = 1; x <=3; x++){
            Assertions.assertNotNull(EstablishmentToAleloType.convert(x));
        }
    }

    @Test
    void convertError() {
        Assertions.assertThrows(IllegalEstablishmentTypeException.class, () -> {
            EstablishmentToAleloType.convert(99);
        });
    }
}