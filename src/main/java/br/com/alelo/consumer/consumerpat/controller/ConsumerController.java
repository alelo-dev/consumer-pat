package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerRepository repository;

	/* Deve listar todos os clientes (cerca de 500) */
	@GetMapping(value = "/list")
	public List<Consumer> listAll() {
		return repository.getAllConsumersList();
	}

	/* Cadastrar novos clientes */
	@PostMapping(value = "/create", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<String> create(@RequestBody Consumer consumer) {
		String retorno = validar(consumer);
		if (retorno.length() > 0)
			return ResponseEntity.badRequest().body(retorno);
		repository.save(consumer);
		return ResponseEntity.ok().body("Cadastro realizado com sucesso.");
	}

	private String validar(Consumer consumer) {
		String mensagem = "";
		if (consumer.getName() == null || consumer.getName().isBlank())
			mensagem += "O nome deve ser informado.";
		if (consumer.getAddress() == null)
			mensagem += "O endereço deve ser informado.";
		return mensagem;
	}

	// Não deve ser possível alterar o saldo do cartão
	@PutMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Consumer> update(@RequestBody Consumer consumerNew, @PathVariable Integer id) {
		return repository.findById(id).map(consumer -> {
			if (consumerNew.getBirthDate() != null)
				consumer.setBirthDate(consumerNew.getBirthDate());
			if (consumerNew.getName() != null && !consumerNew.getName().isEmpty())
				consumer.setName(consumerNew.getName());
			if (consumerNew.getDocumentNumber() > 0)
				consumer.setDocumentNumber(consumerNew.getDocumentNumber());
			if (consumerNew.getContact() != null)
				consumer.setContact(consumerNew.getContact());
			if (consumerNew.getAddress() != null)
				consumer.setAddress(consumerNew.getAddress());
			Consumer salvo = repository.save(consumer);
			return ResponseEntity.ok().body(salvo);
		}).orElse(ResponseEntity.notFound().build());
	}
}
