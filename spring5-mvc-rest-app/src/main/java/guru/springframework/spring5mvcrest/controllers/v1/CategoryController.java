package guru.springframework.spring5mvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTOList;
import guru.springframework.spring5mvcrest.services.CategoryService;

@RestController
@RequestMapping(value = CategoryController.BASE_URI, produces = {
        "application/json", "application/xml"})
public class CategoryController {

    public static final String BASE_URI = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTOList index() {
        return new CategoryDTOList(categoryService.findAll());
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO showByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO showById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

}
