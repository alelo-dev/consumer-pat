package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ExtractService;

@Controller
@RequestMapping("/extract")
public class ExtractController {

    @Autowired
    private ExtractService service;

    @ResponseBody
    @GetMapping()
    public List<Extract> listAllConsumers() {
        return service.list();
    }
}
