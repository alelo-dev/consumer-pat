package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ConsumerService {
	
	@Autowired
	ConsumerRepository repository;
	
	@Autowired
	ExtractRepository extractRepository;
	
	public List<Consumer> listAllConsumer(){
		return repository.getAllConsumersList();
	}

	public void createConsumer(Consumer consumer) {
		repository.save(consumer);
	}

	public String updateConsumer(Consumer consumer) {
		Optional<Consumer> consumerExist = repository.findById(consumer.getId());
		if(!consumerExist.isEmpty()) {
			if(consumer.getDrugstoreCardBalance() != 0 || consumer.getFoodCardBalance() != 0 
					|| consumer.getFuelCardBalance() != 00) {
				return "Não é possivel modificar saldo";
			}else {
				consumer.setDrugstoreCardBalance(consumerExist.get().getDrugstoreCardBalance());
				consumer.setFoodCardBalance(consumerExist.get().getFoodCardBalance());
				consumer.setFuelCardBalance(consumerExist.get().getFuelCardBalance());
				repository.save(consumer);
				return "Alteração feita com sucesso";
			}
		}else {
			return "Consumidor não existe";
		}
	}


	public String setBalance(Consumer consumer, double value) {
		Optional<Consumer> consumerExist = repository.findById(consumer.getId());
		String Response = "";
		if(!consumerExist.isEmpty()) {
			if(repository.findByDrugstoreNumber(consumer.getDrugstoreCardNumber()) != null) {
				consumer.setDrugstoreCardBalance(consumerExist.get().getDrugstoreCardBalance()+value);
				Response = "Saldo de Farmacia atualizado";
			}
			if(repository.findByFoodCardNumber(consumer.getFoodCardNumber()) != null) {
				consumer.setFoodCardBalance(consumerExist.get().getFoodCardBalance()+value);
				if(Response == "") {
					Response = "Saldo de alimentação atualizado";
				}else {
					Response += ", Alimentação atualizado";
				}
			}
			if(repository.findByFuelCardNumber(consumer.getFuelCardNumber()) != null) {
				consumer.setFuelCardBalance(consumerExist.get().getFuelCardBalance()+value);
				if(Response == "") {
					Response = "Saldo de gasolina atualizado";
				}else {
					Response += ", gasolina atualizado";
			}
				}
				repository.save(consumer);
				return Response;
			
			}else {
			return "Cartão não existe";
		}
	}
	

	public String buy(Extract extract, Consumer consumer, double value,int establishmentType) {
		Optional<Consumer> consumerExist = repository.findById(consumer.getId());
		Extract extractNew;
		if(!consumerExist.isEmpty()) {
		
			if (establishmentType == 1) {
	            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
	            Double cashback  = (value / 100) * 10;
	            value = value - cashback;
	            consumer = repository.findByFoodCardNumber(consumerExist.get().getFoodCardNumber());
	            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
	            repository.save(consumer);
	            extractNew = new Extract(extract.getEstablishmentName(), extract.getProductDescription(), new Date()
	            		, consumer.getFoodCardNumber(), value);
	
	        }else if(establishmentType == 2) {
	        	// Farmácia
	            consumer = repository.findByDrugstoreNumber(consumerExist.get().getDrugstoreCardNumber());
	            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
	            repository.save(consumer);
	            extractNew = new Extract(extract.getEstablishmentName(), extract.getProductDescription(), new Date()
	            		, consumer.getDrugstoreCardNumber(), value);
	
	        } else {
	            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
	            Double tax  = (value / 100) * 35;
	            value = value + tax;
	            consumer = repository.findByFuelCardNumber(consumerExist.get().getFuelCardNumber());
	            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
	            repository.save(consumer);
	            extractNew = new Extract(extract.getEstablishmentName(), extract.getProductDescription(), new Date()
	            		, consumer.getFuelCardNumber(), value);
	        }
			extractRepository.save(extractNew);
	        return "Compra realizada com sucesso!";
			
		}else {
			return "Cartão invalido";
		}
		
	}
}