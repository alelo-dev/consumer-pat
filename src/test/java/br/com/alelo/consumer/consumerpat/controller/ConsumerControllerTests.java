package br.com.alelo.consumer.consumerpat.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerSaveDto;

@SpringBootTest
public class ConsumerControllerTests {
    
    @Autowired
    private ConsumerController controller;
    
    @Test
    public void createConsumer() throws Exception {

        var consumers = createListOfConsumers(50);
        consumers.stream().forEach(controller::createConsumer);

        Pageable pageable = PageRequest.of(0, 10);
        var      result   = controller.listAll(pageable);
        assertThat(result.getItems()).hasSize(10);
        
    }
    
    private List<ConsumerSaveDto> createListOfConsumers(final int size) {
        
        List<ConsumerSaveDto> consumers = new ArrayList<>();
        for (var i = 0; i <= size; i++) {
            var dto = ConsumerSaveDto.builder().birthDate(LocalDate.now()).documentNumber("12341234" + i)
                    .name("teste" + i).build();
            consumers.add(dto);
        }

        return consumers;
    }
    
    @Test
    public void getEmptyConsumers() throws Exception {
        
        Pageable pageable = PageRequest.of(0, 10);
        var      result   = controller.listAll(pageable);
        assertThat(result.getItems()).isEmpty();
    }
}
