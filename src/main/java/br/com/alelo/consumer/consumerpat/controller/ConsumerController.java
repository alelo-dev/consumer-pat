package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

  @Autowired
  ConsumerRepository repository;

  @Autowired
  ExtractRepository extractRepository;

  @Autowired
  ConsumerService consumerService;

  @Operation(
    summary = "List Buy",
    description = "Description of your operation:List all purchases by date up to 500"
  )
  @ResponseBody
  @ResponseStatus(code = HttpStatus.OK)
  @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
  public List<Consumer> listAllConsumers() {
    return consumerService.getAllConsumers();
  }

  @Operation(
    summary = "New Buy",
    description = "Description of your operation: Route to add a new purchase"
  )
  @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
  public void createConsumer(@RequestBody Consumer consumer) {
    repository.save(consumer);
  }

  @Operation(
    summary = "Update Buy",
    description = "Description of your operation: Update a purchase"
  )
  @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
  public void updateConsumer(@RequestBody Consumer consumer) {
    repository.save(consumer);
  }

  @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
  public void setBalance(Integer cardNumber, double value) {
    Consumer consumer = null;
    consumer = repository.findByDrugstoreNumber(cardNumber);
    consumerService.validateConsumer(consumer, cardNumber, value);
  }

  // Anotações para documentação no Swagger
  @Operation(
    summary = "Buy List ",
    description = "Description of your operation: List All Buy"
  )
  @ResponseBody
  @RequestMapping(value = "/buy", method = RequestMethod.GET)
  public void buy(
    @RequestParam Integer establishmentNameId,
    @RequestParam Integer establishmentType,
    @RequestParam String establishmentName,
    @RequestParam Integer cardNumber,
    @RequestParam String productDescription,
    @RequestParam double value
  )
    throws Exception {
    consumerService.purchase(
      establishmentNameId,
      establishmentType,
      establishmentName,
      cardNumber,
      productDescription,
      value
    );
  }
}
