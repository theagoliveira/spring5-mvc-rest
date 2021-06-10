package guru.springframework.spring5mvcrest.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guru.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5mvcrest.domain.Category;

class CategoryMapperTest {

    private static final Long ID = 1L;
    private static final String NAME = "name";

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void testCategoryToCategoryDTO() {
        // given
        var category = new Category();
        category.setId(ID);
        category.setName(NAME);

        // when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        // then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }

}
