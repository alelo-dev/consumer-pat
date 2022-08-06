package br.com.alelo.consumer.consumerpat.mock;

import br.com.alelo.consumer.consumerpat.domain.Extract;
import br.com.alelo.consumer.consumerpat.domain.Type;
import br.com.alelo.consumer.consumerpat.request.PurchaseRequest;
import br.com.alelo.consumer.consumerpat.response.ExtractResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MockPurchaseDomain {

    public static Extract buildExtract() {
        return Extract.builder()
                .cardNumber("9557596836147377")
                .purchaseCode("f940e98a-db2d-4276-ae0b-fcfb5466a121")
                .amount(BigDecimal.valueOf(100))
                .establishmentName("ATACADÃO")
                .establishmentType(Type.FOOD_SQUARE)
                .dateBuy(LocalDate.now())
                .cardNumber("2979728773456292")
                .products(List.of("Oreo","Toddynho"))
                .build();
    }

    public static ExtractResponse buildExtractResponse() {
        return ExtractResponse.builder()
                .cardNumber("9557596836147377")
                .purchaseCode("f940e98a-db2d-4276-ae0b-fcfb5466a121")
                .amount(BigDecimal.valueOf(100))
                .establishmentName("ATACADÃO")
                .establishmentType(Type.FOOD_SQUARE)
                .dateBuy(LocalDate.now())
                .cardNumber("2979728773456292")
                .products(List.of("Oreo","Toddynho"))
                .build();
    }

    public static PurchaseRequest buildPurchaseRequest() {
        return PurchaseRequest.builder()
                .cardNumber("9557596836147377")
                .amount(BigDecimal.valueOf(100))
                .establishmentName("ATACADÃO")
                .establishmentType(Type.FOOD_SQUARE)
                .cardNumber("2979728773456292")
                .products(List.of("Oreo","Toddynho"))
                .build();
    }

    public static Extract buildExtractFuel() {
        return Extract.builder()
                .cardNumber("9557596836147377")
                .purchaseCode("f940e98a-db2d-4276-ae0b-fcfb5466a121")
                .amount(BigDecimal.valueOf(100))
                .establishmentName("ATACADÃO")
                .establishmentType(Type.FUEL_STATION)
                .dateBuy(LocalDate.now())
                .cardNumber("2979728773456292")
                .products(List.of("Oreo","Toddynho"))
                .build();
    }

    public static PurchaseRequest buildPurchaseRequestFuel() {

        return PurchaseRequest.builder()
                .cardNumber("9557596836147377")
                .amount(BigDecimal.valueOf(100))
                .establishmentName("ATACADÃO")
                .establishmentType(Type.FUEL_STATION)
                .cardNumber("2979728773456292")
                .products(List.of("Oreo","Toddynho"))
                .build();
    }

    public static Extract buildExtractDrug() {
        return Extract.builder()
                .cardNumber("9557596836147377")
                .purchaseCode("f940e98a-db2d-4276-ae0b-fcfb5466a121")
                .amount(BigDecimal.valueOf(100))
                .establishmentName("ATACADÃO")
                .establishmentType(Type.PHARMACY)
                .dateBuy(LocalDate.now())
                .cardNumber("2979728773456292")
                .products(List.of("Oreo","Toddynho"))
                .build();
    }

    public static PurchaseRequest buildPurchaseRequestDrug() {
        return PurchaseRequest.builder()
                .cardNumber("9557596836147377")
                .amount(BigDecimal.valueOf(100))
                .establishmentName("ATACADÃO")
                .establishmentType(Type.PHARMACY)
                .cardNumber("2979728773456292")
                .products(List.of("Oreo","Toddynho"))
                .build();
    }
}
