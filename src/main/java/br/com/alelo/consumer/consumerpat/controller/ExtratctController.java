package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ExtratcService;


@Controller
@RequestMapping("/extratcs")
public class ExtratctController {

    private final ExtratcService service;
    
    @Autowired
    public ExtratctController(ExtratcService service) {
        this.service = service;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<Extract> listAllExtratByConsumer(@RequestParam(name = "consumerName") String consumerName) {
        return service.findAllByConsumer(consumerName);
    }

}
