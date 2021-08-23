package br.com.alelo.consumerpat.core.usecase.impl;

import br.com.alelo.consumerpat.core.dto.v1.request.ConsumerPaginatedV1RequestDto;
import br.com.alelo.consumerpat.core.dto.v1.response.ConsumerV1ResponseDto;
import br.com.alelo.consumerpat.core.dto.v1.response.PaginatedResponseDto;
import br.com.alelo.consumerpat.core.mapper.response.ConsumerV1ResponseMapper;
import br.com.alelo.consumerpat.core.mapper.response.PaginatedResponseMapper;
import br.com.alelo.consumerpat.core.usecase.ConsumerFindUseCase;
import br.com.alelo.consumerpat.dataprovider.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ConsumerFindAllUseCaseImpl implements ConsumerFindUseCase {

    @Autowired
    private ConsumerRepository consumerDao;

    @Override
    public PaginatedResponseDto<ConsumerV1ResponseDto> findAll(ConsumerPaginatedV1RequestDto request) {
        Page<ConsumerV1ResponseDto> all = this.consumerDao.findAll(PageRequest.of(request.getPage(), request.getSize()))
                .map(ConsumerV1ResponseMapper::convert);

        PaginatedResponseMapper<ConsumerV1ResponseDto> paginatedResponseMapper = new PaginatedResponseMapper<>();

        return paginatedResponseMapper.convert(all);
    }
}
