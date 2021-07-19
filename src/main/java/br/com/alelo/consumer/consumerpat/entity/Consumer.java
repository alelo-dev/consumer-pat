package br.com.alelo.consumer.consumerpat.entity;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String documentNumber;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "consumer_id", referencedColumnName = "id")
    private List<Contacts> contactsList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "consumer_id", referencedColumnName = "id")
    private List<Card> cardsList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "consumer_id", referencedColumnName = "id")
    private List<Address> addressList;

}
