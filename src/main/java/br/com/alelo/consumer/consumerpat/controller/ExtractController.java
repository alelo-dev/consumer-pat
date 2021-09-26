package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.service.ExtractService;
import br.com.alelo.consumer.consumerpat.service.dto.request.ExtractRequest;
import br.com.alelo.consumer.consumerpat.service.dto.response.ExtractResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class ExtractController {

	@Autowired
	private ExtractService extractService;

	@PostMapping("/buy")
	public ResponseEntity<ExtractResponse> buy(@RequestBody ExtractRequest request) {
		return ResponseEntity.ok(extractService.buy(request));
	}
}
