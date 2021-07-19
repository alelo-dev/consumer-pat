package br.com.alelo.consumer.consumerpat.service.testutils;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contacts;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerCreateDto;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerUpdateDto;
import br.com.alelo.consumer.consumerpat.entity.enums.CardType;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestUtils {

    public static ConsumerUpdateDto buildConsumerUpdateDto() {
        Consumer consumer = buildConsumersList(1).get(0);

        Contacts contacts = buildContact();
        contacts.setId(1l);

        Address address = buildAddress(1, new Random());

        Card card = buildCard(new Random(), CardType.FOOD_CARD);

        return ConsumerUpdateDto.builder()
                .name(consumer.getName())
                .documentNumber(consumer.getDocumentNumber())
                .birthDate(consumer.getBirthDate())
                .contactsList(Collections.singletonList(contacts))
                .addressList(Collections.singletonList(address))
                .cardsList(Collections.singletonList(card))
                .build();
    }

    public static ConsumerCreateDto buildConsumerCreatedDto() {
        Consumer consumer = buildConsumersList(1).get(0);
        return ConsumerCreateDto.builder()
                .name(consumer.getName())
                .documentNumber(consumer.getDocumentNumber())
                .birthDate(consumer.getBirthDate())
                .contactsList(consumer.getContactsList())
                .addressList(consumer.getAddressList())
                .cardsList(consumer.getCardsList())
                .build();
    }

    public static Consumer buildConsumer() {
        return buildConsumersList(1).get(0);
    }

    public static List<Consumer> buildConsumersList(int qntds) {
        List<Consumer> consumerList = new ArrayList<>();
        for (int i = 0; i < qntds; i++) {
            consumerList.add(buildConsumerData(i));
        }
        return consumerList;
    }

    @SneakyThrows
    private static Consumer buildConsumerData(int i) {
        Random random = new Random();
        List<Contacts> contactsList = new ArrayList<>();
        List<Card> cardsList = new ArrayList<>();
        List<Address> addressList = new ArrayList<>();

        buildConsumerData(i, random, contactsList, cardsList, addressList);

        return Consumer.builder()
                .id((long) i)
                .name("consumer 01")
                .documentNumber("documentNumber")
                .birthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021"))
                .contactsList(contactsList)
                .cardsList(cardsList)
                .addressList(addressList)
                .build();

    }

    private static void buildConsumerData(int i, Random random, List<Contacts> contactsList, List<Card> cardsList, List<Address> addressList) {
        if (random.nextBoolean()) {
            addressList.add(buildAddress(i, random));
        }

        if (random.nextBoolean()) {
            cardsList.add(buildCard(random, CardType.FOOD_CARD));
        }

        if (random.nextBoolean()) {
            cardsList.add(buildCard(random, CardType.FUEL_CARD));
        }

        if (random.nextBoolean()) {
            cardsList.add(buildCard(random, CardType.DRUG_STORE));
        }

        if (random.nextBoolean()) {
            contactsList.add(buildContact());
        }
    }

    private static Contacts buildContact() {
        Contacts contacts = Contacts.builder()
                .email("contact01@gmail.com")
                .mobilePhoneNumber("(85)989881122")
                .residencePhoneNumber("(85) 989881122")
                .phoneNumber("(85) 989881122")
                .build();
        return contacts;
    }

    private static Card buildCard(Random random, CardType foodCard2) {
        return Card.builder()
                .cardNumber(generateCardNumber())
                .cardBalance(BigDecimal.valueOf(random.nextInt(1000) * random.nextDouble()).setScale(2, RoundingMode.FLOOR))
                .typeCard(foodCard2)
                .build();
    }

    private static Address buildAddress(int i, Random random) {
        Address addressA = Address.builder()
                .street("Rua do endereço " + i)
                .number(125 + random.nextInt(1000))
                .postalCode("654321564")
                .country("Pais " + i)
                .city("Cidade " + i)
                .build();
        return addressA;
    }

    private static String generateCardNumber() {
        return String.valueOf(new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 16);
    }
}
