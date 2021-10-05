package br.com.alelo.consumer.consumerpat.domain.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.domain.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.DepositDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ProductDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.WrappedPurchaseDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.CardType;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.entity.Establishment;
import br.com.alelo.consumer.consumerpat.domain.entity.EstablishmentType;
import br.com.alelo.consumer.consumerpat.domain.entity.Product;
import br.com.alelo.consumer.consumerpat.domain.exception.EntityInUseException;
import br.com.alelo.consumer.consumerpat.domain.exception.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exception.ExceededException;
import br.com.alelo.consumer.consumerpat.domain.exception.NonNullPropertyException;
import br.com.alelo.consumer.consumerpat.domain.respository.ConsumerRepository;

@Service
public class ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
	private CardServiceUtils cardUtils;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ExtractService extractService;
	
	@Autowired
	private EstablishmentService establishmentService;
	
	public List<Consumer> findAll(){
		return consumerRepository.findAll();
	}
	
	public Consumer findById(Long id) {
		Consumer consumer = consumerRepository
				.findById(id)
				.orElseThrow(() -> 
					new EntityNotFoundException(
							String.format("Consumidor de código %d não existe na base de dados!", id)));
		
		return consumer;
	}
	
	public Consumer save(Consumer consumer) {
		try{
			return consumerRepository.save(consumer);
		}
		catch(DataIntegrityViolationException e) {
			throw new NonNullPropertyException(String.format("Verifique os dados inseridos no objeto."));
		}
	}
	
	public void delete(Long id) {
		try {
			consumerRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Consumidor de código %d não pode ser removido, pois está em uso.", id));
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Consumidor de código %d não existe na base de dados!", id));
		}
	}

	public Consumer generateConsumerCard(Long id, CardDTO cardDto) {
		String number = cardUtils.generateCardNumber();
		Consumer consumer = findById(id);
		
		Card card = Card.builder()
						.balance(cardDto.getBalance())
						.number(number)
						.type(cardDto.getType())
						.build();
		
		consumer.getCards().add(card);
		
		return save(consumer);
	}
	
	public Consumer deposit(Long id, DepositDTO depositDTO) {
		Consumer consumer = findById(id);
		String cardNumber = depositDTO.getCardNumber();
		Card cardManaged = cardUtils.validateConsumerCard(consumer,cardNumber);
		
		BigDecimal newBalance = cardManaged.getBalance().add(depositDTO.getAmount());
		cardManaged.setBalance(newBalance);
		
		return save(consumer);
	}

	// FALTA VALIDAR ESTABELECIMENTO
	// REMOVER PRODUTO COMPRADO DO ESTOQUE

	public Consumer buy(Long id, WrappedPurchaseDTO wrappedDTO) {
		Long establishmentId = wrappedDTO.getEstablishmentId(); 
		List<ProductDTO> listProductDTO = wrappedDTO.getProducts(); 
		String cardNumber = wrappedDTO.getCardNumber();
		
		List<ProductDTO> productsDto = validateProduct(listProductDTO);
		
		Consumer consumer = findById(id);
		Card cardManaged = cardUtils.validateConsumerCard(consumer, cardNumber);
		Establishment establishment = establishmentService.findById(establishmentId);
		EstablishmentType establishmentType = establishment.getType();
		
		BigDecimal totalValue = calculateTotalValue(establishmentType, productsDto); 
		boolean cardTypeEqualsEstablishmentType = compareTypes(cardManaged.getType(), establishmentType); 
		
		if(cardTypeEqualsEstablishmentType) {
			BigDecimal newBalance = cardManaged.getBalance().subtract(totalValue);
			
			if(newBalance.compareTo(BigDecimal.ZERO) >= 1) {
				cardManaged.setBalance(newBalance);
				listProductDTO.stream().forEach(product -> {
					Product productManaged = productService.findById(product.getId());
					int actualQuantity = productManaged.getQuantity();
					int finalQuantity = actualQuantity - product.getQuantity();
					productManaged.setQuantity(finalQuantity);
					productService.save(productManaged);
				});
			} 
			else {
				throw new ExceededException("A compra excede o valor limite de seu cartão!");
			}
		}
		else {
			throw new EntityNotFoundException(
					String.format("O tipo de cartão não pode ser utilizado no estabelecimento do tipo %s", establishmentType.toString()));
		}
		
		String establishmentName = establishment.getName();
		List<String> listProductName = new ArrayList<>();
		productsDto.stream().forEach(product -> listProductName.add(product.getName()));
		
		extractService.generateExtract(cardNumber, totalValue, establishmentName, productsDto, consumer);
		
		return save(consumer);
	}

	private List<ProductDTO> validateProduct(List<ProductDTO> listProductDTO) {
		for(ProductDTO prodDTO : listProductDTO) {
			Product product = productService.findById(prodDTO.getId());
			prodDTO.setValue(product.getValue());
			prodDTO.setName(product.getName());
			if(product.getQuantity().compareTo(prodDTO.getQuantity()) < 0) {
				throw new EntityNotFoundException(
						String.format("Estoque insuficiente! (Quantidade em estoque %d do produto solicitado %s)", product.getQuantity(), product.getName()));
			}
			
		}
		return listProductDTO;
	}


	private boolean compareTypes(CardType cardType, EstablishmentType type) {
		return cardType.ordinal() == type.ordinal();
	}
	
	private BigDecimal calculateTotalValue(EstablishmentType type, List<ProductDTO> dtos) {
		BigDecimal totalValue = BigDecimal.ZERO;
		double multiplier = 1.0;
		
		for(ProductDTO dto : dtos) {
			int quantity = dto.getQuantity();
			BigDecimal totalByProduct = dto.getValue().multiply(new BigDecimal(quantity)); 
			totalValue = totalValue.add(totalByProduct);
		}
		
		if(type == EstablishmentType.FOOD) {
			multiplier -= 0.1;
		}
		if(type == EstablishmentType.FUEL) {
			multiplier += 0.35;
		}
		
		totalValue = totalValue.multiply(new BigDecimal(multiplier));
		
		return totalValue;
	}
}
