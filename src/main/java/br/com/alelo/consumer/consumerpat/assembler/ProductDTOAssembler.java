package br.com.alelo.consumer.consumerpat.assembler;

import br.com.alelo.consumer.consumerpat.dto.ProductDTO;
import br.com.alelo.consumer.consumerpat.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDTOAssembler {

    public ProductDTO toModel(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setAmount(product.getAmount());
        productDTO.setEstablishment(product.getEstablishment());

        return productDTO;

    }

    public List<ProductDTO> toCollectionModel(List<Product> products) {
        return products.stream()
                .map(product -> toModel(product))
                .collect(Collectors.toList());

    }
}
