package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "custom_user")
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    @Column
    private String name;

    @NonNull
    @Column
    private String password;

}