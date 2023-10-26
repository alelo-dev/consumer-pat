package br.com.alelo.consumer.consumerpat.adapters.in.controller.customer;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.mapper.CustomerMapper;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.request.CustomerRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response.CustomerResponse;
import br.com.alelo.consumer.consumerpat.application.ports.in.customer.FindCustomerInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.in.customer.InsertCustomerInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.in.customer.UpdateCustomerInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final InsertCustomerInputPort insertCustomerInputPort;
    private final FindCustomerInputPort findCustomerInputPort;
    private final UpdateCustomerInputPort updateCustomerInputPort;
    private final CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> findAllCustomers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size) {
        Pageable pageable = PageRequest.of(page, size);
        var customerList = findCustomerInputPort.findAllCustomers(pageable);
        var customerResponseList = customerList.stream().map(customerMapper::toCustomerResponse).collect(Collectors.toList());
        return ResponseEntity.ok(new PageImpl<>(customerResponseList));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> findCustomer(@PathVariable("customerId") UUID customerId) {
        var customer = findCustomerInputPort.findCustomerById(customerId);
        var response = customer.map(customerMapper::toCustomerResponse);
        return response.map(customerResponse -> ResponseEntity.status(HttpStatus.OK)
                .body(customerResponse)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomerResponse()));
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@Valid  @RequestBody CustomerRequest customerRequest) {
        insertCustomerInputPort.insert(customerMapper.toCustomer(customerRequest));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> updateConsumer(@PathVariable("customerId") UUID customerId, @Valid @RequestBody CustomerRequest customerRequest) {
        updateCustomerInputPort.update(customerId, customerMapper.toCustomer(customerRequest));
        return ResponseEntity.noContent().build();
    }

//
//    /*
//     * Débito de valor no cartão (compra)
//     *
//     * establishmentType: tipo do estabelecimento comercial
//     * establishmentName: nome do estabelecimento comercial
//     * cardNumber: número do cartão
//     * productDescription: descrição do produto
//     * value: valor a ser debitado (subtraído)
//     */
//    @ResponseBody
//    @RequestMapping(value = "/buy", method = RequestMethod.GET)
//    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
//        Customer customer = null;
//        /* O valor só podem ser debitado do catão com o tipo correspondente ao tipo do estabelecimento da compra.
//
//        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação (food) então o valor só pode ser debitado do cartão alimentação
//        *
//        * Tipos dos estabelcimentos:
//        *    1) Alimentação (Food)
//        *    2) Farmácia (DrugStore)
//        *    3) Posto de combustivel (Fuel)
//        */
//
//        if (establishmentType == 1) {
//            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
//            Double cashback  = (value / 100) * 10;
//            value = value - cashback;
//
//            customer = repository.findByFoodCardNumber(cardNumber);
//            customer.setFoodCardBalance(customer.getFoodCardBalance() - value);
//            repository.save(customer);
//
//        }else if(establishmentType == 2) {
//            customer = repository.findByDrugstoreNumber(cardNumber);
//            customer.setDrugstoreCardBalance(customer.getDrugstoreCardBalance() - value);
//            repository.save(customer);
//
//        } else {
//            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
//            Double tax  = (value / 100) * 35;
//            value = value + tax;
//
//            customer = repository.findByFuelCardNumber(cardNumber);
//            customer.setFuelCardBalance(customer.getFuelCardBalance() - value);
//            repository.save(customer);
//        }
//
//        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
//        extractRepository.save(extract);
//    }

}
