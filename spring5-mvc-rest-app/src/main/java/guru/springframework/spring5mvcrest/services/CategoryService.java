package guru.springframework.spring5mvcrest.services;

import java.util.List;

import guru.springframework.model.CategoryDTO;

public interface CategoryService {

    List<CategoryDTO> findAll();

    CategoryDTO findByName(String name);

    CategoryDTO findById(Long id);

}
