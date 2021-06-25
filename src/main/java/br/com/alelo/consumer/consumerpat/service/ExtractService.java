package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class ExtractService {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    ExtractRepository extractRepository;

    @Autowired
    ConsumerService consumerService;

    private static final int FOOD = 1;
    private static final int DRUGSTORE = 2;
    private static final int FUEL = 3;

    public Extract buy(Extract extract) {

        Consumer consumer = null;
        double value = extract.getValue();
        Long cardNumber = extract.getCardNumber();
        String productDescription = extract.getProductDescription();
        int establishmentType = extract.getEstablishmentNameId();
        extract.setDateBuy(convertToDate());

        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        switch(establishmentType) {

            case FOOD:
                // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
                Double cashback  = (value / 100) * 10;
                value = value - cashback;
                extract.setEstablishmentName("Alimentação");

                try {
                    consumer = consumerService.findByFoodCardNumber(cardNumber);
                    consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
                    consumerService.save(consumer);
                } catch (NullPointerException e) {
                    String msg = "O cartão número: "+cardNumber+ ", não foi encontrado para este estabelecimento.";
                    throw new NullPointerException(msg);
                }

                break;

            case DRUGSTORE:
                extract.setEstablishmentName("Farmácia");

                try {
                    consumer = consumerService.findByDrugstoreNumber(cardNumber);
                    consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
                    consumerService.save(consumer);
                } catch (NullPointerException e) {
                    String msg = "O cartão número: "+cardNumber+ ", não foi encontrado para este estabelecimento.";
                    throw new NullPointerException(msg);
                }

                break;

            case FUEL:
                // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
                Double tax  = (value / 100) * 35;
                value = value + tax;
                extract.setEstablishmentName("Combustível");

                try {
                    consumer = consumerService.findByFuelCardNumber(cardNumber);
                    consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
                    consumerService.save(consumer);
                } catch (NullPointerException e) {
                    String msg = "O cartão número: "+cardNumber+ ", não foi encontrado para este estabelecimento.";
                    throw new NullPointerException(msg);
                }
                break;

            default:
                throw new NullPointerException("Número do cartão não condiz com o tipo do estabelecimento");
        }

        extractRepository.save(extract);

        return extract;
    }

    private Date convertToDate () {
        LocalDate localDate = LocalDate.now();
        Date dateBuy = java.sql.Date.valueOf(localDate);

        return dateBuy;
    }
}
