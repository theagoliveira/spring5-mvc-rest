package guru.springframework.spring5mvcrest.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import guru.springframework.spring5mvcrest.domain.Customer;

class CustomerMapperTest {

    private static final Long ID = 1L;
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    void testCustomerToCustomerDTO() {
        // given
        var customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        // when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        // then
        assertEquals(ID, customerDTO.getId());
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
        assertEquals("/api/v1/customers/" + ID, customerDTO.getCustomerUrl());
    }

}
