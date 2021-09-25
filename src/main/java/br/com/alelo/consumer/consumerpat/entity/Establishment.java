package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Establishment extends AbstractEntity {

    String establishmentName;

    @Builder
    public Establishment(final Long id, final String establishmentName) {
        super(id);
        this.establishmentName = establishmentName;
    }
}
