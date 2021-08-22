package br.com.alelo.consumerpat.entrypoint.v1;

import br.com.alelo.consumerpat.core.v1.request.ConsumerCreateV1RequestDto;
import br.com.alelo.consumerpat.core.v1.request.ConsumerPaginatedV1RequestDto;
import br.com.alelo.consumerpat.core.v1.request.ConsumerUpdateV1RequestDto;
import br.com.alelo.consumerpat.core.v1.response.ConsumerV1ResponseDto;
import br.com.alelo.consumerpat.core.v1.response.PaginatedResponseDto;
import br.com.alelo.consumerpat.core.exception.BadRequestException;
import br.com.alelo.consumerpat.core.exception.ConsumerNotFound;
import br.com.alelo.consumerpat.core.usecase.ConsumerCreateUseCase;
import br.com.alelo.consumerpat.core.usecase.ConsumerFindUseCase;
import br.com.alelo.consumerpat.core.usecase.ConsumerUpdateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/consumers")
public class ConsumerResource {

    @Autowired
    private ConsumerCreateUseCase consumerCreateUseCase;

    @Autowired
    private ConsumerFindUseCase consumerFindUseCase;

    @Autowired
    private ConsumerUpdateUseCase consumerUpdateUseCase;

    @GetMapping
    public ResponseEntity<PaginatedResponseDto<ConsumerV1ResponseDto>> findAll(@ModelAttribute ConsumerPaginatedV1RequestDto requestDto) {
        PaginatedResponseDto<ConsumerV1ResponseDto> responseDto = this.consumerFindUseCase.findAll(requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ConsumerCreateV1RequestDto request) throws BadRequestException {
        String consumerCode = this.consumerCreateUseCase.create(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{consumerCode}")
                .buildAndExpand(consumerCode)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{consumerCode}")
    public ResponseEntity<Void> update(@PathVariable("consumerCode") UUID consumerCode, @RequestBody ConsumerUpdateV1RequestDto request) {
        try {
            this.consumerUpdateUseCase.update(consumerCode.toString(), request);

            return ResponseEntity.noContent().build();
        } catch (ConsumerNotFound ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
