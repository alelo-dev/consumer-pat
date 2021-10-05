package br.com.alelo.consumer.consumerpat.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.api.mapper.ProductMapper;
import br.com.alelo.consumer.consumerpat.domain.dto.ProductDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.domain.entity.Product;
import br.com.alelo.consumer.consumerpat.domain.exception.EntityInUseException;
import br.com.alelo.consumer.consumerpat.domain.exception.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exception.NonNullPropertyException;
import br.com.alelo.consumer.consumerpat.domain.respository.ExtractRepository;


@Service
public class ExtractService {

	@Autowired
	private ExtractRepository extractRepository;
	
	@Autowired
	private ProductMapper productMapper;
	
	public List<Extract> findAll(){
		return extractRepository.findAll();
	}
	
	public Extract findById(Long id) {
		Extract extract = extractRepository
				.findById(id)
				.orElseThrow(() -> 
					new EntityNotFoundException(
							String.format("Extrato de código %d não cadastrado na base de dados!", id)));
		
		return extract;
	}
	
	public Extract save(Extract extract) {
		try{
			return extractRepository.save(extract);
		}
		catch(DataIntegrityViolationException e) {
			throw new NonNullPropertyException(String.format("Verifique os dados inseridos no objeto."));
		}
	}
	
	
	public void delete(Long id) {
		try {
			extractRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Extrato de código %d não pode ser removido, pois está em uso.", id));
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Extrato de código %d não existe na base de dados!", id));
		}
	}
	
	public void generateExtract(String cardNumber, BigDecimal totalValue, String establishmentName,
			List<ProductDTO> dtos, Consumer consumer) {
		
		 List<Product> products = productMapper.dtoToEntity(dtos);
		 Extract extract = new Extract(cardNumber,establishmentName, totalValue, products, consumer);
		 save(extract);
	}
	
	
}
