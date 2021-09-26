package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.Client;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.ClientDTO;
import br.com.alelo.consumer.consumerpat.enumerator.CardType;
import br.com.alelo.consumer.consumerpat.exception.DuplicatedRegistry;
import br.com.alelo.consumer.consumerpat.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.mapper.ClientMapper;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ClientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Optional;

@Service
@Log4j2
public class CardService {

    @Autowired
    CardRepository repository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CardMapper cardMapper;

    @Autowired
    ClientMapper clientMapper;

    @Transactional
    public CardDTO add(final Integer cardNumber,
                       final CardType type,
                       final String documentNumber) {
        try {
            Client client = clientRepository.findByDocumentNumber(documentNumber);
            return  this.cardMapper.toDTO(repository.save(Card.builder()
                                                            .cardNumber(cardNumber)
                                                            .client(client)
                                                            .balance(0L)
                                                            .type(type).build()));
        } catch ( DataIntegrityViolationException ex) {
            throw new DuplicatedRegistry("card");
        }
    }

    @Transactional
    public ResponseEntity<CardDTO> setBalance(final Integer cardNumber,
                                      final Double value) {
        try {
            Optional<Card> optional = this.repository.findByCardNumber(cardNumber);

            if (optional.isPresent()) {
                CardDTO dto = this.cardMapper.toDTO(optional.get());
                dto.setBalance(dto.getBalance() + value);
                this.repository.save(this.cardMapper.toEntity(dto));
                return ResponseEntity.ok()
                                    .body(this.cardMapper.toDTO(this.repository.save(this.cardMapper.toEntity(dto))));

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body(null);
            }
        } catch ( Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(null);
        }
    }

}
