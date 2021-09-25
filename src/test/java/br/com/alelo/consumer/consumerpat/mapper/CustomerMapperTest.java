package br.com.alelo.consumer.consumerpat.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import br.com.alelo.consumer.consumerpat.dto.CustomerDto;
import br.com.alelo.consumer.consumerpat.entity.Customer;
import org.junit.jupiter.api.Test;

public class CustomerMapperTest {

    private final CustomerMapper mapper = new CustomerMapper();

    @Test
    public void whenMapCustomerToCustomerDto_thenSuccess() {
        Long id = 1745L;
        String documentNumber = "documentNumber";
        LocalDate birthDate = LocalDate.now();
        String mobilePhoneNumber = "mobilePhoneNumber";
        String residencePhoneNumber = "residencePhoneNumber";
        String phoneNumber = "phoneNumber";
        String email = "email";
        String street = "street";
        int number = 45;
        String city = "city";
        String country = "country";
        String portalCode = "portalCode";

        Customer customer = Customer.builder()
                .id(id)
                .documentNumber(documentNumber)
                .birthDate(birthDate)
                .mobilePhoneNumber(mobilePhoneNumber)
                .residencePhoneNumber(residencePhoneNumber)
                .phoneNumber(phoneNumber)
                .email(email)
                .street(street)
                .number(number)
                .city(city)
                .country(country)
                .portalCode(portalCode)
                .build();

        CustomerDto dto = mapper.mapModelToDto(customer);

        assertEquals(id, dto.getId());
        assertEquals(documentNumber, dto.getDocumentNumber());
        assertEquals(birthDate, dto.getBirthDate());
        assertEquals(mobilePhoneNumber, dto.getMobilePhoneNumber());
        assertEquals(residencePhoneNumber, dto.getResidencePhoneNumber());
        assertEquals(phoneNumber, dto.getPhoneNumber());
        assertEquals(email, dto.getEmail());
        assertEquals(street, dto.getStreet());
        assertEquals(number, dto.getNumber());
        assertEquals(city, dto.getCity());
        assertEquals(country, dto.getCountry());
        assertEquals(portalCode, dto.getPortalCode());
    }

    @Test
    public void whenMapCustomerDtoToCustomer_thenSuccess() {
        Long id = 1745L;
        String documentNumber = "documentNumber";
        LocalDate birthDate = LocalDate.now();
        String mobilePhoneNumber = "mobilePhoneNumber";
        String residencePhoneNumber = "residencePhoneNumber";
        String phoneNumber = "phoneNumber";
        String email = "email";
        String street = "street";
        int number = 45;
        String city = "city";
        String country = "country";
        String portalCode = "portalCode";


        CustomerDto dto = CustomerDto.builder()
                .id(id)
                .documentNumber(documentNumber)
                .birthDate(birthDate)
                .mobilePhoneNumber(mobilePhoneNumber)
                .residencePhoneNumber(residencePhoneNumber)
                .phoneNumber(phoneNumber)
                .email(email)
                .street(street)
                .number(number)
                .city(city)
                .country(country)
                .portalCode(portalCode)
                .build();

        Customer customer = mapper.mapDtoToModel(dto);

        assertEquals(documentNumber, customer.getDocumentNumber());
        assertEquals(birthDate, customer.getBirthDate());
        assertEquals(mobilePhoneNumber, customer.getMobilePhoneNumber());
        assertEquals(residencePhoneNumber, customer.getResidencePhoneNumber());
        assertEquals(phoneNumber, customer.getPhoneNumber());
        assertEquals(email, customer.getEmail());
        assertEquals(street, customer.getStreet());
        assertEquals(number, customer.getNumber());
        assertEquals(city, customer.getCity());
        assertEquals(country, customer.getCountry());
        assertEquals(portalCode, customer.getPortalCode());
    }

    @Test
    public void whenUpdateCustomerFromDtoData_thenSuccess() {
        Long id = 1745L;
        String documentNumber = "new documentNumber";
        LocalDate birthDate = LocalDate.now().minusDays(2);
        String mobilePhoneNumber = "new mobilePhoneNumber";
        String residencePhoneNumber = "new residencePhoneNumber";
        String phoneNumber = "new phoneNumber";
        String email = "new email";
        String street = "new street";
        int number = 85;
        String city = "new city";
        String country = "new country";
        String portalCode = "new portalCode";

        Customer customer = Customer.builder()
                .id(id)
                .documentNumber("documentNumber")
                .birthDate(LocalDate.now())
                .mobilePhoneNumber("mobilePhoneNumber")
                .residencePhoneNumber("residencePhoneNumber")
                .phoneNumber("phoneNumber")
                .email("email")
                .street("street")
                .number(45)
                .city("city")
                .country("country")
                .portalCode("portalCode")
                .build();

        CustomerDto dto = CustomerDto.builder()
                .id(id)
                .documentNumber(documentNumber)
                .birthDate(birthDate)
                .mobilePhoneNumber(mobilePhoneNumber)
                .residencePhoneNumber(residencePhoneNumber)
                .phoneNumber(phoneNumber)
                .email(email)
                .street(street)
                .number(number)
                .city(city)
                .country(country)
                .portalCode(portalCode)
                .build();

        customer = mapper.updateModelTarget(customer, dto);

        assertEquals(id, customer.getId());
        assertEquals(documentNumber, customer.getDocumentNumber());
        assertEquals(birthDate, customer.getBirthDate());
        assertEquals(mobilePhoneNumber, customer.getMobilePhoneNumber());
        assertEquals(residencePhoneNumber, customer.getResidencePhoneNumber());
        assertEquals(phoneNumber, customer.getPhoneNumber());
        assertEquals(email, customer.getEmail());
        assertEquals(street, customer.getStreet());
        assertEquals(number, customer.getNumber());
        assertEquals(city, customer.getCity());
        assertEquals(country, customer.getCountry());
        assertEquals(portalCode, customer.getPortalCode());
    }

}
