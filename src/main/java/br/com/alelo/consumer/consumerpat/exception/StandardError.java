package br.com.alelo.consumer.consumerpat.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable {
  
    private static final long serialVersionUID = -883986984800862493L;
    
	private Integer status_code;
	
    private String message;
    
}
