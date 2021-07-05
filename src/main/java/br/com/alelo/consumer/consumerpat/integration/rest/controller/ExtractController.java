package br.com.alelo.consumer.consumerpat.integration.rest.controller;

import br.com.alelo.consumer.consumerpat.domain.service.ExtractService;
import br.com.alelo.consumer.consumerpat.domain.service.exception.ApiException;
import br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request.ExtractRequestPostV1;
import br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.response.ExtractResponseV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/extract")
public class ExtractController {

    @Autowired
    private ExtractService extractService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ExtractResponseV1> buy(@RequestBody ExtractRequestPostV1 extractRequestPostV1) throws ApiException {
            return ResponseEntity.status(HttpStatus.CREATED).body(ExtractResponseV1.transformToResponse(extractService.buy(ExtractRequestPostV1.transformToExtract(extractRequestPostV1))));
    }

}
