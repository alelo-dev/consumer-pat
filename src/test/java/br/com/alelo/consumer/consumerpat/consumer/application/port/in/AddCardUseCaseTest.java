package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.in.CardConverter;
import br.com.alelo.consumer.consumerpat.common.application.port.in.NewCard;
import br.com.alelo.consumer.consumerpat.common.domain.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.common.domain.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.consumer.application.port.out.LoadConsumerPort;
import br.com.alelo.consumer.consumerpat.consumer.domain.Consumer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddCardUseCaseTest {

    @Mock
    private LoadConsumerPort loadConsumerPort;

    @Spy
    private CardConverter cardConverter = new CardConverter();

    @InjectMocks
    private AddCardUseCase addCardUseCase;

    @Nested
    class SuccessCases {

        @Test
        void shouldAddCardSuccessfully() {

            var consumer = new Consumer();
            var newCard = new NewCard("1111111111111111", CardTypeEnum.FOOD.getValue());
            var command = AddCardCommand.of(consumer.getConsumerId(), newCard);

            given(loadConsumerPort.findByConsumerId(consumer.getConsumerId()))
                    .willReturn(Optional.of(consumer));

            addCardUseCase.addCard(command);

            verify(loadConsumerPort, times(1))
                    .findByConsumerId(consumer.getConsumerId());

            verify(cardConverter, times(1))
                    .toEntity(newCard);
        }
    }

    @Nested
    class ErrorCases {

        @Test
        void shouldNotAddCard_WhenConsumerNotExists() {

            var consumer = new Consumer();
            var newCard = new NewCard("1111111111111111", CardTypeEnum.FOOD.getValue());
            var command = AddCardCommand.of(consumer.getConsumerId(), newCard);


            assertThatThrownBy(() -> addCardUseCase.addCard(command))
                    .isInstanceOf(ConsumerNotFoundException.class);

            verify(loadConsumerPort, times(1))
                    .findByConsumerId(consumer.getConsumerId());

            verify(cardConverter, never())
                    .toEntity(newCard);
        }
    }
}