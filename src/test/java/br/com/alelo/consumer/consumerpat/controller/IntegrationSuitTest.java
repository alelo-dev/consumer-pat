package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestOperations;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public abstract class IntegrationSuitTest {

    @Autowired
    RestOperations http;

    public static <T> String extractIdFromLocationHeader(ResponseEntity<T> response) {
        var headers = response.getHeaders();
        var locationHeader = headers.get("location");
        if (locationHeader == null ||locationHeader.isEmpty()) {
            return null;
        }
        var locationHeaderValue = locationHeader.get(0);
        var value = locationHeaderValue.split("/");
        return value.length == 3 ? value[2] : value[1];
    }

}
