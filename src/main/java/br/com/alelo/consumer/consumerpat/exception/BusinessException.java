package br.com.alelo.consumer.consumerpat.exception;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6671685198965065279L;
	
	private String msg;
	
    public BusinessException(String msg){
      super(msg);
      this.msg = msg;
    }
    
    public String getMessage(){
      return msg;
    }
}
