package br.com.alelo.consumer.consumerpat.application.core.domain.payments;

public class Establishment {
    private String establishmentName;
    private String establishmentType;

    public Establishment() {
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public String getEstablishmentType() {
        return establishmentType;
    }

    public void setEstablishmentType(String establishmentType) {
        this.establishmentType = establishmentType;
    }
}
