package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.exception.ConsumerException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private final ConsumerRepository consumerRepository;
    @Autowired
    private final ExtractRepository extractRepository;

    @Override
    public List<Consumer> getAllConsumersList() {

        return consumerRepository.findAll();
    }

    @Override
    public void createConsumer(Consumer consumer) {
        consumerRepository.save(consumer);

    }

    @Override
    public void updateConsumer(Consumer consumer) throws ConsumerException {
        Consumer consumerOld = consumerRepository.findById(consumer.getId())
                .orElseThrow(() -> new ConsumerException("Consumer [%d] não encontrado", consumer.getId()));
        if (consumerOld.getFoodCardBalance() != consumer.getFoodCardBalance()) {
            throw new ConsumerException("Saldo de alimentação não deve ser alterado diretamente");
        }
        if (consumerOld.getDrugstoreCardBalance() != consumer.getDrugstoreCardBalance()) {
            throw new ConsumerException("Saldo de Farmácia não deve ser alterado diretamente");
        }
        if (consumerOld.getFuelCardBalance() != consumer.getFuelCardBalance()) {
            throw new ConsumerException("Saldo de Cartão combustível não deve ser alterado diretamente");
        }
        consumerRepository.save(consumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @Override
    public void addBalance(int cardNumber, double value) throws ConsumerException {
        Consumer consumer = null;
        consumer = consumerRepository.findByDrugstoreNumber(cardNumber);

        if (consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            consumerRepository.save(consumer);
        } else {
            consumer = consumerRepository.findByFoodCardNumber(cardNumber);
            if (consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                consumerRepository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = consumerRepository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                consumerRepository.save(consumer);
            }
        }

    }

    private Consumer findByCardNumber(int establishmentType, int cardNumber) throws ConsumerException {
        Consumer result = null;
        switch (establishmentType) {
            case 1:
                result = consumerRepository.findByFoodCardNumber(cardNumber);
                break;
            case 2:
                result = consumerRepository.findByDrugstoreNumber(cardNumber);
                break;
            case 3:
                result = consumerRepository.findByFuelCardNumber(cardNumber);
                break;
        }
        if (result == null) {
            throw new ConsumerException("Não foi encontrado cartão para o tipo informado");
        }
        return result;
    }

    @Override
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription,
            double value) throws ConsumerException {

        Consumer consumer = null;
        if (establishmentType == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback = (value / 100) * 10;
            value = value - cashback;
            consumer = findByCardNumber(establishmentType, cardNumber);
            double balance = calculateBalance(consumer.getFoodCardBalance(), value);
            consumer.setFoodCardBalance(balance);
            consumerRepository.save(consumer);

        } else if (establishmentType == 2) {
            consumer = findByCardNumber(establishmentType, cardNumber);
            double balance = calculateBalance(consumer.getDrugstoreCardBalance(), value);
            consumer.setDrugstoreCardBalance(balance);
            consumerRepository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax = (value / 100) * 35;
            value = value + tax;

            consumer = findByCardNumber(establishmentType, cardNumber);
            double balance = calculateBalance(consumer.getFuelCardBalance(), value);
            consumer.setFuelCardBalance(balance);
            consumerRepository.save(consumer);
        }

        Extract extract = new Extract(null, establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }

    private double calculateBalance(Double oldBalance, double value) throws ConsumerException {
        double result = oldBalance - value;
        if (result < 0) {
            throw new ConsumerException("Saldo insuficiente");
        }
        return result;
    }

}
