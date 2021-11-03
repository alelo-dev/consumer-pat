package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

//Melhorias - Troquei o construtor explicitamente definido da classe Extract pela anotação AllArgsConstructor do Lombok.
//Adicionei um NoArgsConstructor que resolveu um alerta do Java que as classes Jpa deveriam ter um construtor NoArgs
//Removi o assignment da variável establishmentNameId no último construtor, pois não servia de nada já que o já havia sido atribuido anteriormente e só estava redundante/inútil.
//Troquei a variável do tipo date por LocalDateTime, já que a Date é basicamente "legado" com diversos problemas e desde o Java 8 pode ser substituida por LocalDate e não mais usada em novos códigos.
//Troquei int por Integer, pois é melhor a classe Wrapper já que tem mais funcionalidades e também facilita para lidar com nulos, principalmente no banco de dados.
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Extract {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer establishmentNameId;
    String establishmentName;
    String productDescription;
    LocalDateTime dateBuy;
    String cardNumber;
    Double value;

    public Extract(String establishmentName, String productDescription, LocalDateTime dateBuy, String cardNumber, Double value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
