package guru.springframework.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.spring5mvcrest.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {}
