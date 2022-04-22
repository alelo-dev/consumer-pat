package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class ExtractDTO {

    private Long id;

    private OffsetDateTime date;

    private Establishment establishment;

    private ConsumerResumeDTO consumer;

    private Card card;

    private Product product;

    private BigDecimal amount;

}