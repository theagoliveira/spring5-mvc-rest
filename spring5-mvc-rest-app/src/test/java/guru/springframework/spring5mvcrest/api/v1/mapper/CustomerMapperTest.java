package guru.springframework.spring5mvcrest.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guru.springframework.model.CustomerDTO;
import guru.springframework.spring5mvcrest.controllers.v1.CustomerController;
import guru.springframework.spring5mvcrest.domain.Customer;

class CustomerMapperTest {

    private static final String CUSTOMERS_URI_SLASH = CustomerController.BASE_URI + "/";
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
        assertEquals(CUSTOMERS_URI_SLASH + ID, customerDTO.getCustomerUrl());
    }

    @Test
    void testCustomerDTOToCustomer() {
        // given
        var customerDTO = new CustomerDTO();
        customerDTO.setId(ID);
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);
        customerDTO.setCustomerUrl(CUSTOMERS_URI_SLASH + ID);

        // when
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        // then
        assertEquals(ID, customer.getId());
        assertEquals(FIRST_NAME, customer.getFirstName());
        assertEquals(LAST_NAME, customer.getLastName());
    }

}
