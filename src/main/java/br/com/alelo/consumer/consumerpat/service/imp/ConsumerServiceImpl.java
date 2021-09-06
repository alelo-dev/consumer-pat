package br.com.alelo.consumer.consumerpat.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService {
	

  @Autowired
	private ConsumerRepository repository;

	//TODO Fazer validacoes dos campos obrigatorios.
	@Override
	public void createConsumer(Consumer consumer)  {
		repository.save(consumer);
	}
	/* Cadastrar novos clientes */
//@RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
//public void createConsumer(@RequestBody Consumer consumer) {
//    repository.save(consumer);
//}

 //TODO Fazer a validacao se existe o consumer cadastrado e verificar se nao sera alterado o saldo.
	@Override
	public void updateConsumer(Consumer consumer)  {
		repository.save(consumer);
	}
	 // Não deve ser possível alterar o saldo do cartão
//@RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
//public void updateConsumer(@RequestBody Consumer consumer) {
//    repository.save(consumer);
//}

	//TODO Fazer paginção
	@Override
	public List<Consumer> listAllConsumers() {
		return repository.findAll();
	}
}
