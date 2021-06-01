package guru.springframework.spring5mvcrest.controllers.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTOList;
import guru.springframework.spring5mvcrest.services.CategoryService;

@RestController
@RequestMapping(value = "/api/v1/categories", produces = "application/json")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public CategoryDTOList index() {
        return new CategoryDTOList(categoryService.findAll());
    }

    @GetMapping("/name/{name}")
    public CategoryDTO showByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }

    @GetMapping("/{id}")
    public CategoryDTO showById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

}
