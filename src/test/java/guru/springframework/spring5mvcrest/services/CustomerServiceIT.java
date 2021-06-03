package guru.springframework.spring5mvcrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import guru.springframework.spring5mvcrest.api.v1.mapper.CustomerMapper;
import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import guru.springframework.spring5mvcrest.bootstrap.Bootstrap;
import guru.springframework.spring5mvcrest.domain.Customer;
import guru.springframework.spring5mvcrest.repositories.CategoryRepository;
import guru.springframework.spring5mvcrest.repositories.CustomerRepository;
import guru.springframework.spring5mvcrest.repositories.VendorRepository;

@DataJpaTest
class CustomerServiceIT {

    private static final String NEW_FIRST_NAME = "newFirstName";
    private static final String NEW_LAST_NAME = "newLastName";

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    Bootstrap bootstrap;
    Long id;

    @BeforeEach
    void setUp() throws Exception {
        categoryRepository.deleteAll();
        customerRepository.deleteAll();

        bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();

        id = customerRepository.findAll().get(0).getId();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void testPatchCustomerFirstName() {
        Customer originalCustomer = customerRepository.findById(id).orElse(null);
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        var customerDTO = new CustomerDTO();
        customerDTO.setFirstName(NEW_FIRST_NAME);

        customerService.patch(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).orElse(null);

        assertNotNull(updatedCustomer);
        assertEquals(id, updatedCustomer.getId());
        assertNotEquals(originalFirstName, updatedCustomer.getFirstName());
        assertEquals(NEW_FIRST_NAME, updatedCustomer.getFirstName());
        assertEquals(originalLastName, updatedCustomer.getLastName());
    }

    @Test
    void testPatchCustomerLastName() {
        Customer originalCustomer = customerRepository.findById(id).orElse(null);
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        var customerDTO = new CustomerDTO();
        customerDTO.setLastName(NEW_LAST_NAME);

        customerService.patch(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).orElse(null);

        assertNotNull(updatedCustomer);
        assertEquals(id, updatedCustomer.getId());
        assertEquals(originalFirstName, updatedCustomer.getFirstName());
        assertNotEquals(originalLastName, updatedCustomer.getLastName());
        assertEquals(NEW_LAST_NAME, updatedCustomer.getLastName());
    }

}
