package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping(TransactionController.ENDPOINT)
public class TransactionController {

    public static final String ENDPOINT = "/transaction";

    @Autowired
    TransactionService service;


    @PostMapping("/{cnpj}")
    public ResponseEntity buy(@PathVariable("cnpj") final String cnpj,
                              @RequestParam final Integer cardNumber,
                              @RequestParam final String productDescription,
                              @RequestParam final Double value){
        return this.service.buy(cnpj,cardNumber,productDescription,value);
    }
}
