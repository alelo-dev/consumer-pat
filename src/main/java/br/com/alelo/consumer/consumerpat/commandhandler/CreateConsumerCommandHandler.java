package br.com.alelo.consumer.consumerpat.commandhandler;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.command.CreateConsumerCommand;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Component
public class CreateConsumerCommandHandler implements CommandHandler<CreateConsumerCommand, Consumer >{

    @Autowired
    ConsumerRepository repository;

    @Override
    public Consumer handle(CreateConsumerCommand command) {

        Consumer consumer = new Consumer(command);

        consumer.setId(UUID.randomUUID().toString());

        return  repository.save(consumer);
        
    }
    
}
