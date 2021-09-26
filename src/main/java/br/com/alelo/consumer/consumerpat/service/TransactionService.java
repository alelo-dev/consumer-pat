package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.Establishment;
import br.com.alelo.consumer.consumerpat.domain.Transaction;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.dto.TransactionDTO;
import br.com.alelo.consumer.consumerpat.enumerator.CardType;
import br.com.alelo.consumer.consumerpat.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.mapper.EstablishmentMapper;
import br.com.alelo.consumer.consumerpat.mapper.TransactionMapper;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.repository.TransactionRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private EstablishmentMapper establishmentMapper;

    public ResponseEntity buy(final String cnpj,
                                              final Integer cardNumber,
                                              final String productDescription,
                                              final Double value) {
        try {
            Optional<Establishment>  optionalEstablishment = this.establishmentRepository.findByCnpj(cnpj);
            Optional<Card> optionalCard = this.cardRepository.findByCardNumber(cardNumber);

            if (optionalEstablishment.isPresent() && optionalCard.isPresent()) {
                EstablishmentDTO establishmentDTO = this.establishmentMapper.toDTO(optionalEstablishment.get());
                CardDTO cardDTO = this.cardMapper.toDTO(optionalCard.get());

                if ( establishmentDTO.getType().toString().equals(cardDTO.getType().toString())) {

                    CardDTO updateCardDTO = this.calculateDebt(cardDTO, value);
                    if ( updateCardDTO.getBalance() >= 0 ) {
                        TransactionDTO transactionDTO = this.transactionMapper.toDTO(this.transactionRepository.save(this.transactionMapper.toEntity(TransactionDTO.builder()
                                .card(this.cardMapper.toDTO(this.cardRepository.save(this.cardMapper.toEntity(updateCardDTO))))
                                .establishment(establishmentDTO)
                                .dateBuy(LocalDateTime.now())
                                .productDescription(Optional.ofNullable(productDescription).orElse(null))
                                .value(value)
                                .build())));

                        return ResponseEntity.status(HttpStatus.CREATED)
                                .body(transactionDTO);
                    } else {
                        throw new Exception("Does not have enough balance ");
                    }
                } else {
                    throw new Exception("Non-corresponding card with establishment");
                }
            } else {
                throw new NotFoundException("Not Found Card or Establishment");
            }

        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private CardDTO calculateDebt(CardDTO cardDTO, Double value) {
        if (cardDTO.getType().equals(CardType.FOOD)) {
            Double cashback  = (value / 100) * 10;
            value = value - cashback;
            cardDTO.setBalance(cardDTO.getBalance() - value);
        } else if (cardDTO.getType().equals(CardType.FUEL)) {
            Double tax  = (value / 100) * 35;
            value = value + tax;
            cardDTO.setBalance(cardDTO.getBalance() - value);
        } else {
            cardDTO.setBalance(cardDTO.getBalance() - value);
        }
        return cardDTO;
    }

}
