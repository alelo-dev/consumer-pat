package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.exception.BusinessSaldoException;
import br.com.alelo.consumer.consumerpat.service.ExtractFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/extracts")
public class ExtractConstroller {

    private final ExtractFactory factory;

    @PostMapping(value = "/buy")
    public ResponseEntity<String> buy(@RequestBody ExtractDTO dto) {
        try {
            factory.findStrategyByEstablishmentId(EstablishmentType.getEstablishmentTypeById(dto.getEstablishmentNameId())).buy(dto);
            return ResponseEntity.ok().build();
        } catch (BusinessSaldoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
