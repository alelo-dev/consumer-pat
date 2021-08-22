package br.com.alelo.consumerpat.core.usecase.impl;

import br.com.alelo.consumerpat.core.dataprovider.dao.ConsumerDao;
import br.com.alelo.consumerpat.core.dataprovider.entity.ConsumerEntity;
import br.com.alelo.consumerpat.core.dto.v1.request.ConsumerPaginatedV1RequestDto;
import br.com.alelo.consumerpat.core.dto.v1.response.ConsumerV1ResponseDto;
import br.com.alelo.consumerpat.core.dto.v1.response.PaginatedResponseDto;
import br.com.alelo.consumerpat.core.mapper.response.pagination.PaginatedBaseMapper;
import br.com.alelo.consumerpat.core.mapper.response.ConsumerV1ResponseMapperPaginated;
import br.com.alelo.consumerpat.core.mapper.response.pagination.PaginatedResponseMapper;
import br.com.alelo.consumerpat.core.usecase.ConsumerFindUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ConsumerFindAllUseCaseImpl implements ConsumerFindUseCase {

    @Autowired
    private ConsumerDao consumerDao;

    @Override
    public PaginatedResponseDto<ConsumerV1ResponseDto> findAll(ConsumerPaginatedV1RequestDto request) {
        Page<ConsumerEntity> all = this.consumerDao.findAll(PageRequest.of(request.getPage(), request.getSize()));

        PaginatedBaseMapper<ConsumerEntity, ConsumerV1ResponseDto> consumerV1ResponseMapper = new ConsumerV1ResponseMapperPaginated();
        PaginatedResponseMapper<ConsumerEntity, ConsumerV1ResponseDto> paginatedResponseMapper = new PaginatedResponseMapper<>();

        return paginatedResponseMapper.convert(all, consumerV1ResponseMapper);
    }
}
