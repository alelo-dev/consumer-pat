package br.com.alelo.consumer.consumerpat.controller;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BussinessException;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/consumer")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ConsumerController {

    private final @NonNull
    ConsumerService service;

    //LISTAR
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/findAll")
    public Page<Consumer> listAllConsumers(Pageable pageable) {
        return service.findAll(pageable);
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Consumer> findById(@PathVariable Long id) {
        Optional<Consumer> consumerOptional = service.findOne(id);
        return consumerOptional
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    // CADASTRAR
    @PostMapping(value = "/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
        return ResponseEntity.ok(service.save(consumer));
    }

  //ALTERAR
    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Consumer> updateConsumer(@PathVariable Long id, @RequestBody Consumer consumer) throws BussinessException {
        Consumer update = null;
        if (id.equals(consumer.getId())) {
            update = service.update(consumer);
        }

        if (update == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(update);
    }

}