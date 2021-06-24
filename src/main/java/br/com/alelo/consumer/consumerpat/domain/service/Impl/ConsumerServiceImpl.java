package br.com.alelo.consumer.consumerpat.domain.service.Impl;

import br.com.alelo.consumer.consumerpat.domain.model.Consumer;
import br.com.alelo.consumer.consumerpat.domain.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.dto.BuyItemRequestDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerCreationDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerUpdateDto;
import br.com.alelo.consumer.consumerpat.mappers.AddressMapper;
import br.com.alelo.consumer.consumerpat.mappers.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.mappers.ContactsMapper;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository repository;

    public void setBalance(int cardNumber, double value) {

        var consumer = repository.findByCardsCardNumber(cardNumber);

        consumer.ifPresent(con->{
            var card = con.getCards().stream()
                    .filter(car-> car.getCardNumber() == cardNumber)
                    .findFirst();
            card.ifPresent(car-> car.addBalance(value));
            repository.save(con);
        });
    }

    @Override
    public void save(ConsumerCreationDto consumerCreationDto) {
        var consumer = ConsumerMapper.mapConsumerCreationDtoToConsumer(consumerCreationDto);
        repository.save(consumer);
    }

    @Override
    public void update(ConsumerUpdateDto consumerUpdateDto) {
        var consumer = repository.findById(consumerUpdateDto.getId());
        consumer.ifPresent(c->{
            c.setAddress(AddressMapper.mapAddressUpdateDtoToAddress(consumerUpdateDto.getAddress(), c.getAddress()));
            c.setBirthDate(consumerUpdateDto.getBirthDate());
            c.setContacts(ContactsMapper.mapContactsUpdateDtoToContacts(consumerUpdateDto.getContacts(), c.getContacts()));
            c.setDocumentNumber(consumerUpdateDto.getDocumentNumber());
            c.setName(consumerUpdateDto.getName());
            repository.save(c);
        });
    }

    @Override
    public Page<Consumer> listAllConsumers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public BuyItemRequestDto buy(BuyItemRequestDto buyItemRequestDto) {

        Optional<Consumer> consumer = repository.findByCardsCardNumber(buyItemRequestDto.getCardNumber());

        consumer.ifPresent(con-> {
            var card = con.getCards().stream()
                    .filter(car-> car.getCardNumber() == buyItemRequestDto.getCardNumber())
                    .findFirst();
            card.ifPresent(car->{
                buyItemRequestDto
                        .setValue(CardCalculatorFactory
                                .getCardCalculator(buyItemRequestDto.getEstablishmentType())
                                .calculate(buyItemRequestDto.getValue()));
                car.removeBalance(buyItemRequestDto.getValue());
            });
            repository.save(con);
        });

        return buyItemRequestDto;
    }
}
