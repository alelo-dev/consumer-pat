package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.assembler.ProductDTOAssembler;
import br.com.alelo.consumer.consumerpat.disassembler.ProductInputDTODisassembler;
import br.com.alelo.consumer.consumerpat.dto.ProductDTO;
import br.com.alelo.consumer.consumerpat.dto.input.ProductInputDTO;
import br.com.alelo.consumer.consumerpat.entity.Product;
import br.com.alelo.consumer.consumerpat.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ProductController.PATH)
public class ProductController {

    protected static final String PATH = "products";

    private final ProductService productService;
    private final ProductDTOAssembler productDTOAssembler;
    private final ProductInputDTODisassembler productInputDTODisassembler;

    public ProductController(ProductService productService, ProductDTOAssembler productDTOAssembler,
                             ProductInputDTODisassembler productInputDTODisassembler) {
        this.productService = productService;
        this.productDTOAssembler = productDTOAssembler;
        this.productInputDTODisassembler = productInputDTODisassembler;
    }


    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        Product product = productService.findOrFail(id);
        return productDTOAssembler.toModel(product);
    }

    @GetMapping("/findByName")
    public ProductDTO findProductByName(@RequestBody ProductInputDTO productInputDTO) {
        Product product = productService.findByName(productInputDTO.getName());
        return productDTOAssembler.toModel(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO save(@RequestBody ProductInputDTO productInputDTO) {
        Product product = productInputDTODisassembler.toDomainObject(productInputDTO);
        return productDTOAssembler.toModel(productService.save(product));
    }

}
