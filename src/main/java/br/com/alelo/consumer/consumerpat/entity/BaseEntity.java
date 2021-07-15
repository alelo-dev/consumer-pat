package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    protected boolean active = true;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    @JsonIgnore
    private LocalDateTime updatedAt;

    @Version
    @Column(name = "VERSION")
    private long version;

    @JsonIgnore
    private boolean deleted;
}
