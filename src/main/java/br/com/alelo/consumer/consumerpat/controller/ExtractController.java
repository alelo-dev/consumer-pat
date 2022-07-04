package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.service.ExtractService;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/extract")
public class ExtractController {
    
    @Autowired
    ExtractService extractService;

    /* Deve listar todos os extratos  */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/extractList", method = RequestMethod.GET)
    public List<Extract> listAllConsumers() {
        return extractService.listAllExtracts();
    }
}
