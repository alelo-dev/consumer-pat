package br.com.alelo.consumer.consumerpat.exception;

/**
 * Service Exception for Entity Not Found
 */
public class EntityNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String identifier;

    private final String type;

    /**
     * @param type       - Entity type
     * @param identifier - Entity identifier
     * @param message    - Exception message
     */
    public EntityNotFoundException(final String type, final String identifier, final String message) {
        super(message);
        this.type = type;
        this.identifier = identifier;
    }
    
    /**
     * @param message    - Exception message
     */
    public EntityNotFoundException(final String message) {
    	this(null, null, message);
    }

    /**
     * @param type       - Entity type
     * @param identifier - Entity identifier
     */
    public EntityNotFoundException(final String type, final String identifier) {
        this(type, identifier, null);
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getType() {
        return type;
    }
}
