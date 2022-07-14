package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exceptions.NotFoundException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author renanravelli
 */

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

  private final ConsumerRepository repository;

  @Override
  public Consumer cadastrar(Consumer consumer) {
    return repository.save(consumer);
  }

  @Override
  @Transactional
  public Consumer atualizar(Integer id, Consumer consumer) {

    Consumer consumerOld = repository.findById(id)
        .map(c -> c.update(consumer))
        .orElseThrow(NotFoundException.supplier("Consumer n\u00E3o encontrado pelo id."));

    return repository.save(consumerOld);
  }

  @Override
  public Consumer buscar(Integer id) {
    return repository.findById(id)
        .orElseThrow(NotFoundException.supplier("Consumer n\u00E3o encontrado."));
  }

  @Override
  public Page<Consumer> pesquisar(int pagina, int tamanho) {
    return repository.findAll(PageRequest.of(pagina, tamanho));
  }

}
