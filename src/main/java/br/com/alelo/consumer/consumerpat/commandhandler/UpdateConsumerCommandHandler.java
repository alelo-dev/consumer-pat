package br.com.alelo.consumer.consumerpat.commandhandler;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.command.UpdateConsumerCommand;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Component
public class UpdateConsumerCommandHandler implements CommandHandler<UpdateConsumerCommand, Consumer >{

    @Autowired
    ConsumerRepository repository;

    @Override
    public Consumer handle(UpdateConsumerCommand command) {

        Consumer consumerById = repository.findById(command.getId()).orElseThrow(() -> new RuntimeException("Consumer not found"));

        Consumer consumer = new Consumer(command);

        try {
            BeanUtils.copyProperties(consumer, consumerById, getNullPropertyNames(consumer));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error copying properties", e);
        }

        return  repository.save(consumerById);
        
    }

    private String[] getNullPropertyNames(Consumer source) throws IllegalArgumentException, IllegalAccessException {
        Class<Consumer> consumerClass = Consumer.class;

        Field[] fields = consumerClass.getDeclaredFields();

        String[] nullPropertyNames = new String[0];

        for (Field field : fields) {
            field.setAccessible(true);
            var objeto = field.get(source);

            if (objeto == null || objeto.equals(0)) {
                nullPropertyNames = Stream.concat(Stream.of(nullPropertyNames), Stream.of(field.getName())).toArray(String[]::new);
            }
        }

        return Stream.concat(Stream.of(nullPropertyNames), Stream.of("foodCardBalance", "fuelCardBalance", "drugstoreCardBalance")).toArray(String[]::new);
    }
    
}
