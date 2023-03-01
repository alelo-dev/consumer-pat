package br.com.alelo.consumer.consumerpat.payment.adapter.in;

import br.com.alelo.consumer.consumerpat.payment.application.port.in.NewPayment;
import br.com.alelo.consumer.consumerpat.payment.application.port.in.RegisterPaymentCommand;
import br.com.alelo.consumer.consumerpat.payment.application.port.in.RegisterPaymentUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterPaymentUseCase registerPaymentUseCase;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Nested
    @DisplayName("Payment controller success cases tests")
    class SuccessCases {

        @Test
        void testRegisterPayment() throws Exception {

            var newPayment = NewPayment.of(1,
                    "Ifood",
                    "1111111111111111",
                    "Hamburger",
                    BigDecimal.valueOf(15.99),
                    LocalDate.now());

            var command = RegisterPaymentCommand.of(newPayment);

            mockMvc.perform(post("/payments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(newPayment)))
                    .andExpect(status().isNoContent());

            then(registerPaymentUseCase).should()
                    .registerPayment(command);
        }
    }

    @Nested
    @DisplayName("Payment controller error cases tests")
    class ErrorCases {

        @Test
        void testRegisterPayment() throws Exception {

            var newPayment = NewPayment.of(1,
                    "Ifood",
                    "1111111111111111",
                    "Hamburger",
                    BigDecimal.valueOf(15.99),
                    LocalDate.of(1969, Month.DECEMBER, 31));

            var command = RegisterPaymentCommand.of(newPayment);

            mockMvc.perform(post("/payments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(newPayment)))
                    .andExpect(status().isBadRequest());

            then(registerPaymentUseCase).shouldHaveNoInteractions();
        }
    }
}