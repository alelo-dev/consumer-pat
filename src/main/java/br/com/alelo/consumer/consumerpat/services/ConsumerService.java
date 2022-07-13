package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.models.Compra;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;

public interface ConsumerService {

  Consumer cadastrar(Consumer consumer);

  Consumer atualizar(Integer id, Consumer consumer);

  Consumer creditar(int cardNumber, BigDecimal value);

  void compra(int cardNumber, Compra compra);

  Page<Consumer> pesquisar(int pagina, int tamanho);

}
