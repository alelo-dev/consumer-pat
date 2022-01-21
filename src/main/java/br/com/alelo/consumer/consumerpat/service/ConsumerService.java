package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.CreditCardDto;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;

@Service
public interface ConsumerService {

	public List<ConsumerDTO> getAllConsumersList();

	public void createConsumer(ConsumerDTO consumer) throws BusinessException;

	public void updateConsumer(ConsumerDTO consumer) throws BusinessException;

	public void setBalance(CreditCardDto dto) throws BusinessException;	
	
	public void buy(BuyDTO dto) throws BusinessException;	
	
	
	
}
