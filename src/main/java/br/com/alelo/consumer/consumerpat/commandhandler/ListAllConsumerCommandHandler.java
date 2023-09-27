package br.com.alelo.consumer.consumerpat.commandhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.command.FindAllConsumerCommand;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Component
public class ListAllConsumerCommandHandler implements CommandHandler<FindAllConsumerCommand, Page<Consumer>> {

    @Autowired
    ConsumerRepository consumerRepository;

    @Override
    public Page<Consumer> handle(FindAllConsumerCommand command) {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Direction.ASC,
                "id");
        return new PageImpl<>(
                consumerRepository.getAllConsumersList(), 
                pageRequest, size);
    }
    
}
