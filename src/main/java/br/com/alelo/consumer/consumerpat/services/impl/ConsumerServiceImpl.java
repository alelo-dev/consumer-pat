package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exceptions.NotFoundException;
import br.com.alelo.consumer.consumerpat.models.Compra;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import br.com.alelo.consumer.consumerpat.services.impl.calculadora.Calculadora;
import br.com.alelo.consumer.consumerpat.services.impl.calculadora.CalculadoraFactory;
import br.com.alelo.consumer.consumerpat.services.impl.validacao.DrugstoreValidacao;
import br.com.alelo.consumer.consumerpat.services.impl.validacao.FoodValidacao;
import br.com.alelo.consumer.consumerpat.services.impl.validacao.FuelValidacao;
import br.com.alelo.consumer.consumerpat.services.impl.validacao.NumeroCartaoValidacao;
import br.com.alelo.consumer.consumerpat.services.impl.validacao.Validacao;
import java.math.BigDecimal;
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
  private final ExtractRepository extractRepository;

  @Override
  public Consumer cadastrar(Consumer consumer) {

    // Validações a serem executadas
    final var validacoes = getValidacoes();
    validacoes.validar(consumer);

    return repository.save(consumer);
  }

  @Override
  @Transactional
  public Consumer atualizar(Integer id, Consumer consumer) {

    // Validações a serem executadas
    final var validacoes = getValidacoes();
    validacoes.validar(consumer);

    Consumer consumerOld = repository.findById(id)
        .map(c -> c.update(consumer))
        .orElseThrow(NotFoundException.supplier("Consumer n\u00E3o encontrado pelo id."));

    return repository.save(consumerOld);
  }

  @Override
  @Transactional
  public Consumer creditar(int cardNumber, BigDecimal value) {
    return repository.findByCardNumber(cardNumber)
        .map(c -> c.creditar(cardNumber, value))
        .map(repository::save)
        .orElseThrow(NotFoundException.supplier(
            "Consumer n\u00E3o encontrado pelo n\u00FAmero do cart\u00E3o."));
  }

  @Override
  @Transactional
  public void compra(int cardNumber, Compra compra) {

    Calculadora calculadora = CalculadoraFactory.criar(compra);
    BigDecimal valorCalculado = calculadora.calcular(compra.getValue());

    repository.findByCardNumber(cardNumber)
        .map(c -> c.debitar(cardNumber, compra, valorCalculado))
        .map(repository::save)
        .orElseThrow(NotFoundException.supplier(
            "Consumer n\u00E3o encontrado pelo n\u00FAmero do cart\u00E3o."));

    final var extract = new Extract(compra.getEstablishmentName(), compra.getProductDescription(),
        cardNumber, valorCalculado);
    extractRepository.save(extract);
  }

  @Override
  public Page<Consumer> pesquisar(int pagina, int tamanho) {
    return repository.findAll(PageRequest.of(pagina, tamanho));
  }

  private Validacao getValidacoes() {
    final var fuelValidacao = new FuelValidacao(repository);
    final var foodValidacao = new FoodValidacao(fuelValidacao, repository);
    final var drugstoreValidacao = new DrugstoreValidacao(foodValidacao, repository);
    return new NumeroCartaoValidacao(drugstoreValidacao);
  }
}
