package br.com.alelo.consumer.consumerpat.helpers.purchase.enums;

public enum PurchaseNamesEnum {
    FoodEstablishmentPurchase(1),
    DrugstoreEstablishmentPurchase(2),
    FuelEstablishmentPurchase(3);

    private final int purchaseType;

    PurchaseNamesEnum(final int purchaseType) {
        this.purchaseType = purchaseType;
    }

    public int getPurchaseType() {
        return purchaseType;
    }

    public static PurchaseNamesEnum getPurchaseEnum(final int establishmentType) {
        for (PurchaseNamesEnum purchase : PurchaseNamesEnum.values()) {
            if (purchase.purchaseType == establishmentType) {
                return purchase;
            }
        }
        throw new IllegalArgumentException("Purchase Type not Found");
    }
}
