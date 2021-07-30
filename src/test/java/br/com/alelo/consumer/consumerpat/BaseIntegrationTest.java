package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.dto.BaseHttpResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = ConsumerTestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@Transactional
public abstract class BaseIntegrationTest {

    @Autowired
    protected MockMvc mvc;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @BeforeAll
    public static void configureMapper() {
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @SneakyThrows
    protected String asJsonString(Object object) {
        return MAPPER.writeValueAsString(object);
    }

    @SneakyThrows
    protected <T> BaseHttpResponse<T> asBaseHttpResponse(String value, Class<T> resultClass) {
        final BaseHttpResponse<T> result = MAPPER.readValue(value, new ResponseTypeReference<>());
        result.setResult(MAPPER.convertValue(result.getResult(), resultClass));
        return result;
    }

    @SneakyThrows
    protected <T> BaseHttpResponse<List<T>> asBaseHttpResponseList(String value) {
        final BaseHttpResponse<List<T>> result = MAPPER.readValue(value, new ResponseListTypeReference<>());
        result.setResult(MAPPER.convertValue(result.getResult(), new ListTypeReference<>()));
        return result;
    }

    private static class ResponseTypeReference<T> extends TypeReference<BaseHttpResponse<T>> {
        private ResponseTypeReference() {
        }
    }

    private static class ResponseListTypeReference<T> extends TypeReference<BaseHttpResponse<List<T>>> {
        private ResponseListTypeReference() {
        }
    }

    private static class ListTypeReference<T> extends TypeReference<List<T>> {
        private ListTypeReference() {
        }
    }
}
