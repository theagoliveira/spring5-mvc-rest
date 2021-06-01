package guru.springframework.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.spring5mvcrest.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByNameIgnoreCase(String name);

}
