package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class ExtractService {

  final ExtractRepository extractRepository;

  public Extract save(@RequestBody Extract extract) {
    return extractRepository.save(extract);
  }
}
