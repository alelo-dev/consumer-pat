package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.pagination.view.PagedResponseWrapper;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.util.UtilRequests;
import br.com.alelo.consumer.consumerpat.vo.ConsumerVO;
import br.com.alelo.consumer.consumerpat.vo.params.BuyParamsVO;
import br.com.alelo.consumer.consumerpat.vo.params.CardBalancerParamsVO;
import br.com.alelo.consumer.consumerpat.vo.params.ConsumerParamsVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/consumer", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ConsumerController {

	@Autowired
	ConsumerService consumerService;

	@GetMapping
	@ApiOperation(value = "Pesquisar consumidores", nickname = "findAll")
	public PagedResponseWrapper<ConsumerVO> findAll(@ModelAttribute final ConsumerParamsVO params) {
		log.info(UtilRequests.getBeginMethod(params));
		final PagedResponseWrapper<ConsumerVO> response = this.consumerService.findAll(params);
		log.info(UtilRequests.getEndMethod(response));
		return response;
	}

	@PostMapping
	@ApiOperation(value = "Cadastrar consumidores", nickname = "save")
	public ConsumerVO save(@RequestBody final ConsumerVO vo) {
		log.info(UtilRequests.getBeginMethod(vo));
		final ConsumerVO response = this.consumerService.save(vo);
		log.info(UtilRequests.getEndMethod(response));
		return response;
	}

	// Não deve ser possível alterar o saldo do cartão
	@PutMapping
	@ApiOperation(value = "Editar consumidores", nickname = "update")
	public ConsumerVO update(@RequestBody final ConsumerVO vo) {
		log.info(UtilRequests.getBeginMethod(vo));
		final ConsumerVO response = this.consumerService.update(vo);
		log.info(UtilRequests.getEndMethod(response));
		return response;
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@PutMapping("/cardbalance")
	@ApiOperation(value = "Balanço de cartões", nickname = "cardBalance")
	public void setCardBalance(@RequestBody CardBalancerParamsVO params) {
		this.consumerService.cardBalance(params);
	}

	@PutMapping("/buy")
	@ApiOperation(value = "Compra no cartão", nickname = "buy")
	public void buy(@RequestBody BuyParamsVO params) {
		this.consumerService.buy(params);
	}

}