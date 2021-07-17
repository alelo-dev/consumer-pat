package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.IConsumerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller of consumer
 *
 * @author mcrj
 */
@RestController
@RequestMapping(value = "/consumer", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ConsumerController {

    private final IConsumerService consumerService;

    @ResponseBody
    @ResponseStatus(code = OK)
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully in the process"),
            @ApiResponse(responseCode = "400", description = "Occurred a fail trying to reach the result")
    })
    public Page<Consumer> listAllConsumers(final Pageable pageable) {
        return consumerService.findAll(pageable);
    }

    @ResponseStatus(code = CREATED)
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the consumer with success"),
            @ApiResponse(responseCode = "400", description = "Occurred a fail trying to reach the result")
    })
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
        return ResponseEntity.ok().body(consumerService.save(consumer));
    }

    @ResponseStatus(code = OK)
    @PutMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully in the process"),
            @ApiResponse(responseCode = "204", description = "No content in the process"),
            @ApiResponse(responseCode = "400", description = "Occurred a fail trying to reach the result"),
            @ApiResponse(responseCode = "403", description = "Not possible to continue in the process")
    })
    public ResponseEntity<Consumer> updateConsumer(@PathVariable Long id, @RequestBody Consumer consumer) {
        if(id.equals(consumer.getId())) {
            return ResponseEntity.ok().body(consumerService.update(consumer));
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
