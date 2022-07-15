package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.model.entity.Address;
import br.com.alelo.consumer.consumerpat.model.entity.Card;
import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.entity.Contact;
import br.com.alelo.consumer.consumerpat.model.enums.ContactType;
import br.com.alelo.consumer.consumerpat.model.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.model.repository.AddressRepository;
import br.com.alelo.consumer.consumerpat.model.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.model.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.model.repository.ContactRepository;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.ConsumerFilterVO;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.NewConsumerFormVO;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.UpdateConsumerFormVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.fixture.ConsumerPatFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class ConsumerServiceImplTest {

    private static final String NOT_FOUND_MESSAGE = "Consumer not found!";
    private static final String DOCUMENT_ALREADY_EXISTS = "There is already a consumer with the informed document (CPF)!";

    @MockBean
    private ConsumerRepository consumerRepository;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private ContactRepository contactRepository;

    @MockBean
    private CardRepository cardRepository;

    @Autowired
    private ConsumerServiceImpl consumerService;

    @Test
    void findById_WithInvalidID_ShouldThrowResourceNotFoundException() {
        given(consumerRepository.findById(CONSUMER_INVALID_ID)).willReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            consumerService.findById(CONSUMER_INVALID_ID);
        });
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(NOT_FOUND_MESSAGE));
    }

    @Test
    void findById_WithValidID_ShouldReturnCorrespondingConsumer() {
        Consumer consumer = buildConsumer();

        given(consumerRepository.findById(consumer.getId())).willReturn(Optional.of(consumer));

        Consumer consumerResult = consumerService.findById(consumer.getId());

        assertNotNull(consumerResult);
        assertEquals(consumer.getId(), consumerResult.getId());
        assertEquals(consumer.getName(), consumerResult.getName());
        assertEquals(consumer.getDocumentNumber(), consumerResult.getDocumentNumber());
        assertEquals(consumer.getBirthDate(), consumerResult.getBirthDate());
    }

    @Test
    void findAll_WithEmptyResult_ShouldReturnEmptyResultInPaginatedList() {
        given(consumerRepository.findAllByFilters(any(ConsumerFilterVO.class), any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of()));

        ConsumerFilterVO filters = new ConsumerFilterVO();

        Page<Consumer> page = consumerService.findAll(filters, buildPageable(filters));

        assertNotNull(page);
        assertNotNull(page.getContent());
        assertEquals(0, page.getTotalElements());
    }

    @Test
    void findAll_WithNonEmptyResult_ShouldReturnConsumerInPaginatedList() {
        Consumer consumer = buildConsumer();

        given(consumerRepository.findAllByFilters(any(ConsumerFilterVO.class), any(Pageable.class)))
                .willReturn(new PageImpl<Consumer>(List.of(consumer)));

        ConsumerFilterVO filters = new ConsumerFilterVO();

        Page<Consumer> page = consumerService.findAll(filters, buildPageable(filters));

        assertNotNull(page);
        assertNotNull(page.getContent());
        assertEquals(1, page.getTotalElements());

        Consumer consumer1 = page.getContent().get(0);

        assertEquals(consumer.getId(), consumer1.getId());
        assertEquals(consumer.getName(), consumer1.getName());
        assertEquals(consumer.getDocumentNumber(), consumer1.getDocumentNumber());
        assertEquals(consumer.getBirthDate(), consumer1.getBirthDate());
    }

    @Test
    void save_WithDuplicateDocument_ShouldThrowBusinessException() {
        Consumer consumer = buildConsumer();

        given(consumerRepository.findByDocumentNumber(any())).willReturn(Optional.of(consumer));

        NewConsumerFormVO form = new NewConsumerFormVO();
        form.setDocumentNumber(consumer.getDocumentNumber());

        Exception exception = assertThrows(BusinessException.class, () -> {
            consumerService.save(form);
        });
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(DOCUMENT_ALREADY_EXISTS));
    }

    @Test
    void save_WithAllMandatoryParameters_ShouldPersistConsumerAndAddressData() {
        Consumer consumer = buildConsumer();
        consumer.setAddress(buildAddress());

        BigDecimal foodCardBalance = BigDecimal.valueOf(100.00);
        String email = "consumer@alelo.com";

        NewConsumerFormVO form = buildNewConsumerFormVo(consumer);
        form.setFoodCardBalance(foodCardBalance);
        form.setEmail(email);

        given(consumerRepository.findByDocumentNumber(any())).willReturn(Optional.empty());
        given(consumerRepository.save(any(Consumer.class))).willAnswer(i -> i.getArgument(0, Consumer.class));
        given(addressRepository.save(any(Address.class))).willAnswer(i -> i.getArgument(0, Address.class));
        given(contactRepository.saveAll(any(List.class))).willAnswer(i -> i.getArgument(0, List.class));
        given(cardRepository.saveAll(any(List.class))).willAnswer(i -> i.getArgument(0, List.class));

        Consumer consumerResult = consumerService.save(form);

        assertNotNull(consumerResult);
        assertEquals(form.getName(), consumerResult.getName());
        assertEquals(form.getDocumentNumber(), consumerResult.getDocumentNumber());
        assertEquals(form.getBirthDate(), consumerResult.getBirthDate());

        assertNotNull(consumerResult.getAddress());
        Address address = consumerResult.getAddress();
        assertEquals(form.getStreet(), address.getStreet());
        assertEquals(form.getNumber(), address.getNumber());
        assertEquals(form.getCity(), address.getCity());
        assertEquals(form.getCountry(), address.getCountry());
        assertEquals(form.getPostalCode(), address.getPostalCode());

        assertNotNull(consumerResult.getCards());
        assertEquals(1, consumerResult.getCards().size());
        Card card = consumerResult.getCards().iterator().next();
        assertEquals(EstablishmentType.FOOD, card.getType());
        assertEquals(foodCardBalance, card.getBalance());

        assertNotNull(consumerResult.getContacts());
        assertEquals(1, consumerResult.getContacts().size());
        Contact contact = consumerResult.getContacts().iterator().next();
        assertEquals(ContactType.EMAIL, contact.getType());
        assertEquals(email, contact.getValue());
    }

    @Test
    void update_WithInvalidID_ShouldThrowResourceNotFoundException() {
        given(consumerRepository.findById(CONSUMER_INVALID_ID)).willReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            consumerService.update(CONSUMER_INVALID_ID, new UpdateConsumerFormVO());
        });
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(NOT_FOUND_MESSAGE));
    }

    @Test
    void update_WithAllMandatoryParameters_ShouldUpdateConsumerData() {
        Consumer consumer = buildConsumer();

        String newConsumerName = "another name";

        UpdateConsumerFormVO form = buildUpdateConsumerFormVO(consumer);
        form.setName(newConsumerName);

        given(consumerRepository.findById(consumer.getId())).willReturn(Optional.of(consumer));
        given(consumerRepository.save(any(Consumer.class))).willAnswer(i -> i.getArgument(0, Consumer.class));

        Consumer consumerResult = consumerService.update(consumer.getId(), form);

        assertNotNull(consumerResult);
        assertEquals(newConsumerName, consumerResult.getName());
        assertEquals(consumer.getDocumentNumber(), consumerResult.getDocumentNumber());
        assertEquals(consumer.getBirthDate(), consumerResult.getBirthDate());
    }

    private Pageable buildPageable(ConsumerFilterVO filters) {
        return PageRequest.of(
            filters.getPage(),
            filters.getSize(),
            Sort.by(
                Sort.Direction.fromString(filters.getSortDirection().name()),
                filters.getSortField().getValue()
            ));
    }
}