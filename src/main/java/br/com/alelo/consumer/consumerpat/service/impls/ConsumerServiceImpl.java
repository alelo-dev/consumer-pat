package br.com.alelo.consumer.consumerpat.service.impls;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.parser.interfaces.ConsumerParser;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.interfaces.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ConsumerParser consumerParser;

    @Override
    public ConsumerDTO insert(ConsumerDTO consumerDTO) {
        Consumer consumer = consumerParser.parse(consumerDTO);
        Consumer consumerInserted = consumerRepository.save(consumer);

        fillIdsToReturn(consumerDTO, consumerInserted);

        return consumerDTO;
    }

    private void fillIdsToReturn(ConsumerDTO consumerDTO, Consumer consumer) {
        consumerDTO.setId(consumer.getId());
        consumerDTO.getAddress().setId(consumer.getAddress().getId());
        consumerDTO.getContact().setId(consumer.getContact().getId());

    }

    @Override
    public ConsumerDTO update(ConsumerDTO consumerDTO) {
        consumerRepository.save(consumerParser.parse(consumerDTO));

        return consumerDTO;
    }

    @Override
    public List<ConsumerDTO> getAllConsumersList() {
        return consumerParser.parse(consumerRepository.findAll());
    }
}
