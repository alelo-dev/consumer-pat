package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.ValidationCard;
import br.com.alelo.consumer.consumerpat.exception.ValidationConsumer;
import br.com.alelo.consumer.consumerpat.exception.ValidationEstablishment;
import br.com.alelo.consumer.consumerpat.respository.*;
import br.com.alelo.consumer.consumerpat.types.EstablishmentTypeEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsumerService {

    private final ConsumerRepository repository;
    private final ExtractRepository extractRepository;
    private final CardRepository cardRepository;

    public List<Consumer> getListAllConsumers() {
        return repository.findAll();
    }

    public void createConsumer(Consumer consumer) throws ValidationConsumer {

        //[Sergio] Validar se os cartoes ja estão cadastrados
        for(Card card : consumer.getCards()){
            if(cardRepository.findByNumber(card.getNumber()) != null){
                throw new ValidationConsumer("Cartão já cadastrado: " + card.getNumber());
            }
        }
        repository.save(consumer);
    }

    public void updateConsumer(Consumer consumer) throws ValidationConsumer {
        if(consumer.getId() == null){throw new ValidationConsumer(("Id do cliente não informado: " +consumer.getId()));}
        //[Sergio] Validar se tem o objeto na base de dados antes de alterar
        Optional<Consumer> consumerBase = repository.findById(consumer.getId());
        if(consumerBase.isEmpty()){
            throw new ValidationConsumer(("Cliente não encontado: " +consumer.getId()));
        }
        //[Sergio] Não permitir alterar o saldo
        for(Card cardBase: consumerBase.get().getCards()){
            consumer.getCards().stream().findFirst().filter(
                    card -> card.getNumber().equals(cardBase.getNumber())).get().setBalance(cardBase.getBalance());
        }

        repository.save(consumer);
    }

    public void setBalance(Long cardNumber, double value) throws ValidationCard {
        Card card = cardRepository.findByNumber(cardNumber);
        if (card == null) {
            throw new ValidationCard("Cartão não cadastrado: " + cardNumber);
        }
        card.setBalance(card.getBalance() + value);
        cardRepository.save(card);
    }

    public void buy(int establishmentType, String establishmentName, Long cardNumber, String productDescription, double value) throws ValidationCard, ValidationEstablishment {
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */
        //[Sergio] Para um melhor entendimento do codido, estrutura e tratamento de erro foi adicionado um switch com ENUM
        //[Sergio] validacao do cartão e codigo do tipo de estabelecimento
        Card card = cardRepository.findByNumber(cardNumber);
        if (card == null) {
            throw new ValidationCard("Cartão não cadastrado: " + cardNumber);
        }
        switch (EstablishmentTypeEnum.getByValue(establishmentType)) {
            case FOOD:
                // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
                Double cashback = (value / 100) * 10;
                value = value - cashback;
                break;

            case DRUGSTORE:
                break;

            case FUEL:
                // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
                Double tax = (value / 100) * 35;
                value = value + tax;

            default:
                //Tratamento de erro caso o tipo de estabelecimento não tiver no CADASTRADO.
                throw new ValidationEstablishment("Tipo de estabilecimento não encontrado: " + establishmentType);
        }
        card.setBalance(card.getBalance() - value);
        cardRepository.save(card);
        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }
}
