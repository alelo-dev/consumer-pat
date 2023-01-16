        package br.com.alelo.consumer.consumerpat.controller;
        import br.com.alelo.consumer.consumerpat.entity.Consumer;
        import br.com.alelo.consumer.consumerpat.entity.Extract;
        import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
        import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
        import java.util.Date;
        import java.util.List;


        @Controller
        @RequestMapping("/consumer")
        public class ConsumerController {

            @Autowired
            ConsumerRepository repository;

            @Autowired
            ExtractRepository extractRepository;


            @ResponseBody
            @ResponseStatus(code = HttpStatus.OK)
            @GetMapping
            public List<Consumer> listAllConsumers() {
                return repository.getAllConsumersList();
            }
            @PostMapping
            public void CreateConsumer(@RequestBody Consumer consumer) {
                repository.save(consumer);
            }

            @PutMapping
            public void UpdateConsumer(@RequestBody Consumer consumer) {
                repository.save(consumer);
            }

            public void SetBalance(int cardNumber, double value) {
                Consumer consumer = null;
                consumer = repository.findByDrugstoreNumber(cardNumber);

                if(consumer != null) {

                    repository.save(consumer);
                } else {
                    consumer = repository.findByFoodCardNumber(cardNumber);
                    if(consumer != null) {


                        repository.save(consumer);
                    } else {

                        consumer = repository.findByFuelCardNumber(cardNumber);

                        repository.save(consumer);
                    }
                }
            }


            public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
                Consumer consumer = null;


                if (establishmentType == 1) {

                    Double cashback  = (value / 100) * 10;
                    value = value - cashback;

                    consumer = repository.findByFoodCardNumber(cardNumber);

                    repository.save(consumer);

                }else if(establishmentType == 2) {
                    consumer = repository.findByDrugstoreNumber(cardNumber);

                    repository.save(consumer);

                } else {

                    Double tax  = (value / 100) * 35;
                    value = value + tax;

                    consumer = repository.findByFuelCardNumber(cardNumber);

                    repository.save(consumer);
                }

                Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
                extractRepository.save(extract);
            }

        }
