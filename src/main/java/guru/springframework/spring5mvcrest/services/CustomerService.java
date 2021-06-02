package guru.springframework.spring5mvcrest.services;

import java.util.List;

import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTO;

public interface CustomerService {

    List<CustomerDTO> findAll();

    CustomerDTO findById(Long id);

    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO put(Long id, CustomerDTO customerDTO);

}
