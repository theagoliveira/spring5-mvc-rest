package guru.springframework.spring5mvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import guru.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import guru.springframework.spring5mvcrest.domain.Customer;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "customerUrl", expression = "java(\"/api/v1/customers/\" + customer.getId())")
    CustomerDTO customerToCustomerDTO(Customer customer);

}
