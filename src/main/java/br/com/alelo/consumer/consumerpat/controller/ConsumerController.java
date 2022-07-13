package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.dto.CompraJsonRequest;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerCreditarJsonRequest;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerFiltroJsonRequest;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerJsonRequest;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerJsonResponse;
import br.com.alelo.consumer.consumerpat.controller.dto.PageJsonResponse;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
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
@RequestMapping(value = "/consumers", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Consumer", description = "Recurso respons\u00E1vel pelos endpoints relacionados a Consumers")
public class ConsumerController {

  private final ConsumerService consumerService;

  /* Deve listar todos os clientes (cerca de 500) */
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  @ApiOperation(value = "Endpoint respons\u00E1vel por listar os clientes.", response = PageJsonResponse.class)
  public ResponseEntity<PageJsonResponse<ConsumerJsonResponse>> listAllConsumers(
      ConsumerFiltroJsonRequest filtro) {
    final var paginacao = consumerService.pesquisar(filtro.getPagina(), filtro.getTamanho());
    return ResponseEntity.ok(PageJsonResponse.from(paginacao, ConsumerJsonResponse::fromEntity));
  }

  /* Cadastrar novos clientes */
  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses({
      @ApiResponse(code = 201, message = "Consumer cadastrado com sucesso"),
      @ApiResponse(code = 400, message = "Erro na valida\u00E7\u00E3o das informa\u00E7\u00F5es")
  })
  @ApiOperation(value = "Endpoint respons\u00E1vel por cadastrar novos clientes.", response = ConsumerJsonRequest.class)
  public ResponseEntity<ConsumerJsonResponse> createConsumer(
      @RequestBody ConsumerJsonRequest request) {
    Consumer consumerCadastrado = consumerService.cadastrar(request.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ConsumerJsonResponse.fromEntity(consumerCadastrado));
  }

  // Não deve ser possível alterar o saldo do cartão
  @ResponseStatus(code = HttpStatus.OK)
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses({
      @ApiResponse(code = 200, message = "Consumer atualizado com sucesso."),
      @ApiResponse(code = 400, message = "Erro na valida\u00E7\u00E3o das informa\u00E7\u00F5es"),
      @ApiResponse(code = 404, message = "Consumer n\u00E3o encontrado.")
  })
  @ApiOperation(value = "Endpoint respons\u00E1vel por atualizar um cliente.", response = ConsumerJsonRequest.class)
  public ResponseEntity<ConsumerJsonResponse> updateConsumer(Integer id,
      @RequestBody ConsumerJsonRequest request) {
    final var consumerAtualizado = consumerService.atualizar(id, request.toEntity());
    return ResponseEntity.ok(ConsumerJsonResponse.fromEntity(consumerAtualizado));
  }

  /*
   * Deve creditar(adicionar) um valor(value) em um no cartão.
   * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
   * para isso deve usar o número do cartão(cardNumber) fornecido.
   */
  @PatchMapping(path = "/{cardNumber}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  @ApiResponses({
      @ApiResponse(code = 400, message = "Erro na valida\u00E7\u00E3o das informa\u00E7\u00F5es"),
      @ApiResponse(code = 404, message = "Consumer n\u00E3o encontrado.")
  })
  @ApiOperation(value = "Endpoint respons\u00E1vel por creditar valor em um cart\u00E3o.")
  public ResponseEntity<?> setBalance(@PathVariable("cardNumber") int cardNumber,
      @RequestBody ConsumerCreditarJsonRequest request) {
    consumerService.creditar(cardNumber, request.getValue());
    return ResponseEntity.noContent().build();
  }

  @ApiResponses({
      @ApiResponse(code = 400, message = "Erro na valida\u00E7\u00E3o das informa\u00E7\u00F5es"),
      @ApiResponse(code = 404, message = "Consumer n\u00E3o encontrado.")
  })
  @ApiOperation(value = "Endpoint respons\u00E1vel por realizar compra com um cart\u00E3o.")
  @PatchMapping(path = "/{cardNumber}/compras", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void buy(@PathVariable("cardNumber") Integer cardNumber,
      @RequestBody CompraJsonRequest request) {
    consumerService.compra(cardNumber, request.toModel());
  }

}
