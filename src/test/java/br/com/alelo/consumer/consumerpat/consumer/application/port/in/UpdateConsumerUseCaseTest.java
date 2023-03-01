package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import br.com.alelo.consumer.consumerpat.common.domain.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.consumer.adapter.out.ConsumerApiModel;
import br.com.alelo.consumer.consumerpat.consumer.application.port.out.LoadConsumerPort;
import br.com.alelo.consumer.consumerpat.consumer.application.port.out.SaveConsumerPort;
import br.com.alelo.consumer.consumerpat.consumer.domain.Consumer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateConsumerUseCaseTest {


    @Mock
    private LoadConsumerPort loadConsumerPort;

    @Spy
    private ConsumerConverter consumerConverter = new ConsumerConverter();

    @Mock
    private SaveConsumerPort saveConsumerPort;

    @InjectMocks
    private UpdateConsumerUseCase updateConsumerUseCase;

    @Nested
    class SuccessCases {

        @Test
        void shouldUpdateConsumerSuccessfully() {

            var consumerId = 1;
            var newConsumer = new UpdateConsumer("John Smith", 12345,
                    LocalDate.of(1985, Month.JANUARY, 1));
            var command = UpdateConsumerCommand.of(consumerId, newConsumer);
            var existingConsumer = consumerConverter.updateEntity(new Consumer(), newConsumer);

            given(loadConsumerPort.findByConsumerId(consumerId))
                    .willReturn(Optional.of(existingConsumer));

            given(saveConsumerPort.save(existingConsumer))
                    .willReturn(existingConsumer);

            assertThat(updateConsumerUseCase.updateConsumer(command))
                    .isNotNull()
                    .isInstanceOf(ConsumerApiModel.class);

            verify(loadConsumerPort, times(1))
                    .findByConsumerId(consumerId);

            verify(saveConsumerPort, times(1))
                    .save(existingConsumer);

            verify(consumerConverter, times(1))
                    .toApiModel(existingConsumer);
        }
    }

    @Nested
    class ErrorCases {

        @Test
        void shouldFailAndNotUpdateConsumer_WhenConsumerNotExists() {

            var consumerId = 1;
            var newConsumer = new UpdateConsumer("John Smith", 12345,
                    LocalDate.of(1985, Month.JANUARY, 1));
            var command = UpdateConsumerCommand.of(consumerId, newConsumer);

            assertThatThrownBy(() -> updateConsumerUseCase.updateConsumer(command))
                    .isInstanceOf(ConsumerNotFoundException.class);

            verify(loadConsumerPort, times(1))
                    .findByConsumerId(consumerId);

            verify(saveConsumerPort, never())
                    .save(any());

            verify(consumerConverter, never())
                    .toApiModel(any());
        }
    }
}