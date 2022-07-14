package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.domain.Page;

public interface ConsumerService {

  Consumer cadastrar(Consumer consumer);

  Consumer atualizar(Integer id, Consumer consumer);

  Consumer buscar(Integer id);

  Page<Consumer> pesquisar(int pagina, int tamanho);

}
