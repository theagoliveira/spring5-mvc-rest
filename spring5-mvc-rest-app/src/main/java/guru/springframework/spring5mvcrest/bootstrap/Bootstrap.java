package guru.springframework.spring5mvcrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5mvcrest.domain.Category;
import guru.springframework.spring5mvcrest.domain.Customer;
import guru.springframework.spring5mvcrest.domain.Vendor;
import guru.springframework.spring5mvcrest.repositories.CategoryRepository;
import guru.springframework.spring5mvcrest.repositories.CustomerRepository;
import guru.springframework.spring5mvcrest.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        var fruits = new Category();
        fruits.setName("Fruits");
        categoryRepository.save(fruits);

        var dried = new Category();
        dried.setName("Dried");
        categoryRepository.save(dried);

        var fresh = new Category();
        fresh.setName("Fresh");
        categoryRepository.save(fresh);

        var exotic = new Category();
        exotic.setName("Exotic");
        categoryRepository.save(exotic);

        var nuts = new Category();
        nuts.setName("Nuts");
        categoryRepository.save(nuts);

        log.info(categoryRepository.count() + " categories loaded.");
    }

    private void loadCustomers() {
        var customer1 = new Customer();
        customer1.setFirstName("Susan");
        customer1.setLastName("Tanner");
        customerRepository.save(customer1);

        var customer2 = new Customer();
        customer2.setFirstName("Freddy");
        customer2.setLastName("Meyers");
        customerRepository.save(customer2);

        var customer3 = new Customer();
        customer3.setFirstName("Joe");
        customer3.setLastName("Buck");
        customerRepository.save(customer3);

        var customer4 = new Customer();
        customer4.setFirstName("Michael");
        customer4.setLastName("Weston");
        customerRepository.save(customer4);

        log.info(customerRepository.count() + " customers loaded.");
    }

    private void loadVendors() {
        var vendor1 = new Vendor();
        vendor1.setName("Fun Fresh Fruits Ltd.");
        vendorRepository.save(vendor1);

        var vendor2 = new Vendor();
        vendor2.setName("Nuts for Nuts Company");
        vendorRepository.save(vendor2);

        log.info(vendorRepository.count() + " vendors loaded.");
    }

}
