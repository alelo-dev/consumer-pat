package br.com.alelo.consumer.consumerpat.domain;

import br.com.alelo.consumer.consumerpat.enumerator.EstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "establishment")
@Builder
public class Establishment implements Serializable {
    @Id
    @Column(name = "cnpj_establishment", unique = true, nullable = false)
    private String cnpj;

    @Column(name = "establishment_name" ,  nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private EstablishmentType type;
}
