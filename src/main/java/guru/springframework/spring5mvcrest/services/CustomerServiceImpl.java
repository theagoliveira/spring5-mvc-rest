package guru.springframework.spring5mvcrest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springframework.spring5mvcrest.api.v1.mapper.CustomerMapper;
import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
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

}
