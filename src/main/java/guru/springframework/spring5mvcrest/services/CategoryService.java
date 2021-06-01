package guru.springframework.spring5mvcrest.services;

import java.util.List;

import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;

public interface CategoryService {

    List<CategoryDTO> findAll();

    CategoryDTO findByName(String name);

    CategoryDTO findById(Long id);

}
