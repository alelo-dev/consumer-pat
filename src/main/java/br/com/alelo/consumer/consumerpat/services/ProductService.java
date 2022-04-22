package br.com.alelo.consumer.consumerpat.services;


import br.com.alelo.consumer.consumerpat.entity.Product;

public interface ProductService {

    Product save(Product product);

    Product findOrFail(Long id);

    Product findByName(String name);

}
