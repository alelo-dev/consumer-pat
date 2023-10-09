package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@Controller
@RequestMapping("/extract")
public class ExtractController {

    @Autowired
    private ExtractRepository extractRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/createextract")
    public void createextract(@RequestBody Extract extract) {
        log.info("extrato criado");
        extractRepository.save(extract);
    }
}
