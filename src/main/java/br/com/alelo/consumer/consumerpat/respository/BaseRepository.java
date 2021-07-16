package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.BaseEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseRepository<T extends BaseEntity> extends PagingAndSortingRepository<T, Long> {

}
