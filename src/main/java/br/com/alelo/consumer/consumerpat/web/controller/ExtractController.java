package br.com.alelo.consumer.consumerpat.web.controller;

import br.com.alelo.consumer.consumerpat.model.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import br.com.alelo.consumer.consumerpat.web.vo.extract.ExtractFilterVO;
import br.com.alelo.consumer.consumerpat.web.vo.extract.ExtractVO;
import br.com.alelo.consumer.consumerpat.web.vo.pagination.PageVO;
import br.com.alelo.consumer.consumerpat.web.vo.pagination.QueryResultVO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/extract")
public class ExtractController {

    private final ExtractService extractService;

    @GetMapping
    public QueryResultVO<ExtractVO> findAll(ExtractFilterVO filters) {
        Page<Extract> result = extractService.findAll(filters, buildPageable(filters));
        return buildResponse(result);
    }

    private Pageable buildPageable(ExtractFilterVO filters) {
        return PageRequest.of(
            filters.getPage(),
            filters.getSize(),
            Sort.by(
                Sort.Direction.fromString(filters.getSortDirection().name()),
                filters.getSortField().getValue()
            ));
    }

    private QueryResultVO<ExtractVO> buildResponse(Page<Extract> result) {
        return QueryResultVO.<ExtractVO>builder()
            .records(result.getContent().stream()
                .map(ExtractVO::from)
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
