package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class CardServiceImpl implements CardService {

    private ConsumerRepository consumerRepository;
    private CardRepository cardRepository;
    private ExtractRepository extractRepository;

    public CardServiceImpl(CardRepository cardRepository, 
                           ExtractRepository extractRepository, 
                           ConsumerRepository consumerRepository) {
        this.cardRepository = cardRepository;
        this.extractRepository = extractRepository;
        this.consumerRepository = consumerRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResultStatus addCard(Long idConsumer, CardDTO cardDTO) {
        Optional<Consumer> consumer = consumerRepository.findById(idConsumer);
        if (consumer.isEmpty()) {
            return ResultStatus.builder().isOk(false).error("Consumer nao encontrado").build();
        }
        Card card = new Card(idConsumer, cardDTO);
        cardRepository.save(card);
        return ResultStatus.builder().isOk(true).build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<CardDTO> setBalance(String cardNumber, BigDecimal value) {
        Optional<Card> optional = cardRepository.findByCardNumber(cardNumber);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Card card = optional.get();
        card.setBalance(value);
        cardRepository.save(card);   
        return Optional.of(toCardDTO(card));
    
    }

    /**
     * Registra uma compra e retorna o balance atualizado
     * Se o cartão não existir retorna vazio
     * Para compras no cartão de alimentação o cliente recebe um desconto de 10%
     * Nas compras com o cartão de combustivel existe um acrescimo de 35%;
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<BigDecimal> registerBuy(String cardNumber, BuyDTO buyDTO) {
        
        Optional<Card> optional = cardRepository.findByCardNumber(cardNumber);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Card card = optional.get();
        if (buyDTO.getEstablishmentType() == EstablishmentType.ALIMENTACAO.getValue()) {
            BigDecimal cashback  = buyDTO.getValue()
                                        .divide(new BigDecimal(100)
                                        .multiply(new BigDecimal(10)));
            buyDTO.setValue(buyDTO.getValue().subtract(cashback));
            card.setBalance(card.getBalance().subtract(buyDTO.getValue()));
        }else if(buyDTO.getEstablishmentType() == EstablishmentType.FARMACIA.getValue()) {
            card.setBalance(card.getBalance().subtract(buyDTO.getValue()));
        } else {
            BigDecimal tax  = buyDTO.getValue()
                                .divide(new BigDecimal(100))
                                .multiply(new BigDecimal(35));
            buyDTO.setValue(buyDTO.getValue().add(tax));
            card.setBalance(card.getBalance().subtract(buyDTO.getValue()));
        }
        cardRepository.save(card);

        Extract extract = new Extract(buyDTO.getEstablishmentName(), 
                                      buyDTO.getProductDescription(), 
                                      new Date(), 
                                      card,
                                      buyDTO.getValue());
        extractRepository.save(extract);
        return Optional.of(card.getBalance());
    }

    private CardDTO toCardDTO(Card card) {
        CardDTO cardDTO = new CardDTO(card);
        return cardDTO;
    }

}
