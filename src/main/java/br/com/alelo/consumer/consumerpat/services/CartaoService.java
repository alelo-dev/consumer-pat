package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Cartao;
import br.com.alelo.consumer.consumerpat.models.Compra;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;

/**
 * @author renanravelli
 */
public interface CartaoService {

  Cartao cadastrar(Integer consumerId, Cartao cartao);

  Cartao atualizar(Integer consumerId, Integer cartaoNumber, Cartao cartao);

  void compra(Integer consumerId, Integer cartaoNumber, Compra compra);

  Cartao creditar(Integer consumerId, int cardNumber, BigDecimal value);

  Page<Cartao> pesquisar(Integer consumerId, int pagina, int tamanho);

}
