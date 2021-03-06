package guru.springframework.spring5mvcrest.services;

import java.util.List;

import guru.springframework.model.CustomerDTO;

public interface CustomerService {

    List<CustomerDTO> findAll();

    CustomerDTO findById(Long id);

    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO put(Long id, CustomerDTO customerDTO);

    CustomerDTO patch(Long id, CustomerDTO customerDTO);

    void delete(Long id);

}
