package br.com.alelo.consumer.consumerpat.entity;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.alelo.consumer.consumerpat.dto.v2.ContactDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ContactEntity {

    // Usaria MapperStruct
    public ContactEntity (ContactDTO dto){
        this.id = dto.getId();
        this.phones = dto.getPhones() != null
                ? dto.getPhones().stream().map(PhoneEntity::new).collect(Collectors.toSet())
                : null;
        this.email = dto.getEmail();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PhoneEntity> phones;
    private String email;

}
