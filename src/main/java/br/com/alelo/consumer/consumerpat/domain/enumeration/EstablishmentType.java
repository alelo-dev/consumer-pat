package br.com.alelo.consumer.consumerpat.domain.enumeration;

/**
 * Enumeração de tipos de estabelecimentos no quais
 * os cartões de benefícios do ocnsumidor podem ser utilizados.
 */
public enum EstablishmentType {

    FOOD(1,"Food"), // Alimentação
    DRUGSTORE(2,"Drugstore"), // Farmácia
    FUEL(3,"Fuel"); // Combustível

    private final int code;
    private final String description;

    private EstablishmentType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static boolean exists(EstablishmentType establishmentType) {

        boolean exists = false;

        for (EstablishmentType e : EstablishmentType.values()) {
            if (e.equals(establishmentType)) {
                return true;
            }
        }

        return exists;
    }

    public static boolean notExists(EstablishmentType establishmentType) {
        return !exists(establishmentType);
    }

    public static boolean isFoodEstablishment(EstablishmentType establishmentType) {
        return establishmentType != null && establishmentType.getCode() == 1;
    }

    public static boolean isDrugstoreEstablishment(EstablishmentType establishmentType) {
        return establishmentType != null && establishmentType.getCode() == 2;
    }

    public static boolean isFuelEstablishment(EstablishmentType establishmentType) {
        return establishmentType != null && establishmentType.getCode() == 3;
    }

}
