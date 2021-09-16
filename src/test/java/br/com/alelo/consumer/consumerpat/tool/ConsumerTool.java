package br.com.alelo.consumer.consumerpat.tool;

import br.com.alelo.consumer.consumerpat.data.entity.Consumer;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConsumerTool {
    public static String CONSUMER_NAME = "John Wall";

    public static Consumer buildFakeConsumer() throws ParseException {
        return new Consumer()
                .setName(CONSUMER_NAME)
                .setDocumentNumber("36098712345")
                .setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("26/06/1988"))
                .setMobilePhoneNumber("+5511988776655")
                .setResidencePhoneNumber("+5512988776655")
                .setPhoneNumber("+5513988776655")
                .setEmail("alelo-challenge@hey.ho")
                .setStreet("Rua Frei Gaspar")
                .setNumber("22B")
                .setCity("Santos")
                .setCountry("Brasil")
                .setPostalCode("11340000");
    }
}
