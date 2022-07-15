package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Cartao;
import br.com.alelo.consumer.consumerpat.entity.CartaoTipo;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author renanravelli
 */
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

  Optional<Cartao> findByNumber(Integer number);

  Optional<Cartao> findByConsumerIdAndNumber(Integer consumerId, Integer number);

  Optional<Cartao> findByNumberAndTipo(Integer number, CartaoTipo tipo);

  Page<Cartao> findAllByConsumerId(Integer consumerId, Pageable pageable);

}
