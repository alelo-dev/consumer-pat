package br.com.alelo.consumer.consumerpat.resource.v1;

import br.com.alelo.consumer.consumerpat.dto.v1.request.ConsumerCreateV1RequestDto;
import br.com.alelo.consumer.consumerpat.dto.v1.request.ConsumerPaginatedV1RequestDto;
import br.com.alelo.consumer.consumerpat.dto.v1.request.ConsumerUpdateV1RequestDto;
import br.com.alelo.consumer.consumerpat.dto.v1.response.ConsumerV1ResponseDto;
import br.com.alelo.consumer.consumerpat.dto.v1.response.PaginatedResponseDto;
import br.com.alelo.consumer.consumerpat.exception.BadRequestException;
import br.com.alelo.consumer.consumerpat.usecase.ConsumerCreateUseCase;
import br.com.alelo.consumer.consumerpat.usecase.ConsumerFindUseCase;
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
                .path("{consumerCode}")
                .buildAndExpand(consumerCode)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{consumerCode}")
    public void update(@RequestParam("consumerCode") UUID consumerCode, @RequestBody ConsumerUpdateV1RequestDto request) {

    }
}
