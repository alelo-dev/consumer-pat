package br.com.alelo.consumer.consumerpat.service;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alelo.consumer.consumerpat.constants.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.constants.EstablishmentType;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@DisplayName("Test for ConsumerService")
@ExtendWith(SpringExtension.class)
public class ConsumerServiceTest {

        @InjectMocks
        private ConsumerService service;

        @Mock
        private ConsumerRepository repositoryMock;

        @Mock
        private ExtractService extractService;

        @Mock
        CardService cardService;

        @Mock
        CardRepository cardRepository;

        @Mock
        private ExtractRepository extractRepositoryMock;

        private Consumer consumerSaved;
        private Consumer consumerToUpdate;
        private Consumer consumerToBeSaved;
        private Consumer consumer2;
        private Card consumerCardFoodSaved;
        private Card consumerCardFuelSaved;
        private Card consumerCardDrugstoreSaved;
        private Card consumerCardSaved;
        // private Extract extractToBeSaved;
        private Extract extractSaved;

        @BeforeEach
        private void setUp() throws ParseException {
                consumer2 = Consumer.builder()
                                .name("João")
                                .documentNumber("12345678901")
                                .birthDate(new Date())
                                .build();

                consumerToUpdate = Consumer.builder()
                                .birthDate(new SimpleDateFormat("dd/MM/yyyy").parse("17/10/1985"))
                                .city("Pinhais")
                                .country("Brasil")
                                .documentNumber("12345678910")
                                .email("leandro.rauseo@email.com")
                                .mobilePhoneNumber("41 9912345678")
                                .name("Leandro Rauseo")
                                .number(642)
                                .portalCode(8300100)
                                .residencePhoneNumber("41 9912345678")
                                .street("Rua Rio Paraná")
                                .id(369)
                                .build();

                consumerSaved = Consumer.builder()
                                .birthDate(new SimpleDateFormat("dd/MM/yyyy").parse("17/10/1985"))
                                .city("Pinhais")
                                .country("Brasil")
                                .documentNumber("12345678910")
                                .email("leandro.rauseo@email.com")
                                .mobilePhoneNumber("41 9912345678")
                                .name("Leandro Rauseo")
                                .number(642)
                                .portalCode(8300100)
                                .residencePhoneNumber("41 9912345678")
                                .street("Rua Rio Paraná")
                                .id(369)
                                .build();

                consumerCardFoodSaved = Card.builder()
                                .cardNumber("123456789")
                                .cardBalance(100.0)
                                .cardType(CardTypeEnum.FOOD)
                                .consumer(consumerSaved)
                                .active(true)
                                .build();

                consumerCardFuelSaved = Card.builder()
                                .cardNumber("123456789")
                                .cardBalance(600.0)
                                .cardType(CardTypeEnum.FUEL)
                                .consumer(consumerSaved)
                                .active(true)
                                .build();

                consumerCardDrugstoreSaved = Card.builder()
                                .cardNumber("123456789")
                                .cardBalance(50.0)
                                .cardType(CardTypeEnum.DRUGSTORE)
                                .consumer(consumerSaved)
                                .active(true)
                                .build();

                consumerToBeSaved = Consumer.builder()
                                .birthDate(new SimpleDateFormat("dd/MM/yyyy").parse("17/10/1985"))
                                .city("Pinhais")
                                .country("Brasil")
                                .documentNumber("12345678910")
                                .email("leandro.rauseo@email.com")
                                .mobilePhoneNumber("41 9912345678")
                                .name("Leandro Rauseo")
                                .number(642)
                                .portalCode(8300100)
                                .residencePhoneNumber("41 9912345678")
                                .street("Rua Rio Paraná")
                                .build();
                consumerCardSaved = Card.builder()
                                .cardNumber("123456789")
                                .cardBalance(100.0)
                                .cardType(CardTypeEnum.FOOD)
                                .consumer(consumerSaved)
                                .active(true)
                                .id(8)
                                .build();
                PageImpl<Consumer> consumerPage = new PageImpl<>(List.of(consumerSaved));
                consumerPage.and(consumer2);
                consumerPage.and(consumerToBeSaved);

                BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Consumer.class))).thenReturn(consumerSaved);
                BDDMockito.when(repositoryMock.findAllConsumers(ArgumentMatchers.any(PageRequest.class)))
                                .thenReturn(consumerPage);
                BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Consumer.class))).thenReturn(consumerSaved);
                BDDMockito.when(repositoryMock.findById(ArgumentMatchers.any(Integer.class)))
                                .thenReturn(Optional.of(consumerSaved));

                BDDMockito.when(extractRepositoryMock.save(ArgumentMatchers.any(Extract.class)))
                                .thenReturn(extractSaved);
        }

        @Test
        void testGetConsumerListGreaterThanZero() {
                Page<Consumer> consumerList = service.consumerList(PageRequest.of(0, 10));
                Assertions.assertThat(consumerList).isNotNull();
                Assertions.assertThat(consumerList.getSize()).isGreaterThan(0);
        }

        @Test
        void testGetConsumerListEqualsZero() {
                BDDMockito.when(repositoryMock.findAllConsumers(ArgumentMatchers.any(PageRequest.class)))
                                .thenReturn(new PageImpl<>(List.of()));
                Page<Consumer> consumerList = service.consumerList(PageRequest.of(0, 10));
                Assertions.assertThat(consumerList).isNotNull();
                Assertions.assertThat(consumerList.getSize()).isEqualTo(0);
        }

        @Test
        void testCreateConsumer() {
                Consumer consumerSaved = service.createConsumer(this.consumerToBeSaved);
                Assertions.assertThat(consumerSaved).isNotNull();
                Assertions.assertThat(consumerSaved.getId()).isEqualTo(369);
        }

        @Test
        void testUpdateConsumer() {
                Consumer consumerSaved = service.createConsumer(this.consumerToBeSaved);
                Assertions.assertThat(consumerSaved).isNotNull();

                Consumer consumerUpdated = service.updateConsumer(consumerToUpdate);
                Assertions.assertThat(consumerUpdated).isNotNull();
                Assertions.assertThat(consumerUpdated).isEqualTo(consumerToUpdate);
        }

        @Test
        void testBuyFood() throws Exception {

                BDDMockito.when(cardService.findByCardNumber(ArgumentMatchers.any(String.class)))
                                .thenReturn(consumerCardSaved);

                BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Consumer.class))).thenReturn(consumerSaved);

                BDDMockito.when(cardRepository.save(ArgumentMatchers.any(Card.class)))
                                .thenReturn(consumerCardSaved);

                double foodValue = 32.50;
                double foodBalance = consumerCardSaved.getCardBalance();
                Double cashback = (foodValue / 100) * 10;

                consumerCardSaved.setCardBalance((foodBalance + cashback) - foodValue);
                BDDMockito.when(cardService.payment(anyInt(), anyString(), anyString(), anyString(), anyDouble()))
                                .thenReturn(consumerCardSaved);

                service.buy(EstablishmentType.FOOD_CARD, "Estabelecimento",
                                consumerCardFoodSaved.getCardNumber(), "Marmita",
                                foodValue);
                final var card = cardService.findByCardNumber(consumerCardSaved.getCardNumber());
                Assertions.assertThat(card.getCardBalance())
                                .isEqualTo((foodBalance - foodValue) + cashback);

        }

        @Test
        void testBuyFuel() throws Exception {

                BDDMockito.when(cardService.findByCardNumber(ArgumentMatchers.any(String.class)))
                                .thenReturn(consumerCardSaved);

                BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Consumer.class))).thenReturn(consumerSaved);

                BDDMockito.when(cardRepository.save(ArgumentMatchers.any(Card.class)))
                                .thenReturn(consumerCardSaved);

                double fuelValue = 350.50;
                double fuelBalance = consumerCardFuelSaved.getCardBalance();

                Double tax = (fuelValue / 100) * 35;
                consumerCardSaved.setCardBalance(fuelBalance - (fuelValue + tax));
                BDDMockito.when(cardService.payment(anyInt(), anyString(), anyString(), anyString(), anyDouble()))
                                .thenReturn(consumerCardSaved);

                service.buy(EstablishmentType.FUEL_CARD, "Posto Shell",
                                consumerCardSaved.getCardNumber(), "Gasolina V-Power",
                                fuelValue);

                Assertions.assertThat(consumerCardSaved.getCardBalance()).isEqualTo(fuelBalance
                                - (fuelValue + tax));

        }

        @Test
        void testBuyDrugstore() throws Exception {

                BDDMockito.when(cardService.findByCardNumber(ArgumentMatchers.any(String.class)))
                                .thenReturn(consumerCardSaved);

                BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Consumer.class))).thenReturn(consumerSaved);

                BDDMockito.when(cardRepository.save(ArgumentMatchers.any(Card.class)))
                                .thenReturn(consumerCardSaved);

                double drugstoreValue = 19.50;
                double drugstoreBalance = consumerCardSaved.getCardBalance();
                consumerCardSaved.setCardBalance(drugstoreBalance - drugstoreValue);
                BDDMockito.when(cardService.payment(anyInt(), anyString(), anyString(), anyString(), anyDouble()))
                                .thenReturn(consumerCardSaved);
                service.buy(EstablishmentType.DRUGSTORE_CARD, "Farmacia Nissei",
                                consumerCardSaved.getCardNumber(), "Nimesulida",
                                drugstoreValue);

                Assertions.assertThat(consumerCardSaved.getCardBalance()).isEqualTo(drugstoreBalance
                                - drugstoreValue);
        }

}
