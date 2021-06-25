package br.com.alelo.consumer.consumerpat.controller.v1;

import br.com.alelo.consumer.consumerpat.service.ExtractService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/extract/v1")
public class ExtractController {

    @Autowired
    ExtractService service;

    @ApiOperation("Registra compra para cartão informado efetuando disconto ou acréscimo conforme produto PAT")
    @ResponseBody
    @PostMapping(value = "/buy")
    public void buy(@RequestParam int establishmentType,
                    @RequestParam String establishmentName,
                    @RequestParam Long cardNumber,
                    @RequestParam String productDescription,
                    @RequestParam("price") BigDecimal value) {
        service.buy(establishmentType, cardNumber, value, establishmentName, productDescription);
    }
}
