package br.com.alelo.consumer.consumerpat.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.enums.Type;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {
	@Autowired
	EstablishmentRepository establishmentRepository;

	@Autowired
	ExtractRepository extractRepository;

	@Autowired
	CardRepository cardRepository;

	@GetMapping(value = "/list")
	public List<Establishment> listAll() {
		return establishmentRepository.findAll();
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody Establishment est) {
		String retorno = validar(est);
		if (retorno.length() > 0)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(retorno);
		establishmentRepository.save(est);
		return ResponseEntity.ok().body("Cadastro realizado com sucesso.");

	}

	private String validar(Establishment est) {
		String mensagem = "";
		if (est.getName() == null || est.getName().isBlank())
			mensagem += "O nome do estabelecimento deve ser informado;";
		if (est.getType() == null)
			mensagem += "O tipo do estabelecimento deve ser informado;";
		return mensagem;
	}

	@PutMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Establishment> update(@RequestBody Establishment establishmentNew, @PathVariable Integer id) {
		return establishmentRepository.findById(id).map(establishment -> {
			if (establishmentNew.getName() != null && !establishmentNew.getName().isEmpty())
				establishment.setName(establishmentNew.getName());
			Establishment salvo = establishmentRepository.save(establishment);
			return ResponseEntity.ok().body(salvo);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		establishmentRepository.deleteById(id);
	}

	@GetMapping("/{id}")
	public Optional<Establishment> find(@PathVariable Integer id) {
		return establishmentRepository.findById(id);
	}

	@GetMapping(value = "/buy")
	public ResponseEntity<String> buy(int establishmentType, String establishmentName, int cardNumber,
			String productDescription, double value) {
		Type type = Type.values()[establishmentType];

		Establishment establishment = establishmentRepository.consultarPorTipoENome(establishmentName, type);
		Card card = cardRepository.findByNumber(cardNumber);
		/*
		 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
		 * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
		 * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
		 * cartão e alimentação
		 *
		 * Tipos de estabelcimentos 1 - Alimentação (food) 2 - Farmácia (DrugStore) 3 -
		 * Posto de combustivel (Fuel)
		 */
		if (card == null)
			return ResponseEntity.badRequest().body("Cartão não encontrado.");
		if (establishment == null)
			return ResponseEntity.badRequest().body("Estabelecimento não encontrado.");
		if (card != null && establishment != null && establishment.getType() != card.getType()) {
			return ResponseEntity.badRequest()
					.body("Não é possível fazer compra com tipo diferente do tipo do estabelecimento.");
		}
		double valueCalculate = calculateValue(establishment.getType(), value);
		if (valueCalculate > card.getBalance())
			return ResponseEntity.badRequest().body("Compra não aprovada.");

		card.setBalance(card.getBalance() - valueCalculate);
		cardRepository.save(card);

		Extract extract = new Extract(productDescription, Calendar.getInstance().getTime(), card, establishment, value);
		extractRepository.save(extract);
		return ResponseEntity.ok().body("Compra realizada com sucesso.");
	}

	private double calculateValue(Type type, double value) {
		if (type == Type.FOOD) {
			// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			Double cashback = (value / 100) * 10;
			value = value - cashback;
			return value;
		} else if (type == Type.FUEL) {
			// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			Double tax = (value / 100) * 35;
			value = value + tax;
		}

		return value;
	}
}
