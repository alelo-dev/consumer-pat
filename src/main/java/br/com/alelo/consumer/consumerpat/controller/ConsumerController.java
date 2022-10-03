package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerConverter;
import br.com.alelo.consumer.consumerpat.controller.dto.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.ResponseConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.validator.ConsumerValidator;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerService consumerService;

	@Autowired
	ExtractRepository extractRepository;

	@GetMapping()
	public Page<ResponseConsumerDTO> listAllConsumers(
			@PageableDefault(size = 100, direction = Sort.Direction.ASC) Pageable pageable) {

		return consumerService.getPageConsumer(pageable);
	}

	@PostMapping
	public ResponseEntity<String> createConsumer(@RequestBody CreateConsumerDTO createConsumer) {
		final String validated = ConsumerValidator.validate(createConsumer);
		if (Objects.isNull(validated)) {
			consumerService.createConsumer(ConsumerConverter.toEntity(createConsumer));
			return ResponseEntity.ok().build();
		} else {
			log.error("Falha validação");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validated);
		}
	}

	@PutMapping(value = "/{id}")
	public void updateConsumer(@PathVariable("id") Integer id, @RequestBody UpdateConsumerDTO updateConsumerDTO) {

		consumerService.updateConsumer(id, updateConsumerDTO);
	}
}
