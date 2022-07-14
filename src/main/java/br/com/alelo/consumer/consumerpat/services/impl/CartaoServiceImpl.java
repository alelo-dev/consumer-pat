package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.Cartao;
import br.com.alelo.consumer.consumerpat.entity.CartaoTipo;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exceptions.BusinessException;
import br.com.alelo.consumer.consumerpat.exceptions.NotFoundException;
import br.com.alelo.consumer.consumerpat.models.Compra;
import br.com.alelo.consumer.consumerpat.respository.CartaoRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.services.CartaoService;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import br.com.alelo.consumer.consumerpat.services.impl.calculadora.CalculadoraDelegate;
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
public class CartaoServiceImpl implements CartaoService {

  private final CartaoRepository repository;
  private final ConsumerService consumerService;
  private final ExtractRepository extractRepository;

  @Override
  public Cartao cadastrar(Integer consumerId, Cartao cartao) {

    final var consumer = consumerService.buscar(consumerId);

    repository.findByNumber(cartao.getNumber())
        .ifPresent(getThrowNumeroCartaoExiste(cartao));

    cartao.addConsumer(consumer);

    return repository.save(cartao);
  }

  @Override
  @Transactional
  public void compra(Integer consumerId, Integer cartaoNumber, Compra compra) {
    BigDecimal valorCalculado = CalculadoraDelegate.delegate(compra).calcular(compra.getValue());
    CartaoTipo cartaoTipo = CartaoTipo.getByEstablishmentType(compra.getEstablishmentType());

    repository.findByNumberAndTipo(cartaoNumber, cartaoTipo)
        .map(c -> c.debitar(valorCalculado))
        .map(repository::save)
        .orElseThrow(NotFoundException.supplier(
            "Cart\u00E3o n\u00E3o encontrado pelo n\u00FAmero e tipo informado."));

    final var extract = new Extract(compra.getEstablishmentName(), compra.getProductDescription(),
        cartaoNumber, valorCalculado);
    extractRepository.save(extract);
  }

  @Override
  public Cartao creditar(Integer consumerId, int cardNumber, BigDecimal value) {
    return repository.findByConsumerIdAndNumber(consumerId, cardNumber)
        .map(c -> c.creditar(value))
        .map(repository::save)
        .orElseThrow(NotFoundException.supplier(
            "Cart\u00E3o n\u00E3o encontrado pelo n\u00FAmero."));
  }

  @Override
  public Page<Cartao> pesquisar(Integer consumerId, int pagina, int tamanho) {
    return repository.findAllByConsumerId(consumerId, PageRequest.of(pagina, tamanho));
  }

  private java.util.function.Consumer<Cartao> getThrowNumeroCartaoExiste(Cartao cartao) {
    return c -> {
      throw new BusinessException(
          String.format("J\u00E1 existe um cart\u00E3o cadastrado com esse n\u00FAmero %s",
              cartao.getNumber()));
    };
  }
}
