package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.request.CardPurchaseRequestDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
 * Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
 * <p>
 * Tipos de estabelcimentos
 * 1 - Alimentação (food)
 * 2 - Farmácia (DrugStore)
 * 3 - Posto de combustivel (Fuel)
 */
@Service
public class CardPurchaseService {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    public void buy(CardPurchaseRequestDto cardPurchaseRequestDto) {

        if (cardPurchaseRequestDto.getEstablishmentType() == 1) {
            Optional<Consumer> consumer = repository.findByFoodCardNumber(cardPurchaseRequestDto.getCardNumber());
            if (consumer.isPresent()) {
                Double valueToCharge = this.chargeFromFoodCard(cardPurchaseRequestDto, consumer.get());
                consumer.get().setFoodCardBalance(valueToCharge);
                repository.save(consumer.get());
            } else {
                throw new RuntimeException("Cliente sem Cartao Alimentacao");
            }
        }

        if (cardPurchaseRequestDto.getEstablishmentType() == 2) {
            Optional<Consumer> consumer = repository.findByDrugstoreCardNumber(cardPurchaseRequestDto.getCardNumber());
            if (consumer.isPresent()) {
                Double valueToCharge = this.chargeFromDrugstoreCard(cardPurchaseRequestDto, consumer.get());
                consumer.get().setDrugstoreCardBalance(valueToCharge);
                repository.save(consumer.get());
            } else {
                throw new RuntimeException("Cliente sem Cartao Farmacia");
            }
        }

        if (cardPurchaseRequestDto.getEstablishmentType() == 3) {
            Optional<Consumer> consumer = repository.findByFuelCardNumber(cardPurchaseRequestDto.getCardNumber());
            if (consumer.isPresent()) {
                Double valueToCharge = this.chargeFromFuelCard(cardPurchaseRequestDto, consumer.get());
                consumer.get().setFuelCardBalance(valueToCharge);
                repository.save(consumer.get());
            } else {
                throw new RuntimeException("Cliente sem Cartao Combustivel");
            }
        }

        Extract extract = new Extract(cardPurchaseRequestDto.getEstablishmentName(),
                cardPurchaseRequestDto.getProductDescription(),
                new Date(),
                cardPurchaseRequestDto.getCardNumber(),
                cardPurchaseRequestDto.getValue());

        extractRepository.save(extract);
    }

    /**
     * Para compras no cartão de alimentação o cliente recebe um desconto de 10%
     * @param cardPurchaseRequestDto informacoes de requisicao
     */
    private Double chargeFromFoodCard(CardPurchaseRequestDto cardPurchaseRequestDto, final Consumer consumer) {
        Double cashback = cardPurchaseRequestDto.getValue()  * 0.10;
        double dicountValue = cardPurchaseRequestDto.getValue() - cashback;
        return consumer.getFoodCardBalance() - dicountValue;
    }

    private Double chargeFromDrugstoreCard(final CardPurchaseRequestDto cardPurchaseRequestDto, final Consumer consumer) {
        return consumer.getDrugstoreCardBalance() - cardPurchaseRequestDto.getValue();
    }

    /**
     * Nas compras com o cartão de combustivel existe um acrescimo de 35%;
     */
    private Double chargeFromFuelCard(final CardPurchaseRequestDto cardPurchaseRequestDto, final Consumer consumer) {
        double taxIncrease = cardPurchaseRequestDto.getValue() * 1.35;
        return consumer.getFuelCardBalance() - taxIncrease;
    }
}
