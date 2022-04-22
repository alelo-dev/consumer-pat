package br.com.alelo.consumer.consumerpat.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class BaseEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(
            generator = "optimized-sequence"
    )
    @GenericGenerator(
            name = "optimized-sequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(
                    name = "prefer_sequence_per_entity",
                    value = "true"
            ), @org.hibernate.annotations.Parameter(
                    name = "initial_value",
                    value = "1"
            ), @org.hibernate.annotations.Parameter(
                    name = "increment_size",
                    value = "5"
            ), @org.hibernate.annotations.Parameter(
                    name = "optimizer",
                    value = "pooled-lo"
            )})
    private Long id;

}
