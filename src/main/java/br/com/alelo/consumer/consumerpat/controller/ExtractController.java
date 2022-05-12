package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extract")
public class ExtractController {

    @Autowired
    ExtractService extractService;

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/listAll")
    public List<Extract> listAllExtracts() {
        return extractService.findAll();
    }
}
