package guru.springframework.spring5mvcrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5mvcrest.domain.Category;
import guru.springframework.spring5mvcrest.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
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

}
