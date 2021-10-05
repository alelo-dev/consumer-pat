package br.com.alelo.consumer.consumerpat.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.domain.entity.Product;
import br.com.alelo.consumer.consumerpat.domain.exception.EntityInUseException;
import br.com.alelo.consumer.consumerpat.domain.exception.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exception.NonNullPropertyException;
import br.com.alelo.consumer.consumerpat.domain.respository.ProductRepository;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		Product product = productRepository
				.findById(id)
				.orElseThrow(() -> 
					new EntityNotFoundException(
							String.format("Produto de código %d não cadastrado na base de dados!", id)));
		
		return product;
	}
	
	public Product save(Product product) {
		try{
			return productRepository.save(product);
		}
		catch(DataIntegrityViolationException e) {
			throw new NonNullPropertyException(String.format("Verifique os dados inseridos no objeto."));
		}
	}
	
	
	public void delete(Long id) {
		try {
			productRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Produto de código %d não pode ser removido, pois está em uso.", id));
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Produto de código %d não existe na base de dados!", id));
		}
	}
	
	
}
