package br.com.alelo.consumer.consumerpat.recharge.adapter.in;

import br.com.alelo.consumer.consumerpat.recharge.application.port.in.NewRecharge;
import br.com.alelo.consumer.consumerpat.recharge.application.port.in.RechargeCommand;
import br.com.alelo.consumer.consumerpat.recharge.application.port.in.RechargeUseCase;
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

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RechargeController.class)
class RechargeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RechargeUseCase rechargeUseCase;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Nested
    @DisplayName("Recharge controller success cases tests")
    class SuccessCases {

        @Test
        void testRechargeCard() throws Exception {

            var newRechage = new NewRecharge("1111111111111111",
                    BigDecimal.valueOf(300),
                    LocalDate.now());

            var command = RechargeCommand.of(newRechage.getCardNumber(),
                    newRechage.getAmount(),
                    newRechage.getRechargedAt());

            mockMvc.perform(post("/recharges")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(newRechage)))
                    .andExpect(status().isNoContent());

            then(rechargeUseCase).should()
                    .recharge(command);
        }
    }

    @Nested
    @DisplayName("Recharge controller success cases tests")
    class ErrorCases {

        @Test
        void testRechargeCard() throws Exception {

            var newRechage = new NewRecharge("1111111111111111",
                    BigDecimal.ZERO,
                    LocalDate.now());

            var command = RechargeCommand.of(newRechage.getCardNumber(),
                    newRechage.getAmount(),
                    newRechage.getRechargedAt());

            mockMvc.perform(post("/recharges")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(newRechage)))
                    .andExpect(status().isBadRequest());

            then(rechargeUseCase).shouldHaveNoInteractions();
        }
    }
}