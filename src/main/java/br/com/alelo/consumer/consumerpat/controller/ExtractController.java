package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.enums.CompanyType;
import br.com.alelo.consumer.consumerpat.exception.BusinessSaldoException;
import br.com.alelo.consumer.consumerpat.service.ExtractFactory;
import br.com.alelo.consumer.consumerpat.service.ExtractStrategy;
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
public class ExtractController {
    private final ExtractFactory factory;

    /**
     * Realiza uma compra com base no DTO fornecido.
     *
     * @param dto DTO de compra
     * @return ResponseEntity com status OK se bem-sucedido ou BAD_REQUEST com mensagem de erro
     */
    @PostMapping("/buy")
    public ResponseEntity<String> buy(@RequestBody ExtractDTO dto) {
        try {
            processBuy(dto);
            return ResponseEntity.ok().build();
        } catch (BusinessSaldoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    private void processBuy(ExtractDTO dto) throws BusinessSaldoException {
        CompanyType companyType = CompanyType.getCompanyTypeById(dto.getCompanyNameId());
        ExtractStrategy strategy = factory.findStrategyByCompanyId(companyType);
        strategy.buy(dto);
    }
}


