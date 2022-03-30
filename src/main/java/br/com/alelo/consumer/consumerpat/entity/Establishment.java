package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.constants.LengthFieldsBD;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, length = LengthFieldsBD.LENGTH_100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID", nullable = false)
    private Type type;
}
