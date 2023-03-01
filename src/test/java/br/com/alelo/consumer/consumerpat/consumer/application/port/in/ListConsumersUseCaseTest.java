package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import br.com.alelo.consumer.consumerpat.consumer.application.port.out.LoadConsumerPort;
import br.com.alelo.consumer.consumerpat.consumer.domain.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ListConsumersUseCaseTest {

    @Mock
    private LoadConsumerPort loadConsumerPort;

    @Spy
    private ConsumerConverter consumerConverter = new ConsumerConverter();

    @InjectMocks
    private ListConsumersUseCase listConsumersUseCase;

    @Nested
    class SuccessCases {

        @Test
        void shouldQueryAndReturnConsumers() {

            var command = ListConsumersCommand.of(PageRequest.of(0, 20));
            var consumers = new PageImpl<>(List.of(new Consumer()));

            given(loadConsumerPort.findAll(command.getPageable()))
                    .willReturn(consumers);

            assertThat(listConsumersUseCase.listAll(command))
                    .isNotEmpty()
                    .hasSize(consumers.getNumberOfElements());

            verify(loadConsumerPort, times(1))
                    .findAll(command.getPageable());

            verify(consumerConverter, times(1))
                    .toApiModel(any());
        }
    }
}