package guru.springframework.spring5mvcrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.spring5mvcrest.api.v1.mapper.CustomerMapper;
import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import guru.springframework.spring5mvcrest.controllers.v1.CustomerController;
import guru.springframework.spring5mvcrest.domain.Customer;
import guru.springframework.spring5mvcrest.repositories.CustomerRepository;

class CustomerServiceTest {

    private static final String CUSTOMERS_URI_SLASH = CustomerController.BASE_URI + "/";
    private static final Long ID = 1L;
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void testFindAll() {
        // given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        // when
        List<CustomerDTO> customerDTOs = customerService.findAll();

        // then
        assertEquals(customers.size(), customerDTOs.size());
    }

    @Test
    void testFindById() {
        // given
        var customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        // when
        CustomerDTO customerDTO = customerService.findById(ID);

        // then
        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getLastName(), customerDTO.getLastName());
        assertEquals(CUSTOMERS_URI_SLASH + customer.getId(), customerDTO.getCustomerUrl());
    }

    @Test
    void testFindByIdNotFound() {
        // given
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when
        assertThrows(ResourceNotFoundException.class, () -> customerService.findById(ID));
    }

    @Test
    void testSave() {
        // given
        var customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        var customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // when
        CustomerDTO returnCustomerDTO = customerService.save(customerDTO);

        // then
        assertEquals(customer.getId(), returnCustomerDTO.getId());
        assertEquals(customer.getFirstName(), returnCustomerDTO.getFirstName());
        assertEquals(customer.getLastName(), returnCustomerDTO.getLastName());
        assertEquals(CUSTOMERS_URI_SLASH + customer.getId(), returnCustomerDTO.getCustomerUrl());
    }

    @Test
    void testPut() {
        // given
        var customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        var customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // when
        CustomerDTO returnCustomerDTO = customerService.put(ID, customerDTO);

        // then
        assertEquals(ID, returnCustomerDTO.getId());
        assertEquals(customerDTO.getFirstName(), returnCustomerDTO.getFirstName());
        assertEquals(customerDTO.getLastName(), returnCustomerDTO.getLastName());
        assertEquals(CUSTOMERS_URI_SLASH + ID, returnCustomerDTO.getCustomerUrl());
    }

    @Test
    void testPatchNotFound() {
        // given
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
        CustomerDTO customerDTO = new CustomerDTO();

        // when
        assertThrows(ResourceNotFoundException.class, () -> customerService.patch(ID, customerDTO));
    }

    @Test
    void testDelete() {
        // when
        customerService.delete(ID);

        // then
        verify(customerRepository).deleteById(anyLong());
    }

}
