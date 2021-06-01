package guru.springframework.spring5mvcrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.spring5mvcrest.api.v1.mapper.CategoryMapper;
import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.domain.Category;
import guru.springframework.spring5mvcrest.repositories.CategoryRepository;

class CategoryServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "name";

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    void testFindAll() {
        // given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        // when
        List<CategoryDTO> categoryDTOs = categoryService.findAll();

        // then
        assertEquals(categories.size(), categoryDTOs.size());

    }

    @Test
    void testFindByName() {
        // given
        var category = new Category();
        category.setId(ID);
        category.setName(NAME);
        when(categoryRepository.findByName(anyString())).thenReturn(category);

        // when
        CategoryDTO categoryDTO = categoryService.findByName(NAME);

        // then
        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getName(), categoryDTO.getName());
    }

}
