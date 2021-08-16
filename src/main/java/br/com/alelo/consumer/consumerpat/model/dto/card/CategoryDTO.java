package br.com.alelo.consumer.consumerpat.model.dto.card;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;	
	private String description;
}
