package br.com.alelo.consumer.consumerpat.json;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyDTOJsonTests {

    @Test
    public void descerializa() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BuyDTO compra = mapper.reader().forType(BuyDTO.class).readValue(json);
        assertEquals(1.99,compra.value.doubleValue());
        assertEquals("Padaria do windson",compra.establishmentName);
        assertEquals("Windson",compra.productDescription);
        assertEquals(1,compra.establishmentType);
    }


    private String json = "{\n" +
            "\"establishmentName\" : \"Padaria do windson\",\n" +
            "\"productDescription\" : \"Windson\",\n" +
            "\"value\" : 1.99,\n" +
            "\"establishmentType\": \"1\"\n" +
            "}";
}
