package br.com.alelo.consumer.consumerpat.service;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.model.EstablishementCards;
import br.com.alelo.consumer.consumerpat.model.FuelCards;
import br.com.alelo.consumer.consumerpat.model.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.model.entity.Cards;

@Service
public interface CardsService {
	
	public void buy (EstablishementCards cards,BuyDTO dto );
	
	public void buy (FuelCards cards,BuyDTO dto);
	
	public void buy (Cards cards,BuyDTO dto);

}
