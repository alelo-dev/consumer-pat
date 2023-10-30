package br.com.alelo.consumer.consumerpat.adapters.in.controller.payments;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.request.EstablishmentRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.request.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.request.PaymentRequest;
import br.com.alelo.consumer.consumerpat.application.ports.in.payments.PaymentsInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentscontrollerTest {
    @InjectMocks
    private PaymentsController paymentsController;

    @MockBean
    private PaymentsInputPort paymentsInputPort;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

    }


    @Test
    void testRegisterPaymentWithSuccess() throws Exception {
        var establishment = new EstablishmentRequest("Sample Restaurant", EstablishmentTypeEnum.FOOD);
        String productDescription = "Sample Product";
        var buyDate = LocalDate.now();
        BigDecimal amount = BigDecimal.valueOf(100);
        PaymentRequest paymentRequest = new PaymentRequest(establishment, productDescription, buyDate, "1234567890123456", amount);

        mockMvc.perform(post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(paymentRequest)))
                .andExpect(status().isNoContent());
    }
}
