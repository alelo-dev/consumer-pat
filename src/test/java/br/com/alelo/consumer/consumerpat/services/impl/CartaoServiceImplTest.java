package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.Cartao;
import br.com.alelo.consumer.consumerpat.entity.CartaoFactoryTest;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exceptions.BusinessException;
import br.com.alelo.consumer.consumerpat.exceptions.NotFoundException;
import br.com.alelo.consumer.consumerpat.models.Compra;
import br.com.alelo.consumer.consumerpat.models.CompraFactoryTest;
import br.com.alelo.consumer.consumerpat.respository.CartaoRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CartaoServiceImplTest {

  @InjectMocks
  private CartaoServiceImpl cartaoService;

  @Mock
  private CartaoRepository cartaoRepository;

  @Mock
  private ConsumerServiceImpl consumerService;

  @Mock
  private ExtractRepository extractRepository;

  @Test
  public void deveCadastrarCartaoComSucesso() {

    Cartao cartao = CartaoFactoryTest.criar();

    Mockito.when(cartaoRepository.findByNumber(Mockito.anyInt())).thenReturn(Optional.empty());
    Mockito.when(cartaoRepository.save(Mockito.any())).thenReturn(cartao);

    Cartao cartaoCadastrado = cartaoService.cadastrar(cartao.getConsumer().getId(), cartao);

    Assertions.assertThat(cartaoCadastrado).isEqualTo(cartao);
  }

  @Test
  public void deveLancarBusinessExceptionAoCadastrarCartao() {

    Cartao cartao = CartaoFactoryTest.criar();

    Mockito.when(cartaoRepository.findByNumber(Mockito.anyInt())).thenReturn(Optional.of(cartao));

    Assertions.assertThatThrownBy(
            () -> cartaoService.cadastrar(cartao.getConsumer().getId(), cartao))
        .isInstanceOf(BusinessException.class);
  }

  @Test
  public void deveRealizarCompra() {

    Cartao cartao = CartaoFactoryTest.criar();
    Compra compra = CompraFactoryTest.criar(2);

    Mockito.when(cartaoRepository.findByNumberAndTipo(Mockito.anyInt(), Mockito.any()))
        .thenReturn(Optional.of(cartao));
    Mockito.when(cartaoRepository.save(Mockito.any())).thenReturn(cartao);

    cartaoService.compra(cartao.getConsumer().getId(), cartao.getId(), compra);

    Assertions.assertThat(cartao.getBalance()).isEqualTo(BigDecimal.ZERO);
  }

  @Test
  public void deveLancarNotFoundAoRealizarCompra() {

    Cartao cartao = CartaoFactoryTest.criar();
    Compra compra = CompraFactoryTest.criar(2);

    Mockito.when(cartaoRepository.findByNumberAndTipo(Mockito.anyInt(), Mockito.any()))
        .thenReturn(Optional.empty());

    Assertions.assertThatThrownBy(() -> cartaoService.compra(cartao.getConsumer().getId(), cartao.getId(), compra))
        .isInstanceOf(NotFoundException.class);
  }

  @Test
  public void deveCreditarCartao() {

    Cartao cartao = CartaoFactoryTest.criar();
    Consumer consumer = cartao.getConsumer();

    Mockito.when(cartaoRepository.findByConsumerIdAndNumber(consumer.getId(), cartao.getNumber()))
        .thenReturn(Optional.of(cartao));
    Mockito.when(cartaoRepository.save(Mockito.any())).thenReturn(cartao);

    Cartao cartaoCreditado = cartaoService.creditar(cartao.getConsumer().getId(),
        cartao.getNumber(), BigDecimal.TEN);

    Assertions.assertThat(cartaoCreditado.getBalance()).isEqualTo(BigDecimal.valueOf(20));
  }

  @Test
  public void deveLancarNotFoundAoCreditarCartao() {

    Cartao cartao = CartaoFactoryTest.criar();
    Consumer consumer = cartao.getConsumer();

    Mockito.when(cartaoRepository.findByConsumerIdAndNumber(consumer.getId(), cartao.getNumber()))
        .thenReturn(Optional.empty());

    Assertions.assertThatThrownBy(() -> cartaoService.creditar(cartao.getConsumer().getId(),
        cartao.getNumber(), BigDecimal.TEN)).isInstanceOf(NotFoundException.class);
  }

  @Test
  public void deveAtualizarCartao() {

    Cartao cartao = CartaoFactoryTest.criar();
    Consumer consumer = cartao.getConsumer();

    Mockito.when(cartaoRepository.findByConsumerIdAndNumber(consumer.getId(), cartao.getNumber()))
        .thenReturn(Optional.of(cartao));
    Mockito.when(cartaoRepository.save(Mockito.any())).thenReturn(cartao);

    Cartao cartaoAtualizado = cartaoService.atualizar(consumer.getId(), cartao.getNumber(),
        cartao);

    Assertions.assertThat(cartaoAtualizado).isEqualTo(cartao);
  }

}
