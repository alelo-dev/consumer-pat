package br.com.alelo.consumer.consumerpat.disassembler;

import br.com.alelo.consumer.consumerpat.dto.input.ProductInputDTO;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductInputDTODisassembler {

    public Product toDomainObject(ProductInputDTO productInputDTO) {
        Product product = new Product();

        product.setName(productInputDTO.getName());
        product.setDescription(productInputDTO.getDescription());
        product.setAmount(productInputDTO.getAmount());

        Establishment establishment = new Establishment();
        establishment.setId(productInputDTO.getEstablishmentId());
        product.setEstablishment(establishment);

        return product;
    }
}
