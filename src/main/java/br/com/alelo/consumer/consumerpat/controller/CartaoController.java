package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.dto.CartaoCreditarJsonRequest;
import br.com.alelo.consumer.consumerpat.controller.dto.CartaoFiltroJsonRequest;
import br.com.alelo.consumer.consumerpat.controller.dto.CartaoJsonRequest;
import br.com.alelo.consumer.consumerpat.controller.dto.CartaoJsonResponse;
import br.com.alelo.consumer.consumerpat.controller.dto.CompraJsonRequest;
import br.com.alelo.consumer.consumerpat.controller.dto.PageJsonResponse;
import br.com.alelo.consumer.consumerpat.controller.mapper.CartaoMapper;
import br.com.alelo.consumer.consumerpat.controller.mapper.CompraMapper;
import br.com.alelo.consumer.consumerpat.entity.Cartao;
import br.com.alelo.consumer.consumerpat.services.CartaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/consumers/{consumerId}/cartoes", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Consumer", description = "Recurso respons\u00E1vel pelos endpoints relacionados a Consumers")
public class CartaoController {

  private final CartaoService cartaoService;

  @ApiResponses({
      @ApiResponse(code = 400, message = "Erro na valida\u00E7\u00E3o das informa\u00E7\u00F5es"),
      @ApiResponse(code = 404, message = "Consumer n\u00E3o encontrado")
  })
  @ApiOperation(value = "Endpoint respons\u00E1vel por cadastrar um cart\u00E3o para um consumer.")
  @PostMapping
  public ResponseEntity<CartaoJsonResponse> cadastrar(
      @PathVariable("consumerId") Integer consumerId, @RequestBody CartaoJsonRequest request) {
    Cartao cartao = cartaoService.cadastrar(consumerId, CartaoMapper.map(request));
    return ResponseEntity.ok(CartaoMapper.map(cartao));
  }

  @ApiResponses({
      @ApiResponse(code = 400, message = "Erro na valida\u00E7\u00E3o das informa\u00E7\u00F5es"),
      @ApiResponse(code = 404, message = "Cart\u00E3o n\u00E3o encontrado.")
  })
  @ApiOperation(value = "Endpoint respons\u00E1vel por realizar compra com um cart\u00E3o.")
  @PatchMapping(path = "/{cardNumber}/compras", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void buy(@PathVariable("consumerId") Integer consumerId,
      @PathVariable("cardNumber") Integer cardNumber,
      @RequestBody CompraJsonRequest request) {
    cartaoService.compra(consumerId, cardNumber, CompraMapper.map(request));
  }

  @ApiResponses({
      @ApiResponse(code = 400, message = "N\u00FAmero do cart\u00E3o j\u00E1 registrado."),
      @ApiResponse(code = 404, message = "Cart\u00E3o n\u00E3o encontrado.")
  })
  @ApiOperation(value = "Endpoint respons\u00E1vel por realizar atualiza\u00E7\u00E3o do cartão.")
  @PutMapping(path = "/{cardNumber}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CartaoJsonResponse> atualizar(
      @PathVariable("consumerId") Integer consumerId,
      @PathVariable("cardNumber") Integer cardNumber,
      @RequestBody CartaoJsonRequest request) {
    Cartao cartao = cartaoService.atualizar(consumerId, cardNumber, CartaoMapper.map(request));
    return ResponseEntity.ok(CartaoMapper.map(cartao));
  }

  /*
   * Deve creditar(adicionar) um valor(value) em um no cartão.
   * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
   * para isso deve usar o número do cartão(cardNumber) fornecido.
   */
  @PatchMapping(path = "/{cardNumber}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  @ApiResponses({
      @ApiResponse(code = 400, message = "Erro na valida\u00E7\u00E3o das informa\u00E7\u00F5es"),
      @ApiResponse(code = 404, message = "Consumer n\u00E3o encontrado.")
  })
  @ApiOperation(value = "Endpoint respons\u00E1vel por creditar valor em um cart\u00E3o.")
  public ResponseEntity<?> setBalance(
      @PathVariable("consumerId") Integer consumerId,
      @PathVariable("cardNumber") Integer cardNumber,
      @RequestBody CartaoCreditarJsonRequest request) {
    cartaoService.creditar(consumerId, cardNumber, request.getValue());
    return ResponseEntity.noContent().build();
  }

  /* Deve listar todos os cartões do cliente */
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  @ApiOperation(value = "Endpoint respons\u00E1vel por listar os cart\u00F5es do cliente.", response = PageJsonResponse.class)
  public ResponseEntity<PageJsonResponse<CartaoJsonResponse>> listAllCartoes(
      @PathVariable("consumerId") Integer consumerId, CartaoFiltroJsonRequest filtro) {
    final var paginacao = cartaoService.pesquisar(consumerId, filtro.getPagina(),
        filtro.getTamanho());
    return ResponseEntity.ok(PageJsonResponse.from(paginacao, CartaoMapper::map));
  }

}
