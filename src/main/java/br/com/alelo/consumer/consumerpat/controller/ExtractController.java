package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ExtractService;

@Controller
@RequestMapping("/extract")
public class ExtractController {
	@Autowired
    ExtractService extractService;
	
	  /* Deve listar todos os extratos*/
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/extractList", method = RequestMethod.GET)
    public List<Extract> listExtracts() {
    	return extractService.listAllExtracts();
    }
    
	@ResponseBody
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public ResponseEntity<Object> buy(@RequestParam int establishmentType, @RequestBody Extract extract) {
		try {
			String response = extractService.buy(establishmentType, extract);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Erro ao realizar requisicao", HttpStatus.BAD_REQUEST);
		}
	}
}
