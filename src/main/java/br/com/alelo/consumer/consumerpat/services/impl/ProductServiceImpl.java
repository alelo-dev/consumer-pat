package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Product;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.ProductNameNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ProductNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.ProductRepository;
import br.com.alelo.consumer.consumerpat.services.EstablishmentService;
import br.com.alelo.consumer.consumerpat.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final EstablishmentService establishmentService;

    public ProductServiceImpl(ProductRepository productRepository, EstablishmentService establishmentService) {
        this.productRepository = productRepository;
        this.establishmentService = establishmentService;
    }


    @Override
    public Product findOrFail(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id) {
                });
    }

    public Product findByName(String name) {
        return productRepository.findProductByNameContaining(name)
                .orElseThrow(() -> new ProductNameNotFoundException(name) {
                });
    }

    @Override
    public Product save(Product product) {

        Establishment establishment = establishmentService.findOrFail(product.getEstablishment().getId());
        product.setEstablishment(establishment);

        Optional<Product> currentProduct = productRepository.findProductByNameContaining(product.getName());

        if (currentProduct.isPresent()) {
            throw new BusinessException(String.format("Já existe um produto de nome: %s", product.getName()));
        }

        return productRepository.save(product);
    }

}
