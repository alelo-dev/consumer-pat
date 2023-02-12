package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.PageData;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/extract")
public class ExtractController {

    @Autowired
    private ExtractService extractService;

    @Operation(summary = "Return a paged list of extract")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a paged list of consumers"),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
            @ApiResponse(responseCode = "401", description = "User invalid", content = @Content)
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<PageData<Extract>> listAllExtract(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "50") int size) {
        log.info("listAllExtract - Getting paged extracts");

        Pageable paging = PageRequest.of(page, size);
        PageData<Extract> pageExtract = extractService.getAllExtractListByPage(paging);

        log.info("listAllExtract - Returning paged extracts");

        return new ResponseEntity<>(pageExtract, HttpStatus.OK);
    }


}