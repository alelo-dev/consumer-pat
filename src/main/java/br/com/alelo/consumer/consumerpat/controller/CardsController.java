package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Cards;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardsRepository;
import br.com.alelo.consumer.consumerpat.service.CardsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/card")
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private Cards cards;

    CardsService cardsService = new CardsService();
    Extract extract = new Extract();

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/setcardbalance")
    public void balance() {
        cardsService.setBalance();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/buy")
    public void buy() {
        cardsService.buy();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/createcard")
    public void createCard(@RequestBody Cards cards) {
        log.info("cartão criado");
        cardsRepository.save(cards);
    }
}