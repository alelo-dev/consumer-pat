package br.com.alelo.consumer.consumerpat.application.controller.payment;

import br.com.alelo.consumer.consumerpat.application.controller.payment.request.PaymentRequest;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Establishment;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.EstablishmentType;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import br.com.alelo.consumer.consumerpat.domain.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @Test
    public void testRegisterPayment_Success() throws Exception {
        UUID paymentId = UUID.randomUUID();
        Establishment establishment = new Establishment("Sample Restaurant", EstablishmentType.FOOD);
        String productDescription = "Sample Product";
        LocalDate buyDate = LocalDate.now();
        CardNumber cardNumber = new CardNumber("1234567890123456");
        BigDecimal amount = BigDecimal.valueOf(100);
        Payment payment = new Payment(establishment, productDescription, buyDate, cardNumber, amount);
        payment.addId(paymentId);
        PaymentRequest paymentRequest = new PaymentRequest(payment);

        mockMvc.perform(post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(paymentRequest)))
                .andExpect(status().isNoContent());

        verify(paymentService).registerPayment(payment);
    }

    @Test
    public void testRegisterPayment_InvalidPayment() throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest(null);

        mockMvc.perform(post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(paymentRequest)))
                .andExpect(status().isBadRequest());
    }

    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(obj);
    }
}
