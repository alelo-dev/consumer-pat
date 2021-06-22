package br.com.alelo.consumer.consumerpat.controller.v1;

import br.com.alelo.consumer.consumerpat.data.vo.v1.ConsumerVO;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("/extract/v1")
public class ExtractController {

    @Autowired
    ExtractService service;

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, Integer cardNumber, String productDescription, BigDecimal value) {
        service.buy(establishmentType, cardNumber, value, establishmentName, productDescription);
    }
}
