package guru.springframework.spring5mvcrest.services;

import java.util.List;
import java.util.stream.Collectors;

import guru.springframework.spring5mvcrest.api.v1.mapper.CategoryMapper;
import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.repositories.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                                 .stream()
                                 .map(categoryMapper::categoryToCategoryDTO)
                                 .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }

    @Override
    public CategoryDTO findById(Long id) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findById(id).orElse(null));
    }

}
