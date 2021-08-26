package br.com.alelo.consumer.consumerpat.controller.v1;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;


@Controller
@RequestMapping("/consumer")
@Deprecated
public class ConsumerController {

    private final ConsumerRepository consumerRepository;
    private final CardRepository cardRepository;

    @Autowired
    public ConsumerController(ConsumerRepository consumerRepository, CardRepository cardRepository) {
        this.consumerRepository = consumerRepository;
        this.cardRepository = cardRepository;
    }

    @GetMapping("/consumerList")
    public Page<Consumer> listAllConsumers(Pageable page) {
        return consumerRepository.findAll(page);
    }


    @PostMapping(value = "/createConsumer")
    public void createConsumer(@RequestBody ConsumerDTO consumerDTO) {
        consumerRepository.save(consumerDTO.parseConsumer());
    }

    @PostMapping(value = "/updateConsumer")
    public void updateConsumer(@RequestBody ConsumerDTO consumerDTO) {
        consumerRepository.save(consumerDTO.parseConsumerWithoutCard());
    }

    @GetMapping(value = "/setcardbalance")
    public void setBalance(int cardNumber, double value) throws Exception {
        Optional<Card> card = cardRepository.findById(cardNumber);
        if(card.isPresent())
            card.get().addBalance(value);
        else
            throw new Exception("O numero do cartao não existe");
        cardRepository.save(card.get());
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    @Transactional
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) throws Exception {
        Optional<Card> card = cardRepository.findById(cardNumber);
        if(card.isPresent()){
            card.get().buy(establishmentName,productDescription,new BigDecimal(value),establishmentType);
            cardRepository.save(card.get());
        }else {
            throw new Exception("O numero do cartao não existe");
        }


        if (establishmentType == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback  = (value / 100) * 10;
            value = value - cashback;

//            consumerDTO.setFoodCardBalance(consumerDTO.getFoodCardBalance() - value);
            //repository.save(consumerDTO);

        }else if(establishmentType == 2) {
//            consumerDTO.setDrugstoreCardBalance(consumerDTO.getDrugstoreCardBalance() - value);
            //repository.save(consumerDTO);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (value / 100) * 35;
            value = value + tax;

//            consumerDTO.setFuelCardBalance(consumerDTO.getFuelCardBalance() - value);
            //repository.save(consumerDTO);
        }
    }
}
