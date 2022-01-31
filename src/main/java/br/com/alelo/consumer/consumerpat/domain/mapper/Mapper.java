package br.com.alelo.consumer.consumerpat.domain.mapper;

import br.com.alelo.consumer.consumerpat.converters.Converter;
import br.com.alelo.consumer.consumerpat.domain.dto.BaseDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.EntidadeBase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper<E extends EntidadeBase<?>, D extends BaseDTO> {

    @Autowired
    protected Converter converter;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    public Mapper(Class<E> entityClass, Class<D> dtoClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }

    public D entityToDTO(E entity) {
        return converter.converter(entity, dtoClass);
    }

    public E dtoToEntity(D dto) {
        return converter.converter(dto, entityClass);
    }

    public List<D> listEntityToDTO(List<E> entityList) {
        return entityList.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<E> listDTOToEntity(List<D> dtoList) {
        return dtoList.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

}
