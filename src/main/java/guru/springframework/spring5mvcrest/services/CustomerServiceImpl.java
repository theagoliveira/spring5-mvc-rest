package guru.springframework.spring5mvcrest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springframework.spring5mvcrest.api.v1.mapper.CustomerMapper;
import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import guru.springframework.spring5mvcrest.domain.Customer;
import guru.springframework.spring5mvcrest.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                                 .stream()
                                 .map(customerMapper::customerToCustomerDTO)
                                 .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findById(Long id) {
        return customerMapper.customerToCustomerDTO(
            customerRepository.findById(id).orElseThrow(RuntimeException::new)
        );
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        var customer = customerMapper.customerDTOToCustomer(customerDTO);
        var savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(savedCustomer);
    }

    @Override
    public CustomerDTO put(Long id, CustomerDTO customerDTO) {
        customerDTO.setId(id);
        return save(customerDTO);
    }

    @Override
    public CustomerDTO patch(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);

        if (customerDTO.getFirstName() == null)
            customerDTO.setFirstName(customer.getFirstName());

        if (customerDTO.getLastName() == null)
            customerDTO.setLastName(customer.getLastName());

        return put(id, customerDTO);
    }

}
