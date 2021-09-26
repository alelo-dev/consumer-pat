package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.Client;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.enumerator.CardType;
import br.com.alelo.consumer.consumerpat.exception.DuplicatedRegistry;
import br.com.alelo.consumer.consumerpat.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.mapper.ClientMapper;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ClientRepository;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CardMapper cardMapper;

    @Autowired
    ClientMapper clientMapper;

    @Transactional
    public ResponseEntity add(final Integer cardNumber,
                       final CardType type,
                       final String documentNumber) {
        try {
            Optional<Client> optionalClient = clientRepository.findByDocumentNumber(documentNumber);
            Optional<Card> optionalCard = cardRepository.findByCardNumber(cardNumber);

            if (optionalClient.isPresent()) {
                if (optionalCard.isEmpty()) {
                    return  ResponseEntity.status(HttpStatus.CREATED).body(this.cardMapper.toDTO(cardRepository.save(Card.builder()
                                                                                        .cardNumber(cardNumber)
                                                                                        .client(optionalClient.get())
                                                                                        .balance(0L)
                                                                                        .type(type).build())));
                } else {
                    throw new Exception("Error Card already exist");
                }
            } else {
                throw new NotFoundException("Not found Client");
            }
        } catch ( DataIntegrityViolationException ex) {
            throw new DuplicatedRegistry("card");
        } catch ( NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch ( Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Transactional
    public ResponseEntity setBalance(final Integer cardNumber,
                                      final Double value) {
        try {
            Optional<Card> optional = this.cardRepository.findByCardNumber(cardNumber);

            if (optional.isPresent()) {
                CardDTO dto = this.cardMapper.toDTO(optional.get());
                dto.setBalance(dto.getBalance() + value);
                this.cardRepository.save(this.cardMapper.toEntity(dto));
                return ResponseEntity.ok()
                                    .body(this.cardMapper.toDTO(this.cardRepository.save(this.cardMapper.toEntity(dto))));

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
