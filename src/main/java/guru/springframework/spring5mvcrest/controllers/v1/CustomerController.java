package guru.springframework.spring5mvcrest.controllers.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTOList;
import guru.springframework.spring5mvcrest.services.CustomerService;

@RestController
@RequestMapping(value = "/api/v1/customers", produces = "application/json")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public CustomerDTOList index() {
        return new CustomerDTOList(customerService.findAll());
    }

    @GetMapping("/{id}")
    public CustomerDTO showById(@PathVariable Long id) {
        return customerService.findById(id);
    }

}
