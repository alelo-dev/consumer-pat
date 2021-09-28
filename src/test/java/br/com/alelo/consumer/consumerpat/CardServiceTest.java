package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.BuyDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.CardEntity;
import br.com.alelo.consumer.consumerpat.domain.entity.ExtractEntity;
import br.com.alelo.consumer.consumerpat.domain.entity.enums.CardType;
import br.com.alelo.consumer.consumerpat.domain.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exception.EstablishmentInvalidException;
import br.com.alelo.consumer.consumerpat.domain.exception.InsuficientBalanceException;
import br.com.alelo.consumer.consumerpat.domain.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("[Card service - Sequencia de testes para os métodos da camada Service referentens a Cartao]")
public class CardServiceTest {

    public static final int FOOD_CARD_NUMBER = 598;
    public static final int DRUGSTORE_CARD_NUMBER = 599;
    public static final int FUEL_CARD_NUMBER = 600;
    private CardEntity foodCard;
    private CardEntity fuelCard;
    private CardEntity drugstoreCard;

    @Autowired
    private CardService cardService;

    @MockBean
    private CardRepository cardRepository;

    @BeforeEach
    public void setUpMocks() {
        MockitoAnnotations.openMocks(this);
        foodCard = new CardEntity();
        foodCard.setId(FOOD_CARD_NUMBER);
        foodCard.setCardBalance(BigDecimal.valueOf(1000));
        foodCard.setType(CardType.FOOD);

        fuelCard = new CardEntity();
        fuelCard.setId(FUEL_CARD_NUMBER);
        fuelCard.setType(CardType.FUEL);
        fuelCard.setCardBalance(BigDecimal.valueOf(1000));

        drugstoreCard = new CardEntity();
        drugstoreCard.setId(FUEL_CARD_NUMBER);
        drugstoreCard.setType(CardType.DRUGSTORE);
        drugstoreCard.setCardBalance(BigDecimal.valueOf(1000));
    }

    @Test
    @DisplayName("[Card Service] - Adicionar saldo ao cartão")
    void adicionarSaldoAoCartao() throws InsuficientBalanceException, CardNotFoundException {
        when(cardRepository.findByNumber(FOOD_CARD_NUMBER)).thenReturn(Optional.of(foodCard));
        cardService.setBalance(FOOD_CARD_NUMBER, 29.90);
    }

    @Test
    @DisplayName("[Card Service] - Alteração de saldo em um cartão inválido")
    @ExceptionHandler(CardNotFoundException.class)
    void alterarSaldoCartaoInvalido() {
        Assertions.assertThrows(CardNotFoundException.class, () -> cardService.setBalance(999, 29.90));
    }

    @Test
    @DisplayName("[Card Service] - Realiza uma compra com sucesso no cartao FOOD")
    void realizaUmaCompraComCartaoFoodSucesso() throws InsuficientBalanceException, CardNotFoundException, EstablishmentInvalidException {

        BuyDTO buyDTO = new BuyDTO(
                598,
                "Bar do seu zé",
                1,
                "Bastante cerveja e salgado",
                350.49);

        when(cardRepository.findByNumber(FOOD_CARD_NUMBER)).thenReturn(Optional.of(foodCard));
        ExtractEntity extrato = cardService.buy(buyDTO);


        //O esperado eh menor devido ao cashback
        assertEquals(315.44100000000003, extrato.getValue());
    }

    @Test
    @DisplayName("[Card Service] - Realiza uma compra com sucesso no cartao DRUGSTORE ")
    void realizaUmaCompraComCartaoDrugstoreSucesso() throws InsuficientBalanceException, CardNotFoundException, EstablishmentInvalidException {

        BuyDTO buyDTO = new BuyDTO(
                599,
                "Farmacia do pos bar",
                2,
                "Engov, ENO e analgesico",
                100.54);

        when(cardRepository.findByNumber(DRUGSTORE_CARD_NUMBER)).thenReturn(Optional.of(drugstoreCard));
        ExtractEntity extrato = cardService.buy(buyDTO);

        assertEquals(100.54, extrato.getValue());
    }

    @Test
    @DisplayName("[Card Service] - Realiza uma compra com sucesso no cartao FUEL")
    void realizaUmaCompraComCartaoFuelSucesso() throws InsuficientBalanceException, CardNotFoundException, EstablishmentInvalidException {
        BuyDTO buyDTO = new BuyDTO(
                600,
                "Posto de Gasolina",
                3,
                "Gasolina",
                215.87);

        when(cardRepository.findByNumber(FUEL_CARD_NUMBER)).thenReturn(Optional.of(fuelCard));
        ExtractEntity extrato = cardService.buy(buyDTO);

        //esperado eh a mais por conta da taxa sobre o FUEL
        assertEquals(291.4245, extrato.getValue());
    }

    @Test
    @DisplayName("[Card Service] - Tentativa de uso do cartão com saldo insuficiente")
    @ExceptionHandler(InsuficientBalanceException.class)
    void realizaUmaCompraComSaldoInsuficienteLancandoException() {

        BuyDTO buyDTO = new BuyDTO(
                598,
                "Farmacia do pos bar",
                1,
                "Engov, ENO e analgesico",
                5000);

        when(cardRepository.findByNumber(FOOD_CARD_NUMBER)).thenReturn(Optional.of(foodCard));
        Assertions.assertThrows(InsuficientBalanceException.class, () ->
                cardService.buy(buyDTO)
        );
    }

    @Test
    @DisplayName("[Card Service] - Tentativa de uso de cartao de tipo diferente do permitido para o estabelecimento")
    void realizaCompraComCartaoFoodEmDrugstore() throws EstablishmentInvalidException {

        BuyDTO buyDTO = new BuyDTO(
                598,
                "Farmacia do pos bar",
                2,
                "Engov, ENO e analgesico",
                100000.54);

        when(cardRepository.findByNumber(FOOD_CARD_NUMBER)).thenReturn(Optional.of(foodCard));
        Assertions.assertThrows(EstablishmentInvalidException.class, () ->
                cardService.buy(buyDTO)
        );
    }

}
