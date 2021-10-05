package br.com.alelo.consumer.consumerpat.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_extract")
public class Extract {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String cardNumber;
    
    @Column(nullable = false)
    private String establishmentName;
    
    @Column(nullable = false)
    private BigDecimal value;
    
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Consumer consumer;
    
    @ManyToMany
    @JoinTable(
      name = "tb_extract_product", 
      joinColumns = @JoinColumn(name = "extract_id"), 
      inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    
	@CreationTimestamp
	@Column(columnDefinition = "datetime")
	private LocalDateTime dateBuy;
	
	public Extract(String cardNumber, String establishmentName, BigDecimal value, List<Product> products, Consumer consumer) {
		this.cardNumber = cardNumber;
		this.establishmentName = establishmentName;
		this.value = value;
		this.products = products;
		this.consumer = consumer;
	}
    
}
