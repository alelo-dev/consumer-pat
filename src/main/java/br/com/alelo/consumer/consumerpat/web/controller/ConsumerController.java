package br.com.alelo.consumer.consumerpat.web.controller;

import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.ConsumerFilterVO;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.ConsumerVO;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.NewConsumerFormVO;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.UpdateConsumerFormVO;
import br.com.alelo.consumer.consumerpat.web.vo.extract.ExtractVO;
import br.com.alelo.consumer.consumerpat.web.vo.extract.NewExtractFormVO;
import br.com.alelo.consumer.consumerpat.web.vo.pagination.PageVO;
import br.com.alelo.consumer.consumerpat.web.vo.pagination.QueryResultVO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/consumer")
public class ConsumerController {

    private final ConsumerService consumerService;

    private final ExtractService extractService;

    @GetMapping("{consumerId}")
    public ConsumerVO findById(@PathVariable Long consumerId) {
        return ConsumerVO.from(consumerService.findById(consumerId));
    }

    @GetMapping
    public QueryResultVO<ConsumerVO> findAll(ConsumerFilterVO filters) {
        Page<Consumer> result = consumerService.findAll(filters, buildPageable(filters));
        return buildResponse(result);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumerVO save(@Valid @RequestBody NewConsumerFormVO form) {
        return ConsumerVO.from(consumerService.save(form));
    }

    @PutMapping("{consumerId}")
    public ConsumerVO update(@PathVariable Long consumerId, @Valid @RequestBody UpdateConsumerFormVO form) {
        return ConsumerVO.from(consumerService.update(consumerId, form));
    }

    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.CREATED)
    public ExtractVO buy(@Valid @RequestBody NewExtractFormVO form) {
        return ExtractVO.from(extractService.save(form));
    }

    private Pageable buildPageable(ConsumerFilterVO filters) {
        return PageRequest.of(
            filters.getPage(),
            filters.getSize(),
            Sort.by(
                Sort.Direction.fromString(filters.getSortDirection().name()),
                filters.getSortField().getValue()
            ));
    }

    private QueryResultVO<ConsumerVO> buildResponse(Page<Consumer> result) {
        return QueryResultVO.<ConsumerVO>builder()
            .records(result.getContent().stream()
                .map(ConsumerVO::from)
                .collect(Collectors.toList()))
            .page(PageVO.builder()
                .hasNext(result.hasNext())
                .hasPrev(result.hasPrevious())
                .page(result.getNumber())
                .totalPages(result.getTotalPages())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .build()
            ).build();
    }

}
