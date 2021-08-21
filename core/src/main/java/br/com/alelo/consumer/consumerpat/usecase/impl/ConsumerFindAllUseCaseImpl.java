package br.com.alelo.consumer.consumerpat.usecase.impl;

import br.com.alelo.consumer.consumerpat.dataprovider.dao.ConsumerDao;
import br.com.alelo.consumer.consumerpat.dataprovider.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.dto.v1.request.ConsumerPaginatedV1RequestDto;
import br.com.alelo.consumer.consumerpat.dto.v1.response.ConsumerV1ResponseDto;
import br.com.alelo.consumer.consumerpat.dto.v1.response.PaginatedResponseDto;
import br.com.alelo.consumer.consumerpat.mapper.BaseMapper;
import br.com.alelo.consumer.consumerpat.mapper.response.ConsumerV1ResponseMapper;
import br.com.alelo.consumer.consumerpat.mapper.response.PaginatedResponseMapper;
import br.com.alelo.consumer.consumerpat.usecase.ConsumerFindUseCase;
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

        BaseMapper<ConsumerEntity, ConsumerV1ResponseDto> consumerV1ResponseMapper = new ConsumerV1ResponseMapper();
        PaginatedResponseMapper<ConsumerEntity, ConsumerV1ResponseDto> paginatedResponseMapper = new PaginatedResponseMapper<>();

        return paginatedResponseMapper.convert(all, consumerV1ResponseMapper);
    }
}
