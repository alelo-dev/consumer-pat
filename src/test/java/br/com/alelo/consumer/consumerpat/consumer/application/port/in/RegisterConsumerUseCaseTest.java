package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import br.com.alelo.consumer.consumerpat.consumer.adapter.out.ConsumerApiModel;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegisterConsumerUseCaseTest {

    @Mock
    private SaveConsumerPort saveConsumerPort;

    @Spy
    private ConsumerConverter consumerConverter = new ConsumerConverter();

    @InjectMocks
    private RegisterConsumerUseCase registerConsumerUseCase;

    @Nested
    class SuccessCases {

        @Test
        void shouldRegisterConsumerSuccessfully() {

            var newConsumer = new NewConsumer("John Smith", 12345,
                    LocalDate.of(1985, Month.JANUARY, 1));
            var consumer = new Consumer();
            var command = RegisterConsumerCommand.of(newConsumer);

            given(saveConsumerPort.save(consumer))
                    .willReturn(consumer);

            given(consumerConverter.toEntity(command))
                    .willReturn(consumer);

            assertThat(registerConsumerUseCase.registerConsumer(command))
                    .isNotNull()
                    .isInstanceOf(ConsumerApiModel.class);

            verify(saveConsumerPort, times(1))
                    .save(consumer);

            verify(consumerConverter, times(1))
                    .toEntity(command);

            verify(consumerConverter, times(1))
                    .toApiModel(consumer);
        }
    }
}