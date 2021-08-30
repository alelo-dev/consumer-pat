package br.com.alelo.consumer.consumerpat.json;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsumerJsonTests {


    @Test
    public void descerializa() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ConsumerDTO dto = mapper.reader().forType(ConsumerDTO.class).readValue(json);
        assertEquals("pvh",dto.getCity());
        assertEquals("andrew@andrew",dto.getEmail());
    }

    private String json = "{\n" +
            "  \"birthDate\": \"2021-08-30T21:16:43.368Z\",\n" +
            "  \"city\": \"pvh\",\n" +
            "  \"country\": \"cidade\",\n" +
            "  \"documentNumber\": 661,\n" +
            "  \"drugstoreNumber\": 6665,\n" +
            "  \"email\": \"andrew@andrew\",\n" +
            "  \"foodCardNumber\": 6665,\n" +
            "  \"fuelCardNumber\": 3331,\n" +
            "  \"id\": 0,\n" +
            "  \"mobilePhoneNumber\": 3331,\n" +
            "  \"name\": \"andrew\",\n" +
            "  \"number\": 111,\n" +
            "  \"phoneNumber\": 11150,\n" +
            "  \"portalCode\": 7770,\n" +
            "  \"residencePhoneNumber\": 555,\n" +
            "  \"street\": \"rua\"\n" +
            "}";
}
