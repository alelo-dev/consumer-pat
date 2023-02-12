package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.TransactionDayDto;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/observability")
public class ObservabilityController {

    @Autowired
    private ExtractService extractService;

    @Operation(summary = "The total number of transactions for the day")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return quantity of transactions"),
            @ApiResponse(responseCode = "400", description = "Data invalid", content = @Content),
            @ApiResponse(responseCode = "401", description = "User invalid", content = @Content)
    })
    @GetMapping(value = "/quantity-transactions-day", produces = "application/json")
    public ResponseEntity<TransactionDayDto> quantityTransactionDay() {

        long countExtract = extractService.getCountExtractDate(LocalDate.now());
        TransactionDayDto transactionDayDto = new TransactionDayDto(LocalDate.now(), countExtract);

        return new ResponseEntity<>(transactionDayDto, HttpStatus.OK);
    }

    @Operation(summary = "Top 10 transactions of the day")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return top 10 transactions of day"),
            @ApiResponse(responseCode = "400", description = "Data invalid", content = @Content),
            @ApiResponse(responseCode = "401", description = "User invalid", content = @Content)
    })
    @GetMapping(value = "/top-ten-transactions", produces = "application/json")
    public ResponseEntity<List<Extract>> top10TransactionsDay() {

        List<Extract> extracts = extractService.findTop10TransactionDay(LocalDate.now());

        return new ResponseEntity<>(extracts, HttpStatus.OK);
    }

}