package br.com.alelo.consumer.consumerpat.resource.v1;

import br.com.alelo.consumer.consumerpat.dto.v1.request.CardBuyV1RequestDto;
import br.com.alelo.consumer.consumerpat.dto.v1.request.CardRechargeV1RequestDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cards")
public class CardResource {

    @PutMapping("/recharge")
    public void recharge(@RequestBody CardRechargeV1RequestDto request) {

    }

    @PutMapping("/buys")
    public void buys(@RequestBody CardBuyV1RequestDto request) {

    }
}
