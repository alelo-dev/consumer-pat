package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.service.IExtractService;

@RestController
@RequestMapping("/extracts")
public class ExtractController {

	@Autowired
	IExtractService service;
	
	@PostMapping("/buy")
	public void buy(@RequestBody ExtractDTO extractDTO) {
		service.buy(extractDTO);
	}
}
