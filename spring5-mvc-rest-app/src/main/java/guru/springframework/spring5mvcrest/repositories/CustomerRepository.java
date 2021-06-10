package guru.springframework.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.spring5mvcrest.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
