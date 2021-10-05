package br.com.alelo.consumer.consumerpat.api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.domain.dto.ProductDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Product;


@Component
public class ProductMapper {

	public ProductDTO entityToDto(Product product) {
		ProductDTO productDTO = new ProductDTO();
		BeanUtils.copyProperties(product, productDTO);
		return productDTO;
	}
	
	public List<Product> dtoToEntity(List<ProductDTO> dtos) {
		List<Product> products = new ArrayList<>();
		
		for(ProductDTO dto : dtos) {
			Product product = new Product();
			BeanUtils.copyProperties(dto, product);
			products.add(product);
		}
		
		return products;
	}

}
