package br.com.alelo.consumer.consumerpat.adapter.in.web;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.alelo.consumer.consumerpat.adapter.in.ConsumerAdapter;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ConsumerIdentificationDTO;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ConsumerUpdateDTO;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

  private ConsumerAdapter consumerAdapter;

  public ConsumerController(final ConsumerAdapter consumerAdapter) {
    this.consumerAdapter = consumerAdapter;
  }
  
  @PostMapping(value = "/create")
  @ResponseBody
  @ResponseStatus(code = HttpStatus.OK)
  public ConsumerIdentificationDTO create(@RequestBody ConsumerDTO consumerDTO) {
    return consumerAdapter.create(consumerDTO);
  }

  // Não deve ser possível alterar o saldo do cartão
  @PutMapping(value = "/update")
  @ResponseBody
  @ResponseStatus(code = HttpStatus.OK)
  public ConsumerIdentificationDTO update(@RequestBody ConsumerUpdateDTO updateConsumerDTO) {
    return consumerAdapter.update(updateConsumerDTO);
  }

  // /* Deve listar todos os clientes (cerca de 500) */
  @GetMapping(value = "/list")
  @ResponseBody
  @ResponseStatus(code = HttpStatus.OK)
  public List<ConsumerDTO> list() {
    return consumerAdapter.list();
  }
}
