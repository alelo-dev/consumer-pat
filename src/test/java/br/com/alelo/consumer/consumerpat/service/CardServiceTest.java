package br.com.alelo.consumer.consumerpat.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alelo.consumer.consumerpat.constants.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.constants.EstablishmentType;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;

@ExtendWith(SpringExtension.class)
public class CardServiceTest {

  @InjectMocks
  private CardService service;

  @Mock
  private CardRepository repositoryMock;
  @Mock
  private ExtractService extractServiceMock;

  private Card cardFuelSaved;
  private Card cardFoodSaved;
  private Card cardSaved;
  private Card cardDrugstoreSaved;
  private Consumer consumerSaved;
  private Card cardToBeSave;

  private Extract extractSaved;

  @BeforeEach
  private void setUp() throws ParseException {
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

    cardFuelSaved = Card.builder()
        .cardNumber("123456789")
        .cardBalance(600.0)
        .cardType(CardTypeEnum.FUEL)
        .consumer(consumerSaved)
        .active(true)
        .id(1)
        .build();

    cardFoodSaved = Card.builder()
        .cardNumber("123456789")
        .cardBalance(300.0)
        .cardType(CardTypeEnum.FOOD)
        .consumer(consumerSaved)
        .active(true)
        .id(2)
        .build();

    cardDrugstoreSaved = Card.builder()
        .cardNumber("123456789")
        .cardBalance(00.0)
        .cardType(CardTypeEnum.DRUGSTORE)
        .consumer(consumerSaved)
        .active(true)
        .id(3)
        .build();

    cardToBeSave = Card.builder()
        .cardNumber("88888")
        .cardBalance(0.0)
        .cardType(CardTypeEnum.FOOD)
        .consumer(consumerSaved)
        .active(true)

        .build();

    cardSaved = Card.builder()
        .cardNumber("88888")
        .cardBalance(0.0)
        .cardType(CardTypeEnum.FOOD)
        .consumer(consumerSaved)
        .active(true)
        .id(564)
        .build();

    extractSaved = Extract.builder()
        .establishmentName("Estabelecimento")
        .productDescription("Produto")
        .dateBuy(new SimpleDateFormat("dd/MM/yyyy").parse("1/05/2022"))
        .cardNumber("88888")
        .value(5.80)
        .id(1)
        .build();
  }

  @Test
  void testAddConsumerCard() {

    BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Card.class))).thenReturn(cardSaved);
    final Card card = service.addConsumerCard(cardToBeSave);
    Assertions.assertThat(card).isNotNull();
    Assertions.assertThat(card.getId()).isEqualTo(cardSaved.getId());

  }

  @Test
  void testFindByCardNumber() {
    BDDMockito.when(repositoryMock.findByCardNumberAndActiveTrue(ArgumentMatchers.any(String.class)))
        .thenReturn(Optional.of(cardSaved));
    final Card card = service.findByCardNumber("88888");
    Assertions.assertThat(card).isNotNull();
    Assertions.assertThat(card.getId()).isEqualTo(cardSaved.getId());
  }

  @Test
  void testPayment() {
    BDDMockito.when(repositoryMock.findByCardNumberAndActiveTrue(ArgumentMatchers.any(String.class)))
        .thenReturn(Optional.of(cardSaved));
    BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Card.class))).thenReturn(cardSaved);
    BDDMockito.when(extractServiceMock.saveExtract(ArgumentMatchers.any(Extract.class))).thenReturn(extractSaved);
    service.setCardbalance(cardSaved.getCardNumber(), 500.00);
    double oldCardBalance = cardSaved.getCardBalance();
    double value = 5.80;

    service.payment(EstablishmentType.FOOD_CARD, "Restaurante", cardSaved.getCardNumber(), "Coxinha", value);

    Assertions.assertThat(cardSaved.getCardBalance()).isEqualTo(oldCardBalance - value);
  }

  @Test
  void testRemoveConsumerCard() {
    BDDMockito.when(repositoryMock.findByIdAndActiveTrue(ArgumentMatchers.any(Integer.class)))
        .thenReturn(Optional.of(cardSaved));
    BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Card.class))).thenReturn(cardSaved);

    service.removeConsumerCard(cardSaved.getId());

    Assertions.assertThatExceptionOfType(RuntimeException.class)
        .isThrownBy(() -> service.findByCardNumber(cardSaved.getCardNumber()))
        .withMessage("Card not found");

  }

  @Test
  void testSetFoodCardbalance100() {
    //
    BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Card.class))).thenReturn(cardFoodSaved);
    BDDMockito.when(repositoryMock.findByCardNumberAndActiveTrue(ArgumentMatchers.any(String.class)))
        .thenReturn(Optional.of(cardFoodSaved));

    double oldCardBalance = cardFoodSaved.getCardBalance();
    service.setCardbalance(cardFoodSaved.getCardNumber(), 100.0);
    Assertions.assertThat(cardFoodSaved).isNotNull();
    Assertions.assertThat(cardFoodSaved.getCardBalance()).isEqualTo(oldCardBalance + 100);
  }

  @Test
  void testSetFuelCardbalance500() {
    BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Card.class))).thenReturn(cardFuelSaved);
    BDDMockito.when(repositoryMock.findByCardNumberAndActiveTrue(ArgumentMatchers.any(String.class)))
        .thenReturn(Optional.of(cardFuelSaved));

    double oldCardBalance = cardFuelSaved.getCardBalance();
    service.setCardbalance(cardFuelSaved.getCardNumber(), 500.0);
    Assertions.assertThat(cardFuelSaved).isNotNull();
    Assertions.assertThat(cardFuelSaved.getCardBalance()).isEqualTo(oldCardBalance + 500);
  }

  @Test
  void testSetDugsStoreCardbalance200() {
    BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Card.class))).thenReturn(cardDrugstoreSaved);
    BDDMockito.when(repositoryMock.findByCardNumberAndActiveTrue(ArgumentMatchers.any(String.class)))
        .thenReturn(Optional.of(cardDrugstoreSaved));

    double oldCardBalance = cardDrugstoreSaved.getCardBalance();
    service.setCardbalance(cardDrugstoreSaved.getCardNumber(), 200.0);
    Assertions.assertThat(cardDrugstoreSaved).isNotNull();
    Assertions.assertThat(cardDrugstoreSaved.getCardBalance()).isEqualTo(oldCardBalance + 200);
  }
}
