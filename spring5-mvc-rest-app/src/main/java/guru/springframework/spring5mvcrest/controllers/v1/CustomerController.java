package guru.springframework.spring5mvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.model.CustomerDTO;
import guru.springframework.model.CustomerDTOList;
import guru.springframework.spring5mvcrest.services.CustomerService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = CustomerController.BASE_URI, produces = {
        "application/json", "application/xml"})
public class CustomerController {

    public static final String BASE_URI = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This will get a list of all customers.", notes = "Notes about the API.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTOList index() {
        var customerDTOList = new CustomerDTOList();
        customerDTOList.getCustomers().addAll(customerService.findAll());
        return customerDTOList;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO show(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO create(@RequestBody CustomerDTO customerDTO) {
        return customerService.save(customerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO put(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.put(id, customerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patch(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.patch(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void destroy(@PathVariable Long id) {
        customerService.delete(id);
    }

}
