package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.dto.ListPagedDto;
import br.com.alelo.consumer.consumerpat.entity.AbstractEntity;
import br.com.alelo.consumer.consumerpat.exception.RestNotFoundException;
import br.com.alelo.consumer.consumerpat.mapper.Mapper;
import br.com.alelo.consumer.consumerpat.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @param <M> - Model
 * @param <D> - DTO
 * @param <S> - Service
 * @param <P> - Mapper
 */
public abstract class CrudController<M extends AbstractEntity, D, S extends CrudService<M, ?>, P extends Mapper<M, D>> {

    private static final int OFFSET_DEFAULT = 0;
    private static final String OFFSET_DEFAULT_STRING = "0";
    private static final int PAGE_SIZE_MAX = 30;
    private static final String PAGE_SIZE_MAX_STRING = "30";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ListPagedDto<D> listAll(
            final @RequestParam(defaultValue = OFFSET_DEFAULT_STRING) int offset,
            final @RequestParam(defaultValue = PAGE_SIZE_MAX_STRING) int pageSize
    ) {
        final Pageable pageable = PageRequest.of(
                Math.min(Math.abs(offset), OFFSET_DEFAULT),
                Math.min(Math.abs(pageSize), PAGE_SIZE_MAX)
        );
        final Page<M> pagedCustomer = getService().listAll(pageable);
        final List<D> customersDto = pagedCustomer.stream().map(getMapper()::mapModelToDto).collect(Collectors.toList());
        return new ListPagedDto<>(pagedCustomer.getTotalElements(), customersDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public D findById(final @PathVariable Long id) {
        return withExistingModel(id, getMapper()::mapModelToDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public D insert(final @RequestBody D dtoBody) {
        final M modelRequest = getMapper().mapDtoToModel(dtoBody);
        final M modelResponse = getService().save(modelRequest);
        return getMapper().mapModelToDto(modelResponse);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public D update(final @PathVariable Long id, final @RequestBody D dtoBody) {
        return withExistingModel(id, currentModel -> {
            final M dtoResponse = getService().save(getMapper().updateModelTarget(currentModel, dtoBody));
            return getMapper().mapModelToDto(dtoResponse);
        });
    }

    protected  <T> T withExistingModel(final Long id, Function<M, T> function) {
        final Optional<M> maybeCurrentModel = getService().findById(id);
        return maybeCurrentModel.map(function).orElseThrow(RestNotFoundException::new);
    }

    protected abstract S getService();

    protected abstract P getMapper();
}
