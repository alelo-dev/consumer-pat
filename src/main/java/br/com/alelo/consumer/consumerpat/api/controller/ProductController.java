package br.com.alelo.consumer.consumerpat.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.alelo.consumer.consumerpat.domain.entity.Product;
import br.com.alelo.consumer.consumerpat.domain.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = ProductController.PRODUCT_ENDPOINT)
public class ProductController {

    public static final String PRODUCT_ENDPOINT = "/products";
	
    @Autowired
    private ProductService productService;
    

    @GetMapping
    public List<Product> readAll() {
    	return productService.findAll();
    }
    
    @GetMapping("/{id}")
    public Product readOne(@PathVariable Long id) {
    	return productService.findById(id);
    }

    @PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Product product) {
    	productService.save(product);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Product product) {
    	Product productIn = productService.findById(id);
		BeanUtils.copyProperties(product, productIn, "id");
    	productService.save(productIn);
    }

    @DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
    	productService.delete(id);
	}


}
