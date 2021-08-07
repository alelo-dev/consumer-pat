package br.com.alelo.consumer.consumerpat.exception;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(Long id, Class<?> clazz)  {
		super("Objeto não foi encontrado! ID:" + id + ", class:" + clazz.getName());
	}
}
